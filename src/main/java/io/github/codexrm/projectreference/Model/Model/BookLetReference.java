package io.github.codexrm.projectreference.model.model;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.utils.FieldValidations;

import java.util.Objects;

public class BookLetReference extends Reference {

    private String author;
    private String howpublished;
    private String address;

    private final FieldValidations validations = new FieldValidations();
    
    public BookLetReference() {
        super();
        this.author = "";
        this.howpublished = "";
        this.address = "";
    }

    public BookLetReference(String title, String year, Months month, String note, Integer id, boolean isFromServer, boolean isModified, boolean isActive, String author, String howpublished, String address) {
        super(title, year, month, note, id, isFromServer, isModified, isActive);
        this.howpublished = howpublished;

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

    public String getHowpublished() {
        return howpublished;
    }

    public void setHowpublished(String howpublished) {
        this.howpublished = howpublished;
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
        BookLetReference that = (BookLetReference) o;
        return Objects.equals(author, that.author) &&
                Objects.equals(howpublished, that.howpublished) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), author, howpublished, address);
    }
}