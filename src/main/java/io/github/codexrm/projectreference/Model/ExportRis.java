package io.github.codexrm.projectreference.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import io.github.codexrm.jris.*;
import io.github.codexrm.projectreference.Enum.Month;
import io.github.codexrm.projectreference.Enum.ThesisType;

public class ExportRis implements Export {

  private AuthorLibrary authorLibrary;

  public ExportRis() {
    // Do nothing
  }

  public AuthorLibrary getAuthorLibrary() {
    return authorLibrary;
  }

  public void setAuthorLibrary(AuthorLibrary authorLibrary) {
    this.authorLibrary = authorLibrary;
  }

  @Override
  public void writeValue(Reference reference, String path) throws IOException {

    RisManager manager = new RisManager();
    manager.addReference(identifyType(reference));
    manager.exportListReference(path);
  }

  @Override
  public void writeValue(Hashtable<Integer, Reference> referenceTable, String path)
      throws IOException {

    RisManager manager = new RisManager();
    Enumeration<Integer> e = referenceTable.keys();
    Integer keyReference;
    Reference valorReference;

    while (e.hasMoreElements()) {
      keyReference = e.nextElement();
      valorReference = referenceTable.get(keyReference);
      RisEntry entry = identifyType(valorReference);
       manager.addReference(entry);
      
    }
    manager.exportListReference(path);
  }

  private RisEntry identifyType(Reference reference)  {
    RisEntry entry = new RisEntry();

    if (reference instanceof ArticleReference) {
      entry = createJour((ArticleReference) reference);

    } else {
      if (reference instanceof BookSectionReference) {
        entry = createSection((BookSectionReference) reference);

      } else {
        if (reference instanceof BookReference) {
          entry = createBook((BookReference) reference);

        } else {
          if (reference instanceof ThesisReference) {
            entry = createThesis((ThesisReference) reference);

          } else {
            if (reference instanceof ConferenceProceedingsReference) {
              entry = createConference((ConferenceProceedingsReference) reference);
            }
          }
        }
      }
    }
    return entry;
  }

  private void writeAuthors(ArrayList<Integer> listId, RisEntry entry) {

    ArrayList<Author> listAuthors = authorLibrary.readAuthorList(listId);

    for (int i = 0; i < listAuthors.size(); i++) {
      if (i == 0) {
        entry.setAu(listAuthors.get(0).getDataAuthor().getName(),
            listAuthors.get(0).getDataAuthor().getLastName());
      }
      if (i == 1) {
        entry.setA2(listAuthors.get(1).getDataAuthor().getName(),
            listAuthors.get(1).getDataAuthor().getLastName());
      }
      if (i == 2) {
        entry.setA3(listAuthors.get(2).getDataAuthor().getName(),
            listAuthors.get(2).getDataAuthor().getLastName());
      }
      if (i == 3) {
        entry.setA4(listAuthors.get(3).getDataAuthor().getName(),
            listAuthors.get(3).getDataAuthor().getLastName());
      }
    }
  }

  private RisMonth convetMonth(Month month) {

    switch (month) {
      case JAN:
        return RisMonth.JANUARY;
      case FEB:
        return RisMonth.FEBRURY;
      case MAR:
        return RisMonth.MARCH;
      case APR:
        return RisMonth.APRIL;
      case MAY:
        return RisMonth.MAY;
      case JUN:
        return RisMonth.JUNE;
      case JUL:
        return RisMonth.JULY;
      case AUG:
        return RisMonth.AUGUST;
      case SEP:
        return RisMonth.SEPTEMBER;
      case OCT:
        return RisMonth.OCTOBER;
      case NOV:
        return RisMonth.NOVEMBER;
      default:
        return RisMonth.DECEMBER;
    }
  }

  private void commonField(Reference reference, RisEntry entry) {

    entry.setTi(reference.getTitle());
    entry.setDa(String.valueOf(reference.getYear()), convetMonth(reference.getMonth()));
    entry.setN1(reference.getNote());
    writeAuthors(reference.getListAuthorId(), entry);
  }

  private Jour createJour(ArticleReference reference) {
    Jour jour = new Jour();

    commonField(reference, jour);
    jour.setJo(reference.getJournal());
    jour.setVl(reference.getVolume());
    jour.setIs(reference.getNumber());
    jour.setSp(reference.getPages());

    return jour;
  }

  private Book createBook(BookReference reference) {

    Book book = new Book();
    commonField(reference, book);
    book.setPb(reference.getPublisher());
    book.setVl(reference.getVolume());
    book.setT2(reference.getSeries());
    book.setAd(reference.getAddress());
    book.setEt(reference.getEdition());

    return book;
  }

  private Secc createSection(BookSectionReference reference) {

    Secc section = new Secc();
    commonField(reference, section);
    section.setPb(reference.getPublisher());
    section.setVl(reference.getVolume());
    section.setT3(reference.getSeries());
    section.setAd(reference.getAddress());
    section.setEt(reference.getEdition());
    section.setEp(reference.getChapter());
    section.setSp(reference.getPages());

    return section;
  }

  private Thes createThesis(ThesisReference reference) {

    Thes thesis = new Thes();
    commonField(reference, thesis);
    thesis.setPb(reference.getSchool());
    thesis.setAd(reference.getAddress());
    if (reference.getType().equals(ThesisType.Masters)) {
      thesis.setM3("Masters");
    } else {
      thesis.setM3("phd");
    }

    return thesis;
  }

  private Conf createConference(ConferenceProceedingsReference reference) {

    Conf proceedings = new Conf();
    commonField(reference, proceedings);
    proceedings.setVl(reference.getVolume());
    proceedings.setT3(reference.getSerie());
    proceedings.setAd(reference.getAddress());
    return proceedings;
  }
}
