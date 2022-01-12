package io.github.codexrm.projectreferences;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import java.io.IOException;
import java.util.ArrayList;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;
import io.github.codexrm.projectreference.Controller.ReferenceLibraryManager;
import io.github.codexrm.projectreference.Enum.*;
import io.github.codexrm.projectreference.Model.*;

@TestMethodOrder(OrderAnnotation.class)
class ReferenceLibraryMaanagerTest {

  private final ReferenceLibraryManager manager =
      ReferenceLibraryManager.getReferenceLibraryManager();

  @Test
  void addReference() {
    DataAuthor data1 = new DataAuthor("Jacob", "Martin-Gonzalez", "jaboc@gmail.cu");
    DataAuthor data2 = new DataAuthor("Enrique", "Navarro-Gener", "enrique@gmail.cu");
    DataAuthor data3 = new DataAuthor("Maria", "Fernandez-Diaz", "maria@gmail.cu");
    DataAuthor data4 = new DataAuthor("Roberto", "Torres-Nunnez", "roberto@gmail.cu");
    DataAuthor data5 = new DataAuthor("Roberto", "Nunnez", "roberto@gmail.cu");

    ArrayList<DataAuthor> listDataA = new ArrayList<DataAuthor>();
    ArrayList<DataAuthor> listDataB = new ArrayList<DataAuthor>();
    ArrayList<DataAuthor> listDataBS = new ArrayList<DataAuthor>();
    ArrayList<DataAuthor> listDataT = new ArrayList<DataAuthor>();
    ArrayList<DataAuthor> listDataBL = new ArrayList<DataAuthor>();
    ArrayList<DataAuthor> listDataCP = new ArrayList<DataAuthor>();

    listDataA.add(data1);
    ArticleReference article = new ArticleReference();
    article.setListAuthorId(manager.addAuthor(listDataA));
    article.setJournal("Sciences and education");
    article.setMonth(Month.MAR);
    article.setNote("aa");
    article.setNumber("no3443");
    article.setPages("p1011");
    article.setTitle("project of education");
    article.setVolume("vol132");
    article.setYear("2008");
    // manager.addReference(article);

    listDataB.add(data2);
    listDataB.add(data1);
    BookReference book = new BookReference();
    book.setAddress("ave67 no02");
    book.setEdition("Ing Isis Perez");
    book.setListAuthorId(manager.addAuthor(listDataB));
    book.setMonth(Month.APR);
    book.setNote("bb");
    book.setPublisher("Prencite Hall");
    book.setSeries("xx7");
    book.setTitle("relations and your career");
    book.setVolume("vol35");
    book.setYear("1987");
    // manager.addReference(book);

    listDataBS.add(data3);
    BookSectionReference seccion = new BookSectionReference();
    seccion.setAddress("ave 76 no74");
    seccion.setChapter("I");
    seccion.setEdition("Ing Olga Domigz");
    seccion.setListAuthorId(manager.addAuthor(listDataBS));
    seccion.setMonth(Month.FEB);
    seccion.setNote("cc");
    seccion.setPages("1234");
    seccion.setPublisher("Education");
    seccion.setSeries("ja4");
    seccion.setTitle("introduction to Windows");
    seccion.setVolume("vol35");
    seccion.setYear("2011");
    // manager.addReference(seccion);

    listDataT.add(data2);
    listDataT.add(data1);
    listDataT.add(data3);
    ThesisReference thesis = new ThesisReference();
    thesis.setAddress("ave45,no 43");
    thesis.setListAuthorId(manager.addAuthor(listDataT));
    thesis.setMonth(Month.JUL);
    thesis.setNote("dd");
    thesis.setSchool("Fructuoso Rodriguez");
    thesis.setTitle("help of Windows");
    thesis.setType(ThesisType.Masters);
    thesis.setYear("2018");
    // manager.addReference(thesis);

    listDataBL.add(data4);
    BookLetReference bookLet = new BookLetReference();
    bookLet.setAddress("ave45,no76");
    bookLet.setHowpublished("NO SE");
    bookLet.setListAuthorId(manager.addAuthor(listDataBL));
    bookLet.setMonth(Month.NOV);
    bookLet.setNote("ee");
    bookLet.setTitle("Images");
    bookLet.setYear("2009");
    // manager.addReference(bookLet);

    listDataCP.add(data5);
    ConferenceProceedingsReference proceedings = new ConferenceProceedingsReference();
    proceedings.setAddress("ave76,no87");
    proceedings.setListAuthorId(manager.addAuthor(listDataCP));
    proceedings.setMonth(Month.AUG);
    proceedings.setNote("ff");
    proceedings.setSerie("xl3");
    proceedings.setTitle("The men");
    proceedings.setVolume("vol34");
    proceedings.setYear("2020");
    // manager.addReference(proceedings);

    Assertions.assertEquals(6, manager.getReferenceLibrary().getReferenceTable().size());

    // Reference reference = manager.readReferenceId(3);
    // Assertions.assertEquals(reference, manager.getReferenceLibrary().getReferenceTable().get(3));

    // reference.setYear(2012);
    // manager.updateReference(reference);
    Assertions.assertEquals(manager.getReferenceLibrary().getReferenceTable().get(3).getYear(),
        2012);

    try {
      manager.saveTables();
      // manager.deleteReferenceId(1);
      Assertions.assertEquals(5, manager.getReferenceLibrary().getReferenceTable().size());

      manager.loadTables();

      ThesisReference thesis2 = new ThesisReference();
      thesis2.setAddress("ave61");
      thesis2.setListAuthorId(manager.addAuthor(listDataT));
      thesis2.setMonth(Month.MAY);
      thesis2.setNote("hh");
      thesis2.setSchool("Cespedes");
      thesis2.setTitle("Reference");
      thesis2.setType(ThesisType.phd);
      thesis2.setYear("2019");
      // manager.addReference(thesis2);

      Assertions.assertEquals(7, manager.getReferenceLibrary().getReferenceTable().size());

      manager.exportReference("test\\referenceRIS.txt",
          manager.getReferenceLibrary().getReferenceTable().get(1), Format.RIS);
      manager.exportReferenceTable("test\\tableReferenceRIS.txt", Format.RIS);

      manager.exportReference("test\\referenceBibTest.txt",
          manager.getReferenceLibrary().getReferenceTable().get(2), Format.BIBTEST);
      manager.exportReferenceTable("test\\tableReferenceBibTest.txt", Format.BIBTEST);

      manager.importReferences("test\\importRis.txt", Format.RIS);
      Assertions.assertEquals(12, manager.getReferenceLibrary().getReferenceTable().size());

      manager.importReferences("test\\importBibTex.txt", Format.BIBTEST);
      Assertions.assertEquals(19, manager.getReferenceLibrary().getReferenceTable().size());

    } catch (IOException | TokenMgrException | ParseException e) {
      e.printStackTrace();
    }
  }
}


