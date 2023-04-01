package io.github.codexrm.projectreference.model.dto;

import io.github.codexrm.projectreference.model.model.User;

public class WebPageReferenceDTO extends ReferenceDTO {

    private String author;
    private String howpublished;

    public WebPageReferenceDTO() {}

    public WebPageReferenceDTO(String title, String year, String month, String note, Integer id, User user, String author, String howpublished) {
        super(title, year, month, note, id, user);
        this.author = author;
        this.howpublished = howpublished;
    }

    public WebPageReferenceDTO(String title, String year, String month, String note, User user, String author, String howpublished) {
        super(title, year, month, note, user);
        this.author = author;
        this.howpublished = howpublished;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getHowpublished() { return howpublished; }

    public void setHowpublished(String howpublished) { this.howpublished = howpublished; }
}
