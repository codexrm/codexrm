package io.github.codexrm.projectreference.model.dto;

import io.github.codexrm.projectreference.model.model.User;

public class WebPageReferenceDTO extends ReferenceDTO {

    private String author;
    private String url;

    public WebPageReferenceDTO() {}

    public WebPageReferenceDTO(String title, String year, String month, String note, Integer id, User user, String author, String url) {
        super(title, year, month, note, id, user);
        this.author = author;
        this.url = url;
    }

    public WebPageReferenceDTO(String title, String year, String month, String note, User user, String author, String url) {
        super(title, year, month, note, user);
        this.author = author;
        this.url = url;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }
}
