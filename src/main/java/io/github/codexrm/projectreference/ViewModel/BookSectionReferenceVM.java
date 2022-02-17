package io.github.codexrm.projectreference.ViewModel;

import io.github.codexrm.projectreference.Model.Model.BookSectionReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Objects;

public class BookSectionReferenceVM extends BookReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    protected StringProperty chapter;
    protected StringProperty pages;

    public BookSectionReferenceVM() {
        super();
        createEmptyBookSectionReferenceVM();
    }

    public BookSectionReferenceVM(final int id, final String author, final String title, LocalDate date, String note, final String publisher,
                                  final String volume, final String series, final String address, final String edition,
                                  final String chapter, final String pages) {
        super(id, author, title, date, note, publisher, volume, series, address, edition);

        createEmptyBookSectionReferenceVM();

        setChapter(chapter);
        setPages(pages);


    }

    public BookSectionReferenceVM(BookSectionReference bookSectionReference) {
        super(bookSectionReference.getId(), bookSectionReference.getAuthor(), bookSectionReference.getTitle(), bookSectionReference.getDate(), bookSectionReference.getNote(), bookSectionReference.getPublisher(), bookSectionReference.getVolume(), bookSectionReference.getSeries(), bookSectionReference.getAddress(), bookSectionReference.getEdition());

        createEmptyBookSectionReferenceVM();

        setChapter(bookSectionReference.getChapter());
        setPages(bookSectionReference.getPages());
    }

    private void createEmptyBookSectionReferenceVM() {
        this.publisher = new SimpleStringProperty();
        this.volume = new SimpleStringProperty();
        this.series = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.edition = new SimpleStringProperty();
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
        BookSectionReference bookSectionReference = new BookSectionReference(this.getId(), this.getAuthor(), this.getTitle(), this.getDate(), this.getNote(), this.getPublisher(), this.getVolume(), this.getSeries(), this.getAddress(), this.getEdition());

        bookSectionReference.setChapter(this.getChapter());
        bookSectionReference.setPages(this.getPages());
        return bookSectionReference;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BookSectionReferenceVM)) return false;
        if (!super.equals(o)) return false;
        final BookSectionReferenceVM that = (BookSectionReferenceVM) o;
        return getReferenceType().equals(that.getReferenceType()) &&
                Objects.equals(getChapter(), that.getChapter()) &&
                Objects.equals(getPages(), that.getPages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getReferenceType(), getChapter(), getPages());
    }
}
