package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.utils.FieldValidations;

import java.util.Objects;

public class ConferencePaperReference extends Reference {

    private String author;
    private String bookTitle;
    private String editor;
    private String volume;
    private String number;
    private String series;
    private String pages;
    private String address;
    private String organization;
    private String publisher;

    private final FieldValidations validations = new FieldValidations();

    public ConferencePaperReference() {
        super();
        this.author = "";
        this.bookTitle = "";
        this.editor = "";
        this.volume = "";
        this.number = "";
        this.series = "";
        this.pages = "";
        this.address = "";
        this.organization = "";
        this.publisher = "";
    }

    public ConferencePaperReference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String author, String bookTitle, String editor,
                                    String volume, String number, String series, String pages, String address, String organization, String publisher) {
        super(title, year, month, note, id, isFromServer, isModified, isActive);
        this.bookTitle = bookTitle;
        this.organization = organization;
        this.publisher = publisher;

        if(validations.validateAuthorOrEditor(author))
            this.author = author;

        if(validations.validateAuthorOrEditor(editor))
            this.editor = editor;

        if(validations.validateNumber(number))
            this.number = number;

        if(validations.validateSeries(series))
            this.series = series;

        if(validations.validateChapterOrVolume(volume))
            this.volume = volume;

        if(validations.validateAddress(address))
            this.address = address;

        if(validations.validatePages(pages))
            this.pages = pages;

    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getBookTitle() { return bookTitle; }

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }

    public String getEditor() { return editor; }

    public void setEditor(String editor) {
        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;
    }

    public String getVolume() { return volume; }

    public void setVolume(String volume) {
        if(validations.validateChapterOrVolume(volume))
        this.volume = volume;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) {
        if(validations.validateNumber(number))
        this.number = number;
    }

    public String getSeries() { return series; }

    public void setSeries(String series) {
        if(validations.validateSeries(series))
        this.series = series;
    }

    public String getPages() { return pages; }

    public void setPages(String pages) {
        if(validations.validatePages(pages))
        this.pages = pages;
    }

    public String getAddress() { return address; }

    public void setAddress(String address) {
        if(validations.validateAddress(address))
        this.address = address;
    }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) { this.organization = organization; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConferencePaperReference that = (ConferencePaperReference) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(bookTitle, that.bookTitle) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(volume, that.volume) &&
                Objects.equals(number, that.number) &&
                Objects.equals(series, that.series) &&
                Objects.equals(pages, that.pages) &&
                Objects.equals(address, that.address) &&
                Objects.equals(organization, that.organization) &&
                Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, bookTitle, editor, volume, number, series, pages, address, organization, publisher);
    }
}
