package io.github.codexrm.projectreference.Model.Model;

import java.time.LocalDate;

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

    public ConferenceProceedingsReference(Integer id, String author, String title, LocalDate date, String note) {
        super(id, author, title, date, note);
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