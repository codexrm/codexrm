package io.github.codexrm.projectreference.model.enums;

public enum ThesisType {

    MASTERS("Maestría"),
    PHD("Doctorado");

    private final String description;

    ThesisType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}