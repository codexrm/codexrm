package io.github.codexrm.projectreference.Model.Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

    protected ArrayList<Integer> authorIdList;
    protected String title;
    protected LocalDate date;
    protected String note;
    protected Integer id;

    public Reference() {
        authorIdList = new ArrayList<>();
        this.title = "";
        this.date = null;
        this.note = "";
    }

    public Reference(Integer id, ArrayList<Integer> authorIdList, String title, LocalDate date, String note) {
        this.authorIdList = new ArrayList<>();
        this.authorIdList = authorIdList;
        this.title = title;
        this.date = date;
        this.note = note;
        this.id = id;
    }

    public ArrayList<Integer> getAuthorIdList() {return authorIdList;}

    public void setAuthorIdList(ArrayList<Integer> authorIdList) {
        this.authorIdList = authorIdList;
    }

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

    public void addAuthorId(ArrayList<Integer> idAuthorlist) {
        for (Integer authorId : idAuthorlist) {
            authorIdList.add(authorId);
        }
    }
}