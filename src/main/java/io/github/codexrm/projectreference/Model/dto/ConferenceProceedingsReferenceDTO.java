package io.github.codexrm.projectreference.model.dto;

import io.github.codexrm.projectreference.model.model.User;

public class ConferenceProceedingsReferenceDTO extends ReferenceDTO{

    private String editor;
    private String volume;
    private String number;
    private String series;
    private String address;
    private String publisher;
    private String organization;
    private String isbn;

    public ConferenceProceedingsReferenceDTO() {}

    public ConferenceProceedingsReferenceDTO(String title, String year, String month, String note, Integer id, User user, String editor, String volume, String number, String series, String address, String publisher, String organization, String isbn) {
        super(title, year, month, note, id, user);
        this.editor = editor;
        this.volume = volume;
        this.number = number;
        this.series = series;
        this.address = address;
        this.publisher = publisher;
        this.organization = organization;
        this.isbn = isbn;
    }

    public ConferenceProceedingsReferenceDTO(String title, String year, String month, String note, User user, String editor, String volume, String number, String series, String address, String publisher, String organization, String isbn) {
        super(title, year, month, note, user);
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

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getOrganization() { return organization; }

    public void setOrganization(String organization) { this.organization = organization; }

    public String getIsbn() { return isbn; }

    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

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
}
