package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.Objects;

public class BookLetReference extends Reference {

    private String howpublished;
    private String address;

    public BookLetReference() {
        super();
        this.howpublished = "";
        this.address = "";
    }

    public BookLetReference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String howpublished, String address) {
        super(author, title, date, note, id, isFromServer, isModified, isActive);
        this.howpublished = howpublished;
        this.address = address;
    }

    public String getHowpublished() {
        return howpublished;
    }

    public void setHowpublished(String howpublished) {
        this.howpublished = howpublished;
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
        return getHowpublished().equals(that.getHowpublished()) && getAddress().equals(that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHowpublished(), getAddress());
    }
}