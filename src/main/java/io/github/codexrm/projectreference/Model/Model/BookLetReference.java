package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.Objects;

public class BookLetReference extends Reference {

    private String howpublisher;
    private String address;

    public BookLetReference() {
        super();
        this.howpublisher = "";
        this.address = "";
    }

    public BookLetReference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String howpublished, String address) {
        super(author, title, date, note, id, isFromServer, isModified, isActive);
        this.howpublisher = howpublished;
        this.address = address;
    }

    public String getHowpublisher() {
        return howpublisher;
    }

    public void setHowpublisher(String howpublisher) {
        this.howpublisher = howpublisher;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookLetReference)) return false;
        if (!super.equals(o)) return false;
        BookLetReference that = (BookLetReference) o;
        return getHowpublisher().equals(that.getHowpublisher()) && getAddress().equals(that.getAddress());
    }
}