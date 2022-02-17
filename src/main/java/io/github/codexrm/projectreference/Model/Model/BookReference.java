package io.github.codexrm.projectreference.Model.Model;

import java.time.LocalDate;

public class BookReference extends Reference {

    protected String publisher;
    protected String volume;
    protected String series;
    protected String address;
    protected String edition;

    public BookReference() {
        super();
        this.publisher = " ";
        this.volume = " ";
        this.series = " ";
        this.address = " ";
        this.edition = " ";
    }

    public BookReference(Integer id, String author, String title, LocalDate date, String note) {
        super(id, author, title, date, note);
        this.publisher = "";
        this.volume = "";
        this.series = "";
        this.address = "";
        this.edition = "";
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}