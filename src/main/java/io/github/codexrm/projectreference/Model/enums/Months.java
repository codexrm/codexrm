package io.github.codexrm.projectreference.model.enums;

public enum Months {

    jan("Enero"),
    feb("Febereo"),
    mar("Marzo"),
    apr("Abril"),
    may("Mayo"),
    jun("Junio"),
    jul("Julio"),
    aug("Agosto"),
    sep("Septiembre"),
    oct("Octubre"),
    nov("Noviembre"),
    dec("Diciembre");

    private final String description;

    Months(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
