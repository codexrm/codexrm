package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.BookSectionType;
import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.model.model.BookSectionReference;
import javafx.beans.property.*;

public class BookSectionReferenceVM extends BookReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty chapter;
    private StringProperty pages;
    private ObjectProperty<BookSectionType> type;

    public BookSectionReferenceVM() {
        super();
        createEmptyBookSectionReferenceVM();
    }

    public BookSectionReferenceVM(BookSectionReference bookSectionReference) {
        super(bookSectionReference.getId(),bookSectionReference.getTitle(), bookSectionReference.getYear(), bookSectionReference.getMonth(), bookSectionReference.getNote(),
                bookSectionReference.isFromServer(), bookSectionReference.isActive(), bookSectionReference.isModified(), bookSectionReference.getAuthor(), bookSectionReference.getEditor(),
                bookSectionReference.getPublisher(), bookSectionReference.getVolume(),bookSectionReference.getNumber(), bookSectionReference.getSeries(), bookSectionReference.getAddress(),
                bookSectionReference.getEdition(), bookSectionReference.getIsbn());

        createEmptyBookSectionReferenceVM();

        setChapter(bookSectionReference.getChapter());
        setPages(bookSectionReference.getPages());
        setType(bookSectionReference.getType());
    }

    private void createEmptyBookSectionReferenceVM() {

        this.chapter = new SimpleStringProperty();
        this.pages = new SimpleStringProperty();
        this.type = new SimpleObjectProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.BOOKSECTION);
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

    public BookSectionType getType() { return type.get(); }

    public ObjectProperty<BookSectionType> typeProperty() { return type; }

    public void setType(BookSectionType type) { this.type.set(type); }

    public ReferenceType getReferenceType() {
        return referenceType.get();
    }

    public void setReferenceType(final ReferenceType referenceType) {
        this.referenceType.set(referenceType);
    }

    public ObjectProperty<ReferenceType> referenceTypeProperty() {
        return referenceType;
    }

    @Override
    public BookSectionReference toModel() {

        BookSectionReference bookSectionReference = new BookSectionReference();

        bookSectionReference.setTitle(this.getTitle());
        bookSectionReference.setYear(this.getYear());
        bookSectionReference.setMonth(this.getMonth());
        bookSectionReference.setId(this.getId());
        bookSectionReference.setNote(this.getNote());
        bookSectionReference.setFromServer(this.isIsFromServer());
        bookSectionReference.setModified(this.isIsModified());
        bookSectionReference.setActive(this.isIsActive());

        bookSectionReference.setAuthor(this.getAuthor());
        bookSectionReference.setEditor(this.getEditor());
        bookSectionReference.setPublisher(this.getPublisher());
        bookSectionReference.setVolume(this.getVolume());
        bookSectionReference.setNumber(this.getNumber());
        bookSectionReference.setSeries(this.getSeries());
        bookSectionReference.setAddress(this.getAddress());
        bookSectionReference.setEdition(this.getEdition());
        bookSectionReference.setIsbn(this.getIsbn());

        bookSectionReference.setChapter(this.getChapter());
        bookSectionReference.setPages(this.getPages());
        bookSectionReference.setType(this.getType());

        return bookSectionReference;
    }
}
