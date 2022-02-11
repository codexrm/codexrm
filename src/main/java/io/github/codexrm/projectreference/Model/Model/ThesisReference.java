package io.github.codexrm.projectreference.Model.Model;

import io.github.codexrm.projectreference.Model.Enum.ThesisType;

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

    public ThesisReference(Integer id, String author, String title, LocalDate date, String note) {
        super(id, author, title, date, note);
        this.school = "";
        this.type = null;
        this.address = "";
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
}