package io.github.codexrm.projectreference.Model.Model;

import java.time.LocalDate;

public class BookSectionReference extends BookReference {

    private String chapter;
    private String pages;

    public BookSectionReference() {
        super();
        this.chapter = "";
        this.pages = "";
    }

    public BookSectionReference(Integer id, String author, String title, LocalDate date, String note, String publisher, String volume, String series, String address, String edition) {
        super(id, author, title, date, note);
        this.chapter = "";
        this.pages = "";
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
}