package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.*;
import io.github.codexrm.projectreference.model.model.ThesisReference;
import javafx.beans.property.*;

public class ThesisReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty author;
    private StringProperty school;
    private ObjectProperty<ThesisType> type;
    private StringProperty address;

    public ThesisReferenceVM() {
        super();
        createEmptyThesisReferenceVM();
    }

    public ThesisReferenceVM(ThesisReference thesisReference) {
        super(thesisReference.getId(), thesisReference.getTitle(), thesisReference.getYear(), thesisReference.getMonth(), thesisReference.getNote(), thesisReference.isFromServer(), thesisReference.isActive(), thesisReference.isModified());

        createEmptyThesisReferenceVM();

        setAuthor(thesisReference.getAuthor());
        setSchool(thesisReference.getSchool());
        setType(thesisReference.getType());
        setAddress(thesisReference.getAddress());
    }

    private void createEmptyThesisReferenceVM() {

        this.author = new SimpleStringProperty();
        this.school = new SimpleStringProperty();
        this.type = new SimpleObjectProperty<>();
        this.address = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.THESIS);
    }

    public String getAuthor() { return author.get(); }

    public StringProperty authorProperty() { return author; }

    public void setAuthor(String author) { this.author.set(author); }

    public String getSchool() {
        return school.get();
    }

    public StringProperty schoolProperty() {
        return school;
    }

    public void setSchool(String school) {
        this.school.set(school);
    }

    public ThesisType getType() {
        return type.get();
    }

    public Property<ThesisType> typeProperty() {
        return type;
    }

    public void setType(ThesisType type) {
        this.type.set(type);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public ReferenceType getReferenceType() {
        return referenceType.get();
    }

    public void setReferenceType(final ReferenceType referenceType) {
        this.referenceType.set(referenceType);
    }

    public ObjectProperty<ReferenceType> referenceTypeProperty() {
        return referenceType;
    }

    @Override
    public ThesisReference toModel() {

        ThesisReference thesisReference = new ThesisReference();

        thesisReference.setTitle(this.getTitle());
        thesisReference.setYear(this.getYear());
        thesisReference.setMonth(this.getMonth());
        thesisReference.setId(this.getId());
        thesisReference.setNote(this.getNote());
        thesisReference.setFromServer(this.isIsFromServer());
        thesisReference.setModified(this.isIsModified());
        thesisReference.setActive(this.isIsActive());

        thesisReference.setAuthor(this.getAuthor());
        thesisReference.setSchool(this.getSchool());
        thesisReference.setType(this.getType());
        thesisReference.setAddress(this.getAddress());

        return thesisReference;
    }
}
