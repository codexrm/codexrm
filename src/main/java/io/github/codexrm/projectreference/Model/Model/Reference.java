package io.github.codexrm.projectreference.model.model;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = ArticleReference.class, name = "article"),
        @Type(value = BookReference.class, name = "book"),
        @Type(value = BookSectionReference.class, name = "bookSection"),
        @Type(value = ThesisReference.class, name = "thesis"),
        @Type(value = BookLetReference.class, name = "bookLet"),
        @Type(value = ConferenceProceedingsReference.class, name = "proceedings")})

public class Reference {

    protected String author;
    protected String title;
    protected LocalDate date;
    protected String note;
    protected Integer id;
    protected boolean isFromServer;
    protected boolean isModified ;
    protected boolean isActive ;

    public Reference() {
        this.author = "";
        this.title = "";
        this.date = null;
        this.note = "";
    }

    public Reference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.note = note;
        this.id = id;
        this.isFromServer = isFromServer;
        this.isModified = isModified;
        this.isActive = isActive;
    }

    public String getAuthor() {return author;}

    public void setAuthor(String author) {this.author = author;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonIgnore
    public LocalDate getLocalDate() {
        return date;
    }

    @JsonIgnore
    public void setLocalDate(LocalDate date) {
        this.date = date;
    }

    public String getDate() {
        if (date == null){
        return "0000-00-00";
        } else{
            return date.toString();
        }
    }

    public void setDate(String date) {
        if (date.equals("0000-00-00")) {
           this.date = null;
        }else{
            String[] partDate = date.split("-", 3);
            this.date = LocalDate.of(Integer.parseInt(partDate[0]),Integer.parseInt(partDate[1]),Integer.parseInt(partDate[2]));
        }
    }
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isFromServer() {return isFromServer;}

    public void setFromServer(boolean fromServer) {isFromServer = fromServer;}

    public boolean isModified() {return isModified;}

    public void setModified(boolean modified) {isModified = modified;}

    public boolean isActive() {return isActive;}

    public void setActive(boolean active) {isActive = active;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reference)) return false;
        Reference reference = (Reference) o;
        return getAuthor().equals(reference.getAuthor()) && getTitle().equals(reference.getTitle()) && getDate().equals(reference.getDate()) && getNote().equals(reference.getNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getTitle(), getDate(), getNote());
    }
}
