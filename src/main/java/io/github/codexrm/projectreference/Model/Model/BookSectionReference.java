package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.Objects;

public class BookSectionReference extends BookReference {

    private String chapter;
    private String pages;

    public BookSectionReference() {
        super();
        this.chapter = "";
        this.pages = "";
    }

    public BookSectionReference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String publisher, String volume, String series, String address, String edition, String chapter, String pages) {
        super(author, title, date, note, id, isFromServer, isModified, isActive, publisher, volume, series, address, edition);
        this.chapter = chapter;
        this.pages = pages;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookSectionReference)) return false;
        if (!super.equals(o)) return false;
        BookSectionReference that = (BookSectionReference) o;
        return getChapter().equals(that.getChapter()) && getPages().equals(that.getPages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getChapter(), getPages());
    }
}