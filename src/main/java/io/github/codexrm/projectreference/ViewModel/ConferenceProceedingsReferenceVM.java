package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.model.AuthorLibrary;
import io.github.codexrm.projectreference.model.model.ConferenceProceedingsReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ConferenceProceedingsReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty volume;
    private StringProperty serie;
    private StringProperty address;

    public ConferenceProceedingsReferenceVM() {
        super();
        createEmptyConferenceProceedingsReferenceVM();
    }

    public ConferenceProceedingsReferenceVM(final int id, final ArrayList<Integer> authorIdList, final String title, LocalDate date, String note,
                                            final String volume, final String serie, final String address, final AuthorLibrary authorLibrary) {
        super(id, authorIdList, title, date, note, authorLibrary);

        createEmptyConferenceProceedingsReferenceVM();

        setVolume(volume);
        setSerie(serie);
        setAddress(address);
    }

    public ConferenceProceedingsReferenceVM(ConferenceProceedingsReference conferenceProceedingsReference, AuthorLibrary authorLibrary) {
        super(conferenceProceedingsReference.getId(), conferenceProceedingsReference.getAuthorIdList(), conferenceProceedingsReference.getTitle(), conferenceProceedingsReference.getLocalDate(), conferenceProceedingsReference.getNote(),authorLibrary);

        createEmptyConferenceProceedingsReferenceVM();

        setVolume(conferenceProceedingsReference.getVolume());
        setSerie(conferenceProceedingsReference.getSerie());
        setAddress(conferenceProceedingsReference.getAddress());
    }

    private void createEmptyConferenceProceedingsReferenceVM() {
        this.volume = new SimpleStringProperty();
        this.serie = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.CONFERENCEPROCEEDINGS);
    }

    public String getVolume() {
        return volume.get();
    }

    public StringProperty volumeProperty() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume.set(volume);
    }

    public String getSerie() {
        return serie.get();
    }

    public StringProperty serieProperty() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie.set(serie);
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
    public ConferenceProceedingsReference toModel() {
        ConferenceProceedingsReference conferenceProceedingsReference = new ConferenceProceedingsReference(this.getId(), this.getAuthorIdList(), this.getTitle(), this.getDate(), this.getNote());
        conferenceProceedingsReference.setVolume(this.getVolume());
        conferenceProceedingsReference.setSerie(this.getSerie());
        conferenceProceedingsReference.setAddress(this.getAddress());

        return conferenceProceedingsReference;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ConferenceProceedingsReferenceVM)) return false;
        if (!super.equals(o)) return false;
        final ConferenceProceedingsReferenceVM that = (ConferenceProceedingsReferenceVM) o;
        return getReferenceType().equals(that.getReferenceType()) &&
                Objects.equals(getVolume(), that.getVolume()) &&
                Objects.equals(getSerie(), that.getSerie()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getReferenceType(), getVolume(), getSerie(), getAddress());
    }
}
