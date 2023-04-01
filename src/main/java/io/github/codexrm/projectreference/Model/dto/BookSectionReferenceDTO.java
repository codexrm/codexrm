package io.github.codexrm.projectreference.model.dto;

import io.github.codexrm.projectreference.model.model.User;

public class BookSectionReferenceDTO extends  BookReferenceDTO{

    private String chapter;
    private String pages;
    private String type;

    public BookSectionReferenceDTO() {}

    public BookSectionReferenceDTO(String title, String year, String month, String note, Integer id, User user, String author, String editor, String publisher, String volume, String series, String number, String address, String edition, String isbn, String chapter, String pages, String type) {
        super(title, year, month, note, id, user, author, editor, publisher, volume, series, number, address, edition, isbn);
        this.chapter = chapter;
        this.pages = pages;
        this.type = type;
    }

    public BookSectionReferenceDTO(String title, String year, String month, String note, User user, String author, String editor, String publisher, String volume, String series, String number, String address, String edition, String isbn, String chapter, String pages, String type) {
        super(title, year, month, note, user, author, editor, publisher, volume, series, number, address, edition, isbn);
        this.chapter = chapter;
        this.pages = pages;
        this.type = type;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}
