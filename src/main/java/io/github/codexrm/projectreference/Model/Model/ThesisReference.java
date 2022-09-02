package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.Enum.ThesisType;

import java.time.LocalDate;

public class ThesisReference extends Reference {

    private String school;
    private ThesisType type;
    private String address;

    public ThesisReference() {
        super();
        this.school = "";
        this.type = null;
        this.address = "";
    }

    public ThesisReference(String author, String title, LocalDate date, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String school, ThesisType type, String address) {
        super(author, title, date, note, id, isFromServer, isModified, isActive);
        this.school = school;
        this.type = type;
        this.address = address;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public ThesisType getType() {
        return type;
    }

    public void setType(ThesisType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThesisReference)) return false;
        if (!super.equals(o)) return false;
        ThesisReference that = (ThesisReference) o;
        return getSchool().equals(that.getSchool()) && getType() == that.getType() && getAddress().equals(that.getAddress());
    }
}