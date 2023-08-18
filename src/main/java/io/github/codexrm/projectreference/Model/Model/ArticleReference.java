package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.utils.FieldValidations;

import java.util.Objects;

public class ArticleReference extends Reference {

    private String author;
    private String journal;
    private String volume;
    private String number;
    private String pages;
    private String issn;

    private final FieldValidations validations = new FieldValidations();

    public ArticleReference() {
        super();
        this.author = "";
        this.journal = "";
        this.volume = "";
        this.number = "";
        this.pages = "";
        this.issn = "";
    }

    public ArticleReference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String author, String journal,
                            String volume, String number, String pages, String issn) {
        super(title, year, month, note, id, isFromServer, isModified, isActive);
        this.journal = journal;

        if(validations.validateAuthorOrEditor(author))
            this.author = author;

        if(validations.isNumber(volume))
            this.volume = volume;

        if(validations.validateNumber(number))
            this.number = number;

        if(validations.validatePages(pages))
            this.pages = pages;

        if(validations.validateIssn(issn))
            this.issn = issn;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
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
        if(validations.isNumber(volume))
        this.volume = volume;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if(validations.validateNumber(number))
        this.number = number;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        if(validations.validatePages(pages))
        this.pages = pages;
    }

    public String getIssn() { return issn; }

    public void setIssn(String issn) {
        if(validations.validateIssn(issn))
        this.issn = issn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArticleReference that = (ArticleReference) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(journal, that.journal) &&
                Objects.equals(volume, that.volume) &&
                Objects.equals(number, that.number) &&
                Objects.equals(pages, that.pages) &&
                Objects.equals(issn, that.issn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, journal, volume, number, pages, issn);
    }
}