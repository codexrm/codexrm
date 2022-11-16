package io.github.codexrm.projectreference.model.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.Objects;

public class WebPageReference extends Reference {

    private LocalDate accessDate;
    private String url;

    public WebPageReference() {
        super();
        this.accessDate = null;
        this.url = "";
    }

    public WebPageReference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, LocalDate accessDate, String url) {
        super(author, title, date, note, id, isFromServer, isModified, isActive);
        this.accessDate = accessDate;
        this.url = url;
    }

    @JsonIgnore
    public LocalDate getAccessDateLocal() { return accessDate; }

    @JsonIgnore
    public void setAccessDateLocal(LocalDate accessDate) { this.accessDate = accessDate; }

    public String getAccessDate() {

        if (accessDate == null){
            return "0000-00-00";
        } else{
            return accessDate.toString();
        }
    }

    public void setAccessDate(String date) {

        if (date.equals("0000-00-00")) {
            this.accessDate = null;
        }else{
            String[] partDate = date.split("-", 3);
            this.accessDate = LocalDate.of(Integer.parseInt(partDate[0]),Integer.parseInt(partDate[1]),Integer.parseInt(partDate[2]));
        }
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WebPageReference that = (WebPageReference) o;
        return accessDate.equals(that.accessDate) &&
                url.equals(that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accessDate, url);
    }
}
