package io.github.codexrm.projectreference.model.dto;

import java.time.LocalDate;

public class BookLetReferenceDTO extends ReferenceDTO{

    private String howpublished;
    private String address;

    public BookLetReferenceDTO() {}

    public BookLetReferenceDTO(String author, String title, LocalDate date, String note, Integer id, UserDTO userId, String howpublished, String address) {
        super(author, title, date, note, id, userId);
        this.howpublished = howpublished;
        this.address = address;
    }

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
