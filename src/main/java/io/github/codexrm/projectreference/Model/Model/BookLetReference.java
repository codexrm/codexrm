package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class BookLetReference extends Reference {

    private String howpublished;
    private String address;

    public BookLetReference() {
        super();
        this.howpublished = "";
        this.address = "";
    }

    public BookLetReference(Integer id, ArrayList<Integer> authorIdList, String title, LocalDate date, String note) {
        super(id, authorIdList, title, date, note);
        this.howpublished = "";
        this.address = "";
    }

    public String getHowpublished() {
        return howpublished;
    }

    public void setHowpublished(String howpublished) {
        this.howpublished = howpublished;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}