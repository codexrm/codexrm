package io.github.codexrm.projectreference.Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXParser;
import org.jbibtex.Key;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import org.jbibtex.Value;
import io.github.codexrm.projectreference.Enum.Month;
import io.github.codexrm.projectreference.Enum.ThesisType;

public class ImportBibTest implements Import {
  private AuthorLibrary authorLibrary;

  public ImportBibTest() {
    super();
  }

  public AuthorLibrary getAuthorLibrary() {
    return authorLibrary;
  }

  public void setAuthorLibrary(AuthorLibrary authorLibrary) {
    this.authorLibrary = authorLibrary;
  }

  @Override
  public ArrayList<Reference> readFile(String path)
      throws FileNotFoundException, TokenMgrException, ParseException {

    Reader reader = new FileReader(path);
    BibTeXParser bibtexParser = new BibTeXParser();
    BibTeXDatabase database = bibtexParser.parseFully(reader);
    Map<Key, BibTeXEntry> entryMap = database.getEntries();
    Collection<BibTeXEntry> entries = entryMap.values();
    ArrayList<Reference> listReference = new ArrayList<>();

    Reference reference = new Reference();
    for (BibTeXEntry entry : entries) {

      if (entry.getType().toString().equalsIgnoreCase("article")) {
        reference = createArticleReference(entry);
      } else {
        if (entry.getType().toString().equalsIgnoreCase("book")) {
          reference = createBookReference(entry);
        } else {
          if (entry.getType().toString().equalsIgnoreCase("inbook")) {
            reference = createBookSectionReference(entry);
          } else {
            if (entry.getType().toString().equalsIgnoreCase("booklet")) {
              reference = createBookLetReference(entry);
            } else {
              if (entry.getType().toString().equalsIgnoreCase("mastersthesis")
                  || entry.getType().toString().equalsIgnoreCase("phdthesis")) {
                reference = createThesisReference(entry);
              } else {
                if (entry.getType().toString().equalsIgnoreCase("inproceedings")) {
                  reference = createConferenceProceedingsReference(entry);
                }
              }
            }
          }
        }
      }
      if (reference != null) {
        listReference.add(reference);
      }
      reference = null;
    }
    return listReference;
  }

  private ArrayList<Integer> listIdAuthor(String content) {

    ArrayList<Integer> listId = new ArrayList<>();
    DataAuthor data = new DataAuthor();
    String[] listAuthors = content.split(" and ", 2);

    for (int i = 0; i < listAuthors.length; i++) {
      String[] author = listAuthors[i].split(",", 2);
      data.setName(author[1].trim());
      data.setLastName(author[0].trim());
      listId.add(authorLibrary.addAuthor(data));
    }
    return listId;
  }

  private Month getMonth(String date) {
    Month month;
    switch (date) {
      case "January":
        month = Month.JAN;
        break;
      case "February":
        month = Month.FEB;
        break;
      case "March":
        month = Month.MAR;
        break;
      case "April":
        month = Month.APR;
        break;
      case "May":
        month = Month.MAY;
        break;
      case "June":
        month = Month.JUN;
        break;
      case "July":
        month = Month.JUL;
        break;
      case "August":
        month = Month.AUG;
        break;
      case "September":
        month = Month.SEP;
        break;
      case "October":
        month = Month.OCT;
        break;
      case "November":
        month = Month.NOV;
        break;
      default:
        month = Month.DEC;
    }
    return month;
  }

  private void commonField(BibTeXEntry entry, Reference reference) {
    Value value = entry.getField(BibTeXEntry.KEY_AUTHOR);
    if (value != null) {
      reference.setListAuthorId(listIdAuthor(value.toUserString()));
    }
    value = entry.getField(BibTeXEntry.KEY_TITLE);
    if (value != null) {
      reference.setTitle(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_MONTH);
    if (value != null) {
      reference.setMonth(getMonth(value.toUserString()));
    }
    value = entry.getField(BibTeXEntry.KEY_YEAR);
    if (value != null) {
      reference.setYear(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_NOTE);
    if (value != null) {
      reference.setNote(value.toUserString());
    }
  }

  private Reference createArticleReference(BibTeXEntry entry) {

    ArticleReference article = new ArticleReference();
    commonField(entry, article);
    Value value = entry.getField(BibTeXEntry.KEY_JOURNAL);
    if (value != null) {
      article.setJournal(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_NUMBER);
    if (value != null) {
      article.setNumber(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_PAGES);
    if (value != null) {
      article.setPages(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_VOLUME);
    if (value != null) {
      article.setVolume(value.toUserString());
    }
    return article;
  }

  private Reference createBookReference(BibTeXEntry entry) {

    BookReference book = new BookReference();
    commonField(entry, book);
    createBook(entry, book);
    return book;
  }

  private void createBook(BibTeXEntry entry, BookReference book) {
    commonField(entry, book);
    Value value = entry.getField(BibTeXEntry.KEY_ADDRESS);
    if (value != null) {
      book.setAddress(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_EDITION);
    if (value != null) {
      book.setEdition(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_PUBLISHER);
    if (value != null) {
      book.setPublisher(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_SERIES);
    if (value != null) {
      book.setSeries(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_VOLUME);
    if (value != null) {
      book.setVolume(value.toUserString());
    }
  }

  private Reference createBookSectionReference(BibTeXEntry entry) {

    BookSectionReference bookSection = new BookSectionReference();
    commonField(entry, bookSection);
    createBook(entry, bookSection);
    Value value = entry.getField(BibTeXEntry.KEY_CHAPTER);
    if (value != null) {
      bookSection.setChapter(value.toUserString());
    }
    value = entry.getField(BibTeXEntry.KEY_PAGES);
    if (value != null) {
      bookSection.setPages(value.toUserString());
    }
    return bookSection;
  }

  private Reference createBookLetReference(BibTeXEntry entry) {

    BookLetReference bookLet = new BookLetReference();
    commonField(entry, bookLet);

    Value value = entry.getField(BibTeXEntry.KEY_ADDRESS);
    if (value != null) {
      bookLet.setAddress(value.toUserString());
    }

    value = entry.getField(BibTeXEntry.KEY_HOWPUBLISHED);
    if (value != null) {
      bookLet.setHowpublished(value.toUserString());
    }
    return bookLet;
  }

  private Reference createThesisReference(BibTeXEntry entry) {
    ThesisReference thesis = new ThesisReference();
    commonField(entry, thesis);

    Value value = entry.getField(BibTeXEntry.KEY_ADDRESS);
    if (value != null) {
      thesis.setAddress(value.toUserString());
    }

    value = entry.getField(BibTeXEntry.KEY_SCHOOL);
    if (value != null) {
      thesis.setSchool(value.toUserString());
    }

    if (entry.getType().getValue().equals("mastersthesis")) {
      thesis.setType(ThesisType.Masters);
    } else {
      thesis.setType(ThesisType.phd);
    }
    return thesis;
  }

  private Reference createConferenceProceedingsReference(BibTeXEntry entry) {

    ConferenceProceedingsReference proceedings = new ConferenceProceedingsReference();
    commonField(entry, proceedings);

    Value value = entry.getField(BibTeXEntry.KEY_ADDRESS);
    if (value != null) {
      proceedings.setAddress(value.toUserString());
    }

    value = entry.getField(BibTeXEntry.KEY_SERIES);
    if (value != null) {
      proceedings.setSerie(value.toUserString());
    }

    value = entry.getField(BibTeXEntry.KEY_VOLUME);
    if (value != null) {
      proceedings.setVolume(value.toUserString());
    }
    return proceedings;
  }
}
