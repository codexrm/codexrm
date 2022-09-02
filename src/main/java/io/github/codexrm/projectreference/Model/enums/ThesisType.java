package io.github.codexrm.projectreference.model.enums;

public enum ThesisType {

    MASTERS("Masters"),
    PHD("phd");

    private final String description;

    ThesisType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}