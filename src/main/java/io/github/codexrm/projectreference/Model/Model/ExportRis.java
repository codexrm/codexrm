package io.github.codexrm.projectreference.model.model;

import java.io.IOException;
import java.util.ArrayList;

import io.github.codexrm.jris.*;
import io.github.codexrm.projectreference.model.Enum.ThesisType;

public class ExportRis implements Export {


    public ExportRis() {
        // Do nothing
    }

    @Override
    public void writeValue(Reference reference, String path) throws IOException {

        RisManager manager = new RisManager();
        manager.addReference(identifyType(reference));
        manager.exportListReference(path);
    }

    @Override
    public void writeValue(ArrayList<Reference> referenceList, String path)
            throws IOException {

        RisManager manager = new RisManager();
        for (Reference reference : referenceList) {
            BaseReference entry = identifyType(reference);
            if (entry != null) {
                manager.addReference(entry);
            }
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
                        }else{
                            entry = null;
                        }
                    }
                }
            }
        }
        return entry;
    }

    private void addAuthors(String author, ArrayList<String> listAuthorReference) {

        String[] authors = author.split(";");
        for (int i = 0; i < authors.length; i++) {
            listAuthorReference.add(authors[i]);
        }
    }

    private void commonField(Reference reference, BaseReference entry) {

        entry.setNotes(reference.getNote());
    }

    private JournalArticle createJour(ArticleReference reference) {
        JournalArticle jour = new JournalArticle();

        commonField(reference, jour);
        addAuthors(reference.getAuthor(), jour.getAuthorList());
        jour.setTitle(reference.getTitle());
        jour.setDate(reference.getLocalDate());
        jour.setJournal(reference.getJournal());
        jour.setVolume(reference.getVolume());
        jour.setNumber(reference.getNumber());
        jour.setPages(reference.getPages());

        return jour;
    }

    private Book createBook(BookReference reference) {

        Book book = new Book();
        commonField(reference, book);
        addAuthors(reference.getAuthor(), book.getAuthorList());
        book.setTitle(reference.getTitle());
        book.setDate(reference.getLocalDate());
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
        addAuthors(reference.getAuthor(), section.getAuthorList());
        section.setTitle(reference.getTitle());
        section.setDate(reference.getLocalDate());
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
        addAuthors(reference.getAuthor(), thesis.getAuthorList());
        thesis.setTitle(reference.getTitle());
        thesis.setDate(reference.getLocalDate());
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
        addAuthors(reference.getAuthor(), proceedings.getAuthorList());
        proceedings.setTitle(reference.getTitle());
        proceedings.setDate(reference.getLocalDate());
        proceedings.setVolume(reference.getVolume());
        proceedings.setSerie(reference.getSerie());
        proceedings.setAddress(reference.getAddress());
        return proceedings;
    }
}
