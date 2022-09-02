package io.github.codexrm.projectreference.model.Enum;

public enum ThesisType {
    Masters("Masters"),
    phd("phd");
    private final String description;

    ThesisType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}