package io.github.codexrm.projectreference.model.enums;

public enum BookSectionType {

    MATHESIS("Master´s thesis"),
    PHDTHESIS("PhD thesis"),
    CANDTHESIS("Candidate thesis"),
    TECHREPORT("Technical report"),
    RESREPORT("Research report"),
    SOFTWARE("Software"),
    AUDIOCD("Audio CD"),
    DataCD("Data CD");


    private final String description;

    BookSectionType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
