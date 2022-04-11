package io.github.codexrm.projectreferences;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.io.IOException;
import java.time.LocalDate;

import org.jbibtex.*;
import io.github.codexrm.projectreference.Model.Controller.ReferenceLibraryManager;
import io.github.codexrm.projectreference.Model.Enum.*;
import io.github.codexrm.projectreference.Model.Model.*;

@TestMethodOrder(OrderAnnotation.class)
class ReferenceLibraryManagerTest {

    private final ReferenceLibraryManager manager =
            ReferenceLibraryManager.getReferenceLibraryManager();

    @Test
    void addReference() {
/*
        ArticleReference article = new ArticleReference();
        article.setAuthorIdList(manager.addAuthor("Martin-Gonzalez, Jacob"));
        article.setJournal("Sciences and education");
        article.setLocalDate(LocalDate.of(2008, 03, 01));
        article.setNote("aa");
        article.setNumber("no3443");
        article.setPages("p1011");
        article.setTitle("project of education");
        article.setVolume("vol132");
        //manager.addReference(article);

        BookReference book = new BookReference();
        book.setAddress("ave67 no02");
        book.setEdition("Ing Isis Perez");
        book.setAuthorIdList(manager.addAuthor("Navarro-Gener,Enrique;Martin-Gonzalez, Jacob"));
        book.setLocalDate(LocalDate.of(1987, 04, 01));
        book.setNote("bb");
        book.setPublisher("Prencite Hall");
        book.setSeries("xx7");
        book.setTitle("relations and your career");
        book.setVolume("vol35");
        // manager.addReference(book);

        BookSectionReference section = new BookSectionReference();
        section.setAddress("ave 76 no74");
        section.setChapter("I");
        section.setEdition("Ing Olga Domigz");
        section.setAuthorIdList(manager.addAuthor("Fernandez-Diaz,Maria"));
        section.setLocalDate(LocalDate.of(2011, 02, 01));
        section.setNote("cc");
        section.setPages("1234");
        section.setPublisher("Education");
        section.setSeries("ja4");
        section.setTitle("introduction to Windows");
        section.setVolume("vol35");
        // manager.addReference(section);

        ThesisReference thesis = new ThesisReference();
        thesis.setAddress("ave45,no 43");
        thesis.setAuthorIdList(manager.addAuthor("Navarro-Gener,Enrique;Martin-Gonzalez, Jacob;Fernandez-Diaz,Maria"));
        thesis.setLocalDate(LocalDate.of(2018, 07, 01));
        thesis.setNote("dd");
        thesis.setSchool("Fructuoso Rodriguez");
        thesis.setTitle("help of Windows");
        thesis.setType(ThesisType.Masters);
        // manager.addReference(thesis);

        BookLetReference bookLet = new BookLetReference();
        bookLet.setAddress("ave45,no76");
        bookLet.setHowpublished("NO SE");
        bookLet.setAuthorIdList(manager.addAuthor("Torres-Nunnez,Roberto"));
        bookLet.setLocalDate(LocalDate.of(2009, 11, 01));
        bookLet.setNote("ee");
        bookLet.setTitle("Images");
        // manager.addReference(bookLet);

        ConferenceProceedingsReference proceedings = new ConferenceProceedingsReference();
        proceedings.setAddress("ave76,no87");
        proceedings.setAuthorIdList(manager.addAuthor("Nunnez,Roberto"));
        proceedings.setLocalDate(LocalDate.of(2020, 8, 01));
        proceedings.setNote("ff");
        proceedings.setSerie("xl3");
        proceedings.setTitle("The men");
        proceedings.setVolume("vol34");
        // manager.addReference(proceedings);

        Assertions.assertEquals(6, manager.getReferenceLibrary().getReferenceTable().size());

        // Reference reference = manager.readReferenceId(3);
        // Assertions.assertEquals(reference, manager.getReferenceLibrary().getReferenceTable().get(3));

        // reference.setYear(2012);
        // manager.updateReference(reference);
        Assertions.assertEquals(manager.getReferenceLibrary().getReferenceTable().get(3).getLocalDate().getYear(),
                2012);

        try {
            manager.saveTables();
            // manager.deleteReferenceId(1);
            Assertions.assertEquals(5, manager.getReferenceLibrary().getReferenceTable().size());

            manager.loadTables();

            ThesisReference thesis2 = new ThesisReference();
            thesis2.setAddress("ave61");
            thesis2.setAuthorIdList(manager.addAuthor("Navarro-Gener,Enrique;Martin-Gonzalez, Jacob;Fernandez-Diaz,Maria"));
            thesis2.setLocalDate(LocalDate.of(2019, 05, 01));
            thesis2.setNote("hh");
            thesis2.setSchool("Cespedes");
            thesis2.setTitle("Reference");
            thesis2.setType(ThesisType.phd);
            // manager.addReference(thesis2);

            Assertions.assertEquals(7, manager.getReferenceLibrary().getReferenceTable().size());

           // manager.exportReference("test\\referenceRIS.txt",
                 //   manager.getReferenceLibrary().getReferenceTable().get(1), Format.RIS);
           // manager.exportReferenceTable("test\\tableReferenceRIS.txt", Format.RIS);

            manager.exportReference("test\\referenceBibTest.txt",
                    manager.getReferenceLibrary().getReferenceTable().get(2), Format.BIBTEST);
           // manager.exportReferenceTable("test\\tableReferenceBibTest.txt", Format.BIBTEST);

            //manager.importReferences("test\\importRis.txt", Format.RIS);
            Assertions.assertEquals(12, manager.getReferenceLibrary().getReferenceTable().size());

           // manager.importReferences("test\\importBibTex.txt", Format.BIBTEST);
            Assertions.assertEquals(19, manager.getReferenceLibrary().getReferenceTable().size());

        } catch (IOException | TokenMgrException | ParseException e) {
            e.printStackTrace();
        }*/
    }
}