package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.utils.FieldValidations;

import java.util.Objects;

public class BookReference extends Reference {

    protected String author;
    protected String editor;
    protected String publisher;
    protected String volume;
    protected String number;
    protected String series;
    protected String address;
    protected String edition;
    protected String isbn;

    private final FieldValidations validations = new FieldValidations();

    public BookReference() {
        super();
        this.author = "";
        this.editor = "";
        this.publisher = "";
        this.volume = "";
        this.number = "";
        this.series = "";
        this.address = "";
        this.edition = "";
        this.isbn = "";
    }

    public BookReference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String author, String editor, String publisher,
                         String volume, String number, String series, String address, String edition, String isbn) {
        super(title, year, month, note, id, isFromServer, isModified, isActive);
        this.publisher = publisher;

        if(validations.validateAuthorOrEditor(author))
            this.author = author;

        if(validations.validateAuthorOrEditor(editor))
            this.editor = editor;

        if(validations.isNumber(volume))
            this.volume = volume;

        if(validations.validateNumber(number))
            this.number = number;

        if(validations.validateSeries(series))
            this.series = series;

        if(validations.validateAddress(address))
            this.address = address;

        if(validations.validateEdition(edition))
            this.edition = edition;

        if(validations.validateIsbn(isbn))
            this.isbn = isbn;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
    }

    public String getEditor() { return editor; }

    public void setEditor(String editor) {
        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getVolume() { return volume; }

    public void setVolume(String volume) {
        if(validations.isNumber(volume))
        this.volume = volume;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) {
        if(validations.validateNumber(number))
        this.number = number;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        if(validations.validateSeries(series))
        this.series = series;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(validations.validateAddress(address))
            this.address = address;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        if(validations.validateEdition(edition))
        this.edition = edition;
    }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) {
        if(validations.validateIsbn(isbn))
            this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookReference that = (BookReference) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(editor, that.editor) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(volume, that.volume) &&
                Objects.equals(number, that.number) &&
                Objects.equals(series, that.series) &&
                Objects.equals(address, that.address) &&
                Objects.equals(edition, that.edition) &&
                Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, editor, publisher, volume, number, series, address, edition, isbn);
    }
}