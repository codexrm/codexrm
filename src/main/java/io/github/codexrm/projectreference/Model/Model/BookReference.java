package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class BookReference extends Reference {

    protected String publisher;
    protected String volume;
    protected String series;
    protected String address;
    protected String edition;

    public BookReference() {
        super();
        this.publisher = "";
        this.volume = "";
        this.series = "";
        this.address = "";
        this.edition = "";
    }

    public BookReference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String publisher, String volume, String series, String address, String edition) {
        super(author, title, date, note, id, isFromServer, isModified, isActive);
        this.publisher = publisher;
        this.volume = volume;
        this.series = series;
        this.address = address;
        this.edition = edition;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookReference)) return false;
        if (!super.equals(o)) return false;
        BookReference that = (BookReference) o;
        return getPublisher().equals(that.getPublisher()) && getVolume().equals(that.getVolume()) && getSeries().equals(that.getSeries()) && getAddress().equals(that.getAddress()) && getEdition().equals(that.getEdition());
    }
}