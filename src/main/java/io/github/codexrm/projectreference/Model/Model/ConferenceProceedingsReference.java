package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class ConferenceProceedingsReference extends Reference {

    private String volume;
    private String serie;
    private String address;

    public ConferenceProceedingsReference() {
        super();
        this.volume = "";
        this.serie = "";
        this.address = "";
    }

    public ConferenceProceedingsReference(Integer id, ArrayList<Integer> authorIdList, String title, LocalDate date, String note) {
        super(id, authorIdList, title, date, note);
        this.volume = "";
        this.serie = "";
        this.address = "";
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}