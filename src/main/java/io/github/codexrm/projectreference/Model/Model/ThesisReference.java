package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.enums.ThesisType;
import io.github.codexrm.projectreference.model.utils.FieldValidations;

import java.util.Objects;

public class ThesisReference extends Reference {

    private String author;
    private String school;
    private ThesisType type;
    private String address;

    private final FieldValidations validations = new FieldValidations();

    public ThesisReference() {
        super();
        this.author = "";
        this.school = "";
        this.type = null;
        this.address = "";
    }

    public ThesisReference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String author, String school, ThesisType type, String address) {
        super(title, year, month, note, id, isFromServer, isModified, isActive);
        this.school = school;
        this.type = type;

        if(validations.validateAuthorOrEditor(author))
            this.author = author;

        if(validations.validateAddress(address))
            this.address = address;
    }

    public String getAuthor() { return author; }

    public void setAuthor(String author) {
        if(validations.validateAuthorOrEditor(author))
        this.author = author;
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
        if(validations.validateAddress(address))
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ThesisReference that = (ThesisReference) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(school, that.school) &&
                type == that.type &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, school, type, address);
    }
}