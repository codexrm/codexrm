package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.Objects;

public class ConferencePaperReference extends Reference {

    private String volume;
    private String publisher;
    private String address;
    private String pages;

    public ConferencePaperReference() {
        super();
        this.volume = "";
        this.publisher = "";
        this.address = "";
        this.pages = "";
    }

    public ConferencePaperReference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String volume, String publisher, String address, String pages) {
        super(author, title, date, note, id, isFromServer, isModified, isActive);
        this.volume = volume;
        this.publisher = publisher;
        this.address = address;
        this.pages = pages;
    }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ConferencePaperReference that = (ConferencePaperReference) o;
        return volume.equals(that.volume) &&
                publisher.equals(that.publisher) &&
                address.equals(that.address) &&
                pages.equals(that.pages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), volume, publisher, address, pages);
    }
}
