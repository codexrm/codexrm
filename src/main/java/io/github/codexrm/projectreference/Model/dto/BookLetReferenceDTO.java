package io.github.codexrm.projectreference.model.dto;

import io.github.codexrm.projectreference.model.model.User;

public class BookLetReferenceDTO extends ReferenceDTO{

    private String author;
    private String howpublished;
    private String address;

    public BookLetReferenceDTO() {}

    public BookLetReferenceDTO(String title, String year, String month, String note, Integer id, User user, String author, String howpublished, String address) {
        super(title, year, month, note, id, user);
        this.author = author;
        this.howpublished = howpublished;
        this.address = address;
    }

    public BookLetReferenceDTO(String title, String year, String month, String note, User user, String author, String howpublished, String address) {
        super(title, year, month, note, user);
        this.author = author;
        this.howpublished = howpublished;
        this.address = address;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) { this.author = author; }

    public String getHowpublished() {
        return howpublished;
    }

    public void setHowpublished(String howpublished) {this.howpublished = howpublished;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
