package io.github.codexrm.projectreference.model.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.utils.FieldValidations;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@Type(value = ArticleReference.class, name = "article"),
        @Type(value = BookReference.class, name = "book"),
        @Type(value = BookSectionReference.class, name = "bookSection"),
        @Type(value = ThesisReference.class, name = "thesis"),
        @Type(value = BookLetReference.class, name = "bookLet"),
        @Type(value = ConferencePaperReference.class, name = "paper"),
        @Type(value = WebPageReference.class, name = "webPage"),
        @Type(value = ConferenceProceedingsReference.class, name = "proceedings")})

public class Reference {

    protected String title;
    protected String year;
    protected Months month;
    protected String note;
    protected Integer id;
    protected boolean isFromServer;
    protected boolean isModified ;
    protected boolean isActive ;

    private final FieldValidations validations = new FieldValidations();

    public Reference() {
        this.title = "";
        this.year = "";
        this.month = null;
        this.note = "";
    }

    public Reference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive) {
        this.title = title;
        this.month = month;
        this.note = note;
        this.id = id;
        this.isFromServer = isFromServer;
        this.isModified = isModified;
        this.isActive = isActive;

        if(validations.validateYear(year))
        this.year = year;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getYear() { return year; }

    public void setYear(String year) {
        if(validations.validateYear(year))
        this.year = year;
    }

    public Months getMonth() { return month; }

    public void setMonth(Months month) { this.month = month; }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() { return id; }

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
        if (o == null || getClass() != o.getClass()) return false;
        Reference reference = (Reference) o;
        return isFromServer == reference.isFromServer &&
                isModified == reference.isModified &&
                isActive == reference.isActive &&
                Objects.equals(title, reference.title) &&
                Objects.equals(year, reference.year) &&
                Objects.equals(month, reference.month) &&
                Objects.equals(note, reference.note) &&
                Objects.equals(id, reference.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, month, note, id, isFromServer, isModified, isActive);
    }
}
