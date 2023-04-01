package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.Months;

import java.util.Objects;

public class WebPageReference extends Reference {

    private String author;
    private String url;

    public WebPageReference() {
        super();
        this.author = "";
        this.url = "";
    }

    public WebPageReference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String author, String url) {
        super(title, year, month, note, id, isFromServer, isModified, isActive);
        this.author = author;
        this.url = url;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WebPageReference that = (WebPageReference) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, url);
    }
}