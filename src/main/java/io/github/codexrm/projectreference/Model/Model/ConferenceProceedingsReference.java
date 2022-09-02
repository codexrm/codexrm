package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.Objects;

public class ConferenceProceedingsReference extends Reference {

    private String volume;
    private String series;
    private String address;

    public ConferenceProceedingsReference() {
        super();
        this.volume = "";
        this.series = "";
        this.address = "";
    }

    public ConferenceProceedingsReference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String volume, String serie, String address) {
        super(author, title, date, note, id, isFromServer, isModified, isActive);
        this.volume = volume;
        this.series = serie;
        this.address = address;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConferenceProceedingsReference)) return false;
        if (!super.equals(o)) return false;
        ConferenceProceedingsReference that = (ConferenceProceedingsReference) o;
        return getVolume().equals(that.getVolume()) && getSeries().equals(that.getSeries()) && getAddress().equals(that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVolume(), getSeries(), getAddress());
    }
}