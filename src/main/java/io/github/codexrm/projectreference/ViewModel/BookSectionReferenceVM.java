package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.model.model.BookSectionReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookSectionReferenceVM extends BookReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    protected StringProperty chapter;
    protected StringProperty pages;

    public BookSectionReferenceVM() {
        super();
        createEmptyBookSectionReferenceVM();
    }

    public BookSectionReferenceVM(BookSectionReference bookSectionReference) {
        super(bookSectionReference.getId(), bookSectionReference.getAuthor(), bookSectionReference.getTitle(), bookSectionReference.getLocalDate(), bookSectionReference.getNote(), bookSectionReference.isFromServer(), bookSectionReference.isActive(), bookSectionReference.isModified(), bookSectionReference.getPublisher(), bookSectionReference.getVolume(), bookSectionReference.getSeries(), bookSectionReference.getAddress(), bookSectionReference.getEdition());

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

        BookSectionReference bookSectionReference = new BookSectionReference();

        bookSectionReference.setAuthor(this.getAuthor());
        bookSectionReference.setTitle(this.getTitle());
        bookSectionReference.setLocalDate(this.getDate());
        bookSectionReference.setId(this.getId());
        bookSectionReference.setNote(this.getNote());
        bookSectionReference.setFromServer(this.isIsFromServer());
        bookSectionReference.setModified(this.isIsModified());
        bookSectionReference.setActive(this.isIsActive());

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
