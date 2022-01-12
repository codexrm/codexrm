package io.github.codexrm.projectreference.Model;

public class Author {

  private DataAuthor dataAuthor;
  private Integer id;

  public Author() {
    super();
  }

  public Author(DataAuthor dataAuthor, Integer id) {
    super();
    this.dataAuthor = dataAuthor;
    this.id = id;
  }

  public DataAuthor getDataAuthor() {
    return dataAuthor;
  }

  public void setDataAuthor(DataAuthor dataAuthor) {
    this.dataAuthor = dataAuthor;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
