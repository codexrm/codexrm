package io.github.codexrm.projectreference.Model.Model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = ArticleReference.class, name = "article"),
        @Type(value = BookReference.class, name = "book"),
        @Type(value = BookSectionReference.class, name = "bookSeccion"),
        @Type(value = ThesisReference.class, name = "thesis"),
        @Type(value = BookLetReference.class, name = "bookLet"),
        @Type(value = ConferenceProceedingsReference.class, name = "proceedings")})

public class Reference {

    protected ArrayList<Integer> authorIdList;
    protected String title;
    protected LocalDate date;
    protected String note;
    protected Integer id;
    protected AuthorLibrary authorLibrary;

    public Reference() {
        authorIdList = new ArrayList<>();
    }

    @JsonIgnore
    public String getAuthor() {
        if (!authorIdList.isEmpty()) {
            return authorLibrary.readAuthorsViewList(authorIdList);
        } else {
            return "lastName,Name;lastName2,Name2";
        }
    }

    @JsonIgnore
    public void setAuthor(String authorLine) {
        if (authorLine != "lastName,Name;lastName2,Name2") {
            authorIdList = authorLibrary.createAuthor(authorLine);
        }
    }

    public ArrayList<Integer> getAuthorIdList() {
        return authorIdList;
    }

    public void setAuthorIdList(ArrayList<Integer> authorIdList) {
        this.authorIdList = authorIdList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    @JsonIgnore
    public AuthorLibrary getAuthorLibrary() {
        return authorLibrary;
    }

    @JsonIgnore
    public void setAuthorLibrary(AuthorLibrary authorLibrary) {
        this.authorLibrary = authorLibrary;
    }

    public void addAuthorId(ArrayList<Integer> idAuthorlist) {
        for (Integer authorId : idAuthorlist) {
            authorIdList.add(authorId);
        }
    }
}