package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.model.AuthorLibrary;
import io.github.codexrm.projectreference.model.model.BookSectionReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class BookSectionReferenceVM extends BookReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    protected StringProperty chapter;
    protected StringProperty pages;

    public BookSectionReferenceVM() {
        super();
        createEmptyBookSectionReferenceVM();
    }

    public BookSectionReferenceVM(final int id, final ArrayList<Integer> authorIdList, final String title, LocalDate date, String note, final String publisher,
                                  final String volume, final String series, final String address, final String edition,
                                  final String chapter, final String pages, final AuthorLibrary authorLibrary) {
        super(id, authorIdList, title, date, note, publisher, volume, series, address, edition, authorLibrary);

        createEmptyBookSectionReferenceVM();

        setChapter(chapter);
        setPages(pages);


    }

    public BookSectionReferenceVM(BookSectionReference bookSectionReference,AuthorLibrary authorLibrary) {
        super(bookSectionReference.getId(), bookSectionReference.getAuthorIdList(), bookSectionReference.getTitle(), bookSectionReference.getLocalDate(), bookSectionReference.getNote(), bookSectionReference.getPublisher(), bookSectionReference.getVolume(), bookSectionReference.getSeries(), bookSectionReference.getAddress(), bookSectionReference.getEdition(),authorLibrary);

        createEmptyBookSectionReferenceVM();

        setChapter(bookSectionReference.getChapter());
        setPages(bookSectionReference.getPages());
    }

    private void createEmptyBookSectionReferenceVM() {
        this.chapter = new SimpleStringProperty();
        this.pages = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.BOOKSECTION);
    }

    public ReferenceType getReferenceType() {
        return referenceType.get();
    }

    public void setReferenceType(final ReferenceType referenceType) {
        this.referenceType.set(referenceType);
    }

    public ObjectProperty<ReferenceType> referenceTypeProperty() {
        return referenceType;
    }

    public String getChapter() {
        return chapter.get();
    }

    public StringProperty chapterProperty() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter.set(chapter);
    }

    public String getPages() {
        return pages.get();
    }

    public StringProperty pagesProperty() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages.set(pages);
    }

    @Override
    public BookSectionReference toModel() {
        BookSectionReference bookSectionReference = new BookSectionReference(this.getId(), this.getAuthorIdList(), this.getTitle(), this.getDate(), this.getNote());

        bookSectionReference.setPublisher(this.getPublisher());
        bookSectionReference.setVolume(this.getVolume());
        bookSectionReference.setSeries(this.getSeries());
        bookSectionReference.setAddress(this.getAddress());
        bookSectionReference.setEdition(this.getEdition());
        bookSectionReference.setChapter(this.getChapter());
        bookSectionReference.setPages(this.getPages());
        return bookSectionReference;
    }
}
