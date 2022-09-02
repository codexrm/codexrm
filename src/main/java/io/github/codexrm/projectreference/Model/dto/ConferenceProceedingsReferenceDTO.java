package io.github.codexrm.projectreference.model.dto;

import java.time.LocalDate;

public class ConferenceProceedingsReferenceDTO extends ReferenceDTO{

    private String volume;
    private String series;
    private String address;

    public ConferenceProceedingsReferenceDTO() {}

    public ConferenceProceedingsReferenceDTO(String author, String title, LocalDate date, String note, Integer id, UserDTO userId, String volume, String series, String address) {
        super(author, title, date, note, id, userId);
        this.volume = volume;
        this.series = series;
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
}
