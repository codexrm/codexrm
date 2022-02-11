package io.github.codexrm.projectreference.ViewModel;

import io.github.codexrm.projectreference.Model.Enum.ThesisType;
import io.github.codexrm.projectreference.Model.Model.ThesisReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Objects;

public class ThesisReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty school;
    private ObjectProperty<ThesisType> type;
    private StringProperty address;

    public ThesisReferenceVM() {
        super();
        createEmptyThesisReferenceVM();
    }

    public ThesisReferenceVM(final int id, final String author, final String title, LocalDate date, String note,
                             final String school, final ThesisType type, final String address) {
        super(id, author, title, date, note);

        createEmptyThesisReferenceVM();

        setSchool(school);
        setType(type);
        setAddress(address);
    }

    public ThesisReferenceVM(ThesisReference thesisReference) {
        super(thesisReference.getId(), thesisReference.getAuthor(), thesisReference.getTitle(), thesisReference.getDate(), thesisReference.getNote());

        createEmptyThesisReferenceVM();

        setSchool(thesisReference.getSchool());
        setType(thesisReference.getType());
        setAddress(thesisReference.getAddress());
    }

    private void createEmptyThesisReferenceVM() {
        this.school = new SimpleStringProperty();
        this.type = new SimpleObjectProperty<>();
        this.address = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.THESIS);
    }

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

    public ObjectProperty<ThesisType> typeProperty() {
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
        ThesisReference thesisReference = new ThesisReference(this.getId(), this.getAuthor(), this.getTitle(), this.getDate(), this.getNote());
        thesisReference.setSchool(this.getSchool());
        thesisReference.setType(this.getType());
        thesisReference.setAddress(this.getAddress());

        return thesisReference;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ThesisReferenceVM)) return false;
        if (!super.equals(o)) return false;
        final ThesisReferenceVM that = (ThesisReferenceVM) o;
        return getReferenceType().equals(that.getReferenceType()) &&
                Objects.equals(getSchool(), that.getSchool()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getReferenceType(), getSchool(), getType(), getAddress());
    }
}
