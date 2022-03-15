package io.github.codexrm.projectreference.Model.Model;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import io.github.codexrm.jris.*;
import io.github.codexrm.projectreference.Model.Enum.ThesisType;

public class ImportRis implements Import {

    private AuthorLibrary authorLibrary;
    private final RisManager manager;

    public ImportRis() {
        this.manager = new RisManager();
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
        ArrayList<BaseReference> list = manager.importReferences(reader);

        for (BaseReference entry : list) {
            listReference.add(createReference(entry));
        }
        return listReference;
    }

    private Reference createReference(BaseReference entry) {
        Reference reference;
        if (entry instanceof JournalArticle) {
            reference = readArticleReference((JournalArticle) entry);
        } else {
            if (entry instanceof Book) {
                reference = readBookReference((Book) entry);
            } else {
                if (entry instanceof BookSection) {
                    reference = readBookSectionReference((BookSection) entry);
                } else {
                    if (entry instanceof Thesis) {
                        reference = readThesisReference((Thesis) entry);
                    } else {
                        if (entry instanceof ConferenceProceedings) {
                            reference = readConferenceProceedingsReference((ConferenceProceedings) entry);
                        } else {
                            reference = null;
                        }
                    }
                }
            }
        }
        return reference;
    }

    private void commonField(BaseReference entry, Reference reference) {
        reference.setNote(entry.getNotes());
    }

    private Reference readArticleReference(JournalArticle entry) {
        ArticleReference article = new ArticleReference();

        commonField(entry, article);
        article.addAuthorId(authorLibrary.addAuthor(entry.getListAuthor()));
        article.setTitle(entry.getTitle());
        article.setLocalDate(entry.getDate());
        article.setJournal(entry.getJournal());
        article.setVolume(entry.getVolume());
        article.setNumber(entry.getNumber());
        article.setPages(entry.getPages());

        return article;
    }

    private Reference readBookReference(Book entry) {
        BookReference book = new BookReference();

        commonField(entry, book);
        book.addAuthorId(authorLibrary.addAuthor(entry.getListAuthor()));
        book.setTitle(entry.getTitle());
        book.setLocalDate(entry.getDate());
        book.setAddress(entry.getAddress());
        book.setPublisher(entry.getPublisher());
        book.setVolume(entry.getVolume());
        book.setSeries(entry.getSerie());
        book.setEdition(entry.getEdition());

        return book;
    }

    private Reference readBookSectionReference(BookSection entry) {
        BookSectionReference section = new BookSectionReference();

        commonField(entry, section);
        section.addAuthorId(authorLibrary.addAuthor(entry.getListAuthor()));
        section.setTitle(entry.getTitle());
        section.setLocalDate(entry.getDate());
        section.setAddress(entry.getAddress());
        section.setPublisher(entry.getPublisher());
        section.setVolume(entry.getVolume());
        section.setSeries(entry.getSerie());
        section.setEdition(entry.getEdition());
        section.setChapter(entry.getChapter());
        section.setPages(entry.getPages());

        return section;
    }

    private Reference readThesisReference(Thesis entry) {
        ThesisReference thesis = new ThesisReference();

        commonField(entry, thesis);
        thesis.addAuthorId(authorLibrary.addAuthor(entry.getListAuthor()));
        thesis.setTitle(entry.getTitle());
        thesis.setLocalDate(entry.getDate());
        thesis.setAddress(entry.getAddress());
        thesis.setSchool(entry.getSchool());
        if (entry.getThesisType().equals("Masters")) {
            thesis.setType(ThesisType.Masters);
        } else {
            if (entry.getThesisType().equals("Phd")) {
                thesis.setType(ThesisType.phd);
            }
        }
        return thesis;
    }

    private Reference readConferenceProceedingsReference(ConferenceProceedings entry) {
        ConferenceProceedingsReference proceedings = new ConferenceProceedingsReference();

        commonField(entry, proceedings);
        proceedings.addAuthorId(authorLibrary.addAuthor(entry.getListAuthor()));
        proceedings.setTitle(entry.getTitle());
        proceedings.setLocalDate(entry.getDate());
        proceedings.setVolume(entry.getVolume());
        proceedings.setSerie(entry.getSerie());
        proceedings.setAddress(entry.getAddress());

        return proceedings;
    }
}