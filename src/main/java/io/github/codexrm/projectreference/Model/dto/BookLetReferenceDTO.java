package io.github.codexrm.projectreference.model.dto;

import java.time.LocalDate;

public class BookLetReferenceDTO extends ReferenceDTO{

    private String howpublisher;
    private String address;

    public BookLetReferenceDTO() {}

    public BookLetReferenceDTO(String author, String title, LocalDate date, String note, Integer id, UserDTO userId, String howpublisher, String address) {
        super(author, title, date, note, id, userId);
        this.howpublisher = howpublisher;
        this.address = address;
    }

    public String getHowpublisher() {
        return howpublisher;
    }

    public void setHowpublisher(String howpublisher) {this.howpublisher = howpublisher;}

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
