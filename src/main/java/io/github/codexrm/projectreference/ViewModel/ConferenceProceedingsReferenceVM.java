package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.model.model.ConferenceProceedingsReference;
import javafx.beans.property.*;

public class ConferenceProceedingsReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty editor;
    private StringProperty volume;
    private StringProperty number;
    private StringProperty series;
    private StringProperty address;
    private StringProperty publisher;
    private StringProperty organization;
    private StringProperty isbn;

    public ConferenceProceedingsReferenceVM() {
        super();
        createEmptyConferenceProceedingsReferenceVM();
    }

    public ConferenceProceedingsReferenceVM(ConferenceProceedingsReference conferenceProceedingsReference) {
        super(conferenceProceedingsReference.getId(), conferenceProceedingsReference.getTitle(), conferenceProceedingsReference.getYear(), conferenceProceedingsReference.getMonth(),
                conferenceProceedingsReference.getNote(), conferenceProceedingsReference.isFromServer(), conferenceProceedingsReference.isActive(), conferenceProceedingsReference.isModified());

        createEmptyConferenceProceedingsReferenceVM();

        setEditor(conferenceProceedingsReference.getEditor());
        setVolume(conferenceProceedingsReference.getVolume());
        setNumber(conferenceProceedingsReference.getNumber());
        setSeries(conferenceProceedingsReference.getSeries());
        setAddress(conferenceProceedingsReference.getAddress());
        setPublisher(conferenceProceedingsReference.getPublisher());
        setOrganization(conferenceProceedingsReference.getOrganization());
        setIsbn(conferenceProceedingsReference.getIsbn());
    }

    private void createEmptyConferenceProceedingsReferenceVM() {

        this.editor = new SimpleStringProperty();
        this.volume = new SimpleStringProperty();
        this.number = new SimpleStringProperty();
        this.series = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.publisher = new SimpleStringProperty();
        this.organization = new SimpleStringProperty();
        this.isbn = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.CONFERENCEPROCEEDINGS);
    }

    public String getEditor() { return editor.get(); }

    public StringProperty editorProperty() { return editor; }

    public void setEditor(String editor) { this.editor.set(editor); }

    public String getVolume() {
        return volume.get();
    }

    public StringProperty volumeProperty() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume.set(volume);
    }

    public String getNumber() { return number.get(); }

    public StringProperty numberProperty() { return number; }

    public void setNumber(String number) { this.number.set(number); }

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

    public String getPublisher() { return publisher.get(); }

    public StringProperty publisherProperty() { return publisher; }

    public void setPublisher(String publisher) { this.publisher.set(publisher); }

    public String getOrganization() { return organization.get(); }

    public StringProperty organizationProperty() { return organization; }

    public void setOrganization(String organization) { this.organization.set(organization); }

    public String getIsbn() { return isbn.get(); }

    public StringProperty isbnProperty() { return isbn; }

    public void setIsbn(String isbn) { this.isbn.set(isbn); }

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

        conferenceProceedingsReference.setTitle(this.getTitle());
        conferenceProceedingsReference.setYear(this.getYear());
        conferenceProceedingsReference.setMonth(this.getMonth());
        conferenceProceedingsReference.setId(this.getId());
        conferenceProceedingsReference.setNote(this.getNote());
        conferenceProceedingsReference.setFromServer(this.isIsFromServer());
        conferenceProceedingsReference.setModified(this.isIsModified());
        conferenceProceedingsReference.setActive(this.isIsActive());

        conferenceProceedingsReference.setEditor(this.getEditor());
        conferenceProceedingsReference.setVolume(this.getVolume());
        conferenceProceedingsReference.setNumber(this.getNumber());
        conferenceProceedingsReference.setSeries(this.getSeries());
        conferenceProceedingsReference.setAddress(this.getAddress());
        conferenceProceedingsReference.setPublisher(this.getPublisher());
        conferenceProceedingsReference.setOrganization(this.getOrganization());
        conferenceProceedingsReference.setIsbn(this.getIsbn());

        return conferenceProceedingsReference;
    }
}
