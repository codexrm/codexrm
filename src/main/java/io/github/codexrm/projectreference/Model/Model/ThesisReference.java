package io.github.codexrm.projectreference.Model.Model;

import io.github.codexrm.projectreference.Model.Enum.ThesisType;

public class ThesisReference extends Reference {

    private String school;
    private ThesisType type;
    private String address;

    public ThesisReference() {
        super();
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