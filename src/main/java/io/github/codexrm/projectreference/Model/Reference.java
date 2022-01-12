package io.github.codexrm.projectreference.Model;

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.codexrm.projectreference.Enum.Month;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = ArticleReference.class, name = "article"),
    @Type(value = BookReference.class, name = "book"),
    @Type(value = BookSectionReference.class, name = "bookSeccion"),
    @Type(value = ThesisReference.class, name = "thesis"),
    @Type(value = BookLetReference.class, name = "bookLet"),
    @Type(value = ConferenceProceedingsReference.class, name = "proceedings")})

public class Reference {

  protected ArrayList<Integer> listAuthorId;
  protected String title;
  protected int year;
  protected Month month;
  protected String note;
  protected Integer id;
  protected AuthorLibrary authorLibrary;

  public Reference() {
    listAuthorId = new ArrayList<>();
  }

  @JsonIgnore
  public String getAuthor() {
    if (!listAuthorId.isEmpty()) {
      return authorLibrary.readAuthorsViewList(listAuthorId);
    } else {
      return "lastName,Name;lastName2,Name2";
    }
  }

  @JsonIgnore
  public void setAuthor(String authorLine) {
    if (authorLine != "lastName,Name;lastName2,Name2") {
      listAuthorId = authorLibrary.createAuthor(authorLine);
    }
  }

  public ArrayList<Integer> getListAuthorId() {
    return listAuthorId;
  }

  public void setListAuthorId(ArrayList<Integer> listAuthorId) {
    this.listAuthorId = listAuthorId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getYear() {
    return String.valueOf(year);
  }

  public void setYear(String year) {
    this.year = Integer.parseInt(year);
  }

  public Month getMonth() {
    return month;
  }

  public void setMonth(Month month) {
    this.month = month;
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

  public void addAuthorId(Integer authorId) {
    listAuthorId.add(authorId);
  }

}
