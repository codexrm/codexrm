package io.github.codexrm.projectreference.model.enums;

public enum ReferenceType {

    ARTICLE("Referencia de Artículo"),
    BOOK("Referencia de Libro"),
    BOOKSECTION("Referencia de Sección de libro"),
    BOOKLET("Referencia de Folleto"),
    CONFERENCEPROCEEDINGS("Referencia de Acta de congreso"),
    THESIS("Referencia de Tesis"),
    CONFERENCEPAPER("Referencia de Doc. de sesión"),
    WEBPAGE("Referencia de Página web");

    private final String description;

    ReferenceType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
