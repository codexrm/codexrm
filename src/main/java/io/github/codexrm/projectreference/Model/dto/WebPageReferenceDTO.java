package io.github.codexrm.projectreference.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;

public class WebPageReferenceDTO extends ReferenceDTO {

    private LocalDate accessDate;
    private String url;

    public WebPageReferenceDTO() {}

    public WebPageReferenceDTO(String author, String title, LocalDate date, String note, Integer id, UserDTO userId, LocalDate accessDate, String url) {
        super(author, title, date, note, id, userId);
        this.accessDate = accessDate;
        this.url = url;
    }

    @JsonIgnore
    public LocalDate getAccessDateLocal() { return accessDate; }

    @JsonIgnore
    public void setAccessDateLocal(LocalDate accessDate) { this.accessDate = accessDate; }

    public String getAccessDate() {
        if (accessDate == null) {
            return "0000-00-00";
        } else {
            return accessDate.toString();
        }
    }

    public void setAccessDate(String date) {
        if (date.equals("0000-00-00")) {
            this.accessDate = null;
        } else {
            String[] partDate = date.split("-", 3);
            this.accessDate = LocalDate.of(Integer.parseInt(partDate[0]), Integer.parseInt(partDate[1]), Integer.parseInt(partDate[2]));
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
