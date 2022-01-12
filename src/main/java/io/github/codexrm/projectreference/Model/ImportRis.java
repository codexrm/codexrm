package io.github.codexrm.projectreference.Model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import io.github.codexrm.jris.*;
import io.github.codexrm.projectreference.Enum.Month;
import io.github.codexrm.projectreference.Enum.ThesisType;

public class ImportRis implements Import {

  private AuthorLibrary authorLibrary;
  private final RisManager manager;

  public ImportRis() {
    this.manager =  new RisManager();
  }

  public AuthorLibrary getAuthorLibrary() {
    return authorLibrary;
  }

  public void setAuthorLibrary(AuthorLibrary authorLibrary) {
    this.authorLibrary = authorLibrary;
  }

  @Override
  public ArrayList<Reference> readFile(String path) throws IOException {
    ArrayList<Reference> listReference = new ArrayList<>();

    Reader reader = new FileReader(path);
    ArrayList<RisEntry> list = manager.importReferences(reader);

    for (RisEntry entry : list) {
      listReference.add(createReference(entry));
    }
    return listReference;
  }

  private String getYear(String date) throws NumberFormatException {
    date = date.trim();
    return date;
  }

  private Month getMonth(String date) {
    Month month;
    switch (date) {
      case "01":
        month = Month.JAN;
        break;
      case "02":
        month = Month.FEB;
        break;
      case "03":
        month = Month.MAR;
        break;
      case "04":
        month = Month.APR;
        break;
      case "05":
        month = Month.MAY;
        break;
      case "06":
        month = Month.JUN;
        break;
      case "07":
        month = Month.JUL;
        break;
      case "08":
        month = Month.AUG;
        break;
      case "09":
        month = Month.SEP;
        break;
      case "10":
        month = Month.OCT;
        break;
      case "11":
        month = Month.NOV;
        break;
      default:
        month = Month.DEC;
    }
    return month;
  }

  private Reference createReference(RisEntry entry) {
    Reference reference;
    if (entry instanceof Jour) {
      reference = readArticleReference((Jour) entry);
    } else {
      if (entry instanceof Book) {
        reference = readBookReference((Book) entry);
      } else {
        if (entry instanceof Secc) {
          reference = readBookSectionReference((Secc) entry);
        } else {
          if (entry instanceof Thes) {
            reference = readThesisReference((Thes) entry);
          } else {
            if (entry instanceof Conf) {
              reference = readConferenceProceedingsReference((Conf) entry);
            } else {
              reference = null;
            }
          }
        }
      }
    }
    return reference;
  }

  private int idAuthor(String content) {
    DataAuthor data = new DataAuthor();
    String[] authorLine = content.split(",", 2);
    data.setName(authorLine[1].trim());
    data.setLastName(authorLine[0].trim());

    return authorLibrary.addAuthor(data);
  }

  private void commonField(RisEntry entry, Reference reference) {

    reference.setTitle(entry.getTi());
    reference.setNote(entry.getN1());

    String[] dataDate = entry.getDa().split("/");
    reference.setYear(getYear(dataDate[0]));
    if (dataDate.length == 2) {
      reference.setMonth(getMonth(dataDate[1]));
    }
    if (entry.getAu() != null) {
      reference.addAuthorId(idAuthor(entry.getAu()));
    }
    if (entry.getA2() != null) {
      reference.addAuthorId(idAuthor(entry.getA2()));
    }
    if (entry.getA3() != null) {
      reference.addAuthorId(idAuthor(entry.getA3()));
    }
    if (entry.getA4() != null) {
      reference.addAuthorId(idAuthor(entry.getA4()));
    }
  }

  private Reference readArticleReference(Jour entry) {
    ArticleReference article = new ArticleReference();

    commonField(entry, article);
    article.setJournal(entry.getJo());
    article.setVolume(entry.getVl());
    article.setNumber(entry.getIs());
    article.setPages(entry.getSp());

    return article;
  }

  private Reference readBookReference(Book entry) {
    BookReference book = new BookReference();

    commonField(entry, book);
    book.setAddress(entry.getAd());
    book.setPublisher(entry.getPb());
    book.setVolume(entry.getVl());
    book.setSeries(entry.getT2());
    book.setEdition(entry.getEt());

    return book;
  }

  private Reference readBookSectionReference(Secc entry) {
    BookSectionReference section = new BookSectionReference();

    commonField(entry, section);
    section.setAddress(entry.getAd());
    section.setPublisher(entry.getPb());
    section.setVolume(entry.getVl());
    section.setSeries(entry.getT3());
    section.setEdition(entry.getEt());
    section.setChapter(entry.getEp());
    section.setPages(entry.getSp());

    return section;
  }

  private Reference readThesisReference(Thes entry) {
    ThesisReference thesis = new ThesisReference();

    commonField(entry, thesis);
    thesis.setAddress(entry.getAd());
    thesis.setSchool(entry.getPb());
    if (entry.getM3().equals("Masters")) {
      thesis.setType(ThesisType.Masters);
    } else {
      if (entry.getM3().equals("Phd")) {
        thesis.setType(ThesisType.phd);
      }
    }
    return thesis;
  }

  private Reference readConferenceProceedingsReference(Conf entry) {
    ConferenceProceedingsReference proceedings = new ConferenceProceedingsReference();

    commonField(entry, proceedings);
    proceedings.setVolume(entry.getVl());
    proceedings.setSerie(entry.getT3());
    proceedings.setAddress(entry.getAd());

    return proceedings;
  }
}
