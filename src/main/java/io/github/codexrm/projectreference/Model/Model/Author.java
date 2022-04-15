package io.github.codexrm.projectreference.model.model;

public class Author {

    private String author;
    private Integer id;

    public Author() {super();}

    public Author(String author, Integer id) {
        this.author = author;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}