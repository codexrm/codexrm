package io.github.codexrm.projectreference.model.dto;

import java.time.LocalDate;

public class ConferencePaperReferenceDTO extends ReferenceDTO{

    private String volume;
    private String publisher;
    private String address;
    private String pages;

    public ConferencePaperReferenceDTO() { }

    public ConferencePaperReferenceDTO(String author, String title, LocalDate date, String note, Integer id, UserDTO userId, String volume, String publisher, String address, String pages) {
        super(author, title, date, note, id, userId);
        this.volume = volume;
        this.publisher = publisher;
        this.address = address;
        this.pages = pages;
    }

    public String getVolume() { return volume; }

    public void setVolume(String volume) { this.volume = volume; }

    public String getPublisher() { return publisher; }

    public void setPublisher(String publisher) { this.publisher = publisher; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPages() { return pages; }

    public void setPages(String pages) { this.pages = pages; }
}
