package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

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

    public ArticleReference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String journal, String volume, String number, String pages) {
        super(author, title, date, note, id, isFromServer, isModified, isActive);
        this.journal = journal;
        this.volume = volume;
        this.number = number;
        this.pages = pages;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleReference)) return false;
        if (!super.equals(o)) return false;
        ArticleReference that = (ArticleReference) o;
        return getJournal().equals(that.getJournal()) && getVolume().equals(that.getVolume()) && getNumber().equals(that.getNumber()) && getPages().equals(that.getPages());
    }
}