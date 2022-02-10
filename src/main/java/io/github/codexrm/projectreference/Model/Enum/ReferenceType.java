package io.github.codexrm.projectreference.Model.Enum;

public enum ReferenceType {
    ARTICLE("Article"),
    BOOK("Book"),
    BOOKSECCION("Book Seccion"),
    BOOKLET("Book Let"),
    CONFERENCEPROCEEDINGS("Conference Proceedings"),
    THESIS("Thesis");

    private final String description;

    ReferenceType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}