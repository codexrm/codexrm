package io.github.codexrm.projectreference.ViewModel;

public enum ReferenceType {

    ARTICLE("Article"),
    BOOK("Book"),
    BOOKSECTION("Book Section"),
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
