package io.github.codexrm.projectreference.Model.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import io.github.codexrm.jris.*;
import io.github.codexrm.projectreference.Model.Enum.ThesisType;

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
            BaseReference entry = identifyType(valorReference);
            manager.addReference(entry);

        }
        manager.exportListReference(path);
    }

    private BaseReference identifyType(Reference reference) {
        BaseReference entry = new BaseReference();

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

    private void addAuthors(ArrayList<Integer> listId, ArrayList<String> listAuthorReference) {

        ArrayList<Author> listAuthors = authorLibrary.readAuthorList(listId);
        for (int i = 0; i < listAuthors.size(); i++) {
            String author = listAuthors.get(i).getAuthor();
            listAuthorReference.add(author);
        }
    }

    private void commonField(Reference reference, BaseReference entry) {

        entry.setNotes(reference.getNote());
    }

    private JournalArticle createJour(ArticleReference reference) {
        JournalArticle jour = new JournalArticle();

        commonField(reference, jour);
        addAuthors(reference.getAuthorIdList(), jour.getListAuthor());
        jour.setTitle(reference.getTitle());
        jour.setDate(reference.getDate());
        jour.setJournal(reference.getJournal());
        jour.setVolume(reference.getVolume());
        jour.setNumber(reference.getNumber());
        jour.setPages(reference.getPages());

        return jour;
    }

    private Book createBook(BookReference reference) {

        Book book = new Book();
        commonField(reference, book);
        addAuthors(reference.getAuthorIdList(), book.getListAuthor());
        book.setTitle(reference.getTitle());
        book.setDate(reference.getDate());
        book.setPublisher(reference.getPublisher());
        book.setVolume(reference.getVolume());
        book.setSerie(reference.getSeries());
        book.setAddress(reference.getAddress());
        book.setEdition(reference.getEdition());

        return book;
    }

    private BookSection createSection(BookSectionReference reference) {

        BookSection section = new BookSection();
        commonField(reference, section);
        addAuthors(reference.getAuthorIdList(), section.getListAuthor());
        section.setTitle(reference.getTitle());
        section.setDate(reference.getDate());
        section.setPublisher(reference.getPublisher());
        section.setVolume(reference.getVolume());
        section.setSerie(reference.getSeries());
        section.setAddress(reference.getAddress());
        section.setEdition(reference.getEdition());
        section.setChapter(reference.getChapter());
        section.setPages(reference.getPages());

        return section;
    }

    private Thesis createThesis(ThesisReference reference) {

        Thesis thesis = new Thesis();
        commonField(reference, thesis);
        addAuthors(reference.getAuthorIdList(), thesis.getListAuthor());
        thesis.setTitle(reference.getTitle());
        thesis.setDate(reference.getDate());
        thesis.setSchool(reference.getSchool());
        thesis.setAddress(reference.getAddress());
        if (reference.getType().equals(ThesisType.Masters)) {
            thesis.setThesisType("Masters");
        } else {
            thesis.setThesisType("phd");
        }

        return thesis;
    }

    private ConferenceProceedings createConference(ConferenceProceedingsReference reference) {

        ConferenceProceedings proceedings = new ConferenceProceedings();
        commonField(reference, proceedings);
        ;
        addAuthors(reference.getAuthorIdList(), proceedings.getListAuthor());
        proceedings.setTitle(reference.getTitle());
        proceedings.setDate(reference.getDate());
        proceedings.setVolume(reference.getVolume());
        proceedings.setSerie(reference.getSerie());
        proceedings.setAddress(reference.getAddress());
        return proceedings;
    }
}
