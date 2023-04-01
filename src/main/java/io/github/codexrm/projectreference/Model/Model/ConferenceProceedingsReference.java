package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.Months;

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

    public ConferenceProceedingsReference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String editor, String volume, String number, String series, String address, String publisher, String organization, String isbn) {
        super(title, year, month, note, id, isFromServer, isModified, isActive);
        this.editor = editor;
        this.volume = volume;
        this.number = number;
        this.series = series;
        this.address = address;
        this.publisher = publisher;
        this.organization = organization;
        this.isbn = isbn;
    }

    public String getEditor() { return editor; }

    public void setEditor(String editor) { this.editor = editor; }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) { this.organization = organization; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

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