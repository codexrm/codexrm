package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.utils.FieldValidations;

import java.util.Objects;

public class ConferenceProceedingsReference extends Reference {

    private String editor;
    private String volume;
    private String number;
    private String series;
    private String address;
    private String publisher;
    private String organization;
    private String isbn;

    private final FieldValidations validations = new FieldValidations();

    public ConferenceProceedingsReference() {
        super();
        this.editor = "";
        this.volume = "";
        this.number = "";
        this.series = "";
        this.address = "";
        this.publisher = "";
        this.organization = "";
        this.isbn = "";
    }

    public ConferenceProceedingsReference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String editor, String volume, String number,
                                          String series, String address, String publisher, String organization, String isbn) {
        super(title, year, month, note, id, isFromServer, isModified, isActive);
        this.publisher = publisher;
        this.organization = organization;

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

        if(validations.validateIsbn(isbn))
            this.isbn = isbn;
    }

    public String getEditor() { return editor; }

    public void setEditor(String editor) {
        if(validations.validateAuthorOrEditor(editor))
        this.editor = editor;
    }

    public String getVolume() {
        return volume;
    }

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

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) { this.organization = organization; }

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
        ConferenceProceedingsReference that = (ConferenceProceedingsReference) o;
        return Objects.equals(editor, that.editor) &&
                Objects.equals(volume, that.volume) &&
                Objects.equals(number, that.number) &&
                Objects.equals(series, that.series) &&
                Objects.equals(address, that.address) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(organization, that.organization) &&
                Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), editor, volume, number, series, address, publisher, organization, isbn);
    }
}