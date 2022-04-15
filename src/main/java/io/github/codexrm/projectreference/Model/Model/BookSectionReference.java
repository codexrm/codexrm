package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookSectionReference extends BookReference {

    private String chapter;
    private String pages;

    public BookSectionReference() {
        super();
        this.chapter = "";
        this.pages = "";
    }

    public BookSectionReference(Integer id, ArrayList<Integer> authorIdList, String title, LocalDate date, String note) {
        super(id, authorIdList, title, date, note);
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