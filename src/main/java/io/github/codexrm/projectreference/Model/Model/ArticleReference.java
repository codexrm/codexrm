package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ArticleReference extends Reference {

    private String journal;
    private String volume;
    private String number;
    private String pages;

    public ArticleReference() {
        super();
        this.journal = "";
        this.volume = "";
        this.number = "";
        this.pages = "";
    }

    public ArticleReference(Integer id, String author, String title, LocalDate date, String note) {
        super(id, author, title, date, note);
        this.journal = "";
        this.volume = "";
        this.number = "";
        this.pages = "";
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}