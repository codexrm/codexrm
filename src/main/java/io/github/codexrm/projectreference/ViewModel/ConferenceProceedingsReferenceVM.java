package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.model.ConferenceProceedingsReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConferenceProceedingsReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty volume;
    private StringProperty series;
    private StringProperty address;

    public ConferenceProceedingsReferenceVM() {
        super();
        createEmptyConferenceProceedingsReferenceVM();
    }

    public ConferenceProceedingsReferenceVM(ConferenceProceedingsReference conferenceProceedingsReference) {
        super(conferenceProceedingsReference.getId(), conferenceProceedingsReference.getAuthor(), conferenceProceedingsReference.getTitle(), conferenceProceedingsReference.getLocalDate(), conferenceProceedingsReference.getNote(), conferenceProceedingsReference.isFromServer(), conferenceProceedingsReference.isActive(), conferenceProceedingsReference.isModified());

        createEmptyConferenceProceedingsReferenceVM();

        setVolume(conferenceProceedingsReference.getVolume());
        setSeries(conferenceProceedingsReference.getSeries());
        setAddress(conferenceProceedingsReference.getAddress());
    }

    private void createEmptyConferenceProceedingsReferenceVM() {
        this.volume = new SimpleStringProperty();
        this.series = new SimpleStringProperty();
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

    public String getSeries() {
        return series.get();
    }

    public StringProperty seriesProperty() {
        return series;
    }

    public void setSeries(String series) {
        this.series.set(series);
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
        ConferenceProceedingsReference conferenceProceedingsReference = new ConferenceProceedingsReference();

        conferenceProceedingsReference.setAuthor(this.getAuthor());
        conferenceProceedingsReference.setTitle(this.getTitle());
        conferenceProceedingsReference.setLocalDate(this.getDate());
        conferenceProceedingsReference.setId(this.getId());
        conferenceProceedingsReference.setNote(this.getNote());
        conferenceProceedingsReference.setFromServer(this.isIsFromServer());
        conferenceProceedingsReference.setModified(this.isIsModified());
        conferenceProceedingsReference.setActive(this.isIsActive());

        conferenceProceedingsReference.setVolume(this.getVolume());
        conferenceProceedingsReference.setSeries(this.getSeries());
        conferenceProceedingsReference.setAddress(this.getAddress());

        return conferenceProceedingsReference;
    }
}
