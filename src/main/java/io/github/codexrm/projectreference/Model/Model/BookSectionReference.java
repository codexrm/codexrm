package io.github.codexrm.projectreference.Model.Model;

public class BookSectionReference extends BookReference {

    private String chapter;
    private String pages;

    public BookSectionReference() {
        super();
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
}