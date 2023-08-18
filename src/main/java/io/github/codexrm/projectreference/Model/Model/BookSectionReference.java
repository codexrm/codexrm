package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.BookSectionType;
import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.utils.FieldValidations;

import java.util.Objects;

public class BookSectionReference extends BookReference {

    private String chapter;
    private String pages;
    private BookSectionType type;

    private final FieldValidations validations = new FieldValidations();

    public BookSectionReference() {
        super();
        this.chapter = "";
        this.pages = "";
        this.type = null;
    }

    public BookSectionReference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String author, String editor, String publisher,
                                String volume, String number, String series, String address, String edition, String isbn, String chapter, String pages, BookSectionType type) {
        super(title, year, month, note, id, isFromServer, isModified, isActive, author, editor, publisher, volume, number, series, address, edition, isbn);
        this.type = type;

        if(validations.isNumber(chapter))
            this.chapter = chapter;

        if(validations.validatePages(pages))
            this.pages = pages;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        if(validations.isNumber(chapter))
        this.chapter = chapter;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        if(validations.validatePages(pages))
        this.pages = pages;
    }

    public BookSectionType getType() { return type; }

    public void setType(BookSectionType type) { this.type = type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookSectionReference that = (BookSectionReference) o;
        return Objects.equals(chapter, that.chapter) &&
                Objects.equals(pages, that.pages) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chapter, pages, type);
    }
}