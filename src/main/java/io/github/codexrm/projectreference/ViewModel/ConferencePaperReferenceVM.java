package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.model.ConferencePaperReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConferencePaperReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty volume;
    private StringProperty publisher;
    private StringProperty address;
    private StringProperty pages;

    public ConferencePaperReferenceVM() {
        super();
        createEmptyConferencePaperReferenceVM();
    }

    public ConferencePaperReferenceVM(ConferencePaperReference conferencePaperReference) {
        super(conferencePaperReference.getId(),conferencePaperReference.getAuthor(), conferencePaperReference.getTitle(), conferencePaperReference.getLocalDate(), conferencePaperReference.getNote(), conferencePaperReference.isFromServer(), conferencePaperReference.isActive(), conferencePaperReference.isModified());

        createEmptyConferencePaperReferenceVM();

        setVolume(conferencePaperReference.getVolume());
        setPublisher(conferencePaperReference.getPublisher());
        setAddress(conferencePaperReference.getAddress());
        setPages(conferencePaperReference.getPages());
    }

    private void createEmptyConferencePaperReferenceVM() {
        this.volume = new SimpleStringProperty();
        this.publisher = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.pages = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.CONFERENCEPAPER);
    }

    public ReferenceType getReferenceType() { return referenceType.get(); }

    public ObjectProperty<ReferenceType> referenceTypeProperty() { return referenceType; }

    public void setReferenceType(ReferenceType referenceType) { this.referenceType.set(referenceType); }

    public String getVolume() { return volume.get(); }

    public StringProperty volumeProperty() { return volume; }

    public void setVolume(String volume) { this.volume.set(volume); }

    public String getPublisher() { return publisher.get(); }

    public StringProperty publisherProperty() { return publisher; }

    public void setPublisher(String publisher) { this.publisher.set(publisher); }

    public String getAddress() { return address.get(); }

    public StringProperty addressProperty() { return address; }

    public void setAddress(String address) { this.address.set(address); }

    public String getPages() { return pages.get(); }

    public StringProperty pagesProperty() { return pages; }

    public void setPages(String pages) { this.pages.set(pages); }

    @Override
    public ConferencePaperReference toModel() {

        ConferencePaperReference conferencePaperReference = new ConferencePaperReference();
        conferencePaperReference.setAuthor(this.getAuthor());
        conferencePaperReference.setTitle(this.getTitle());
        conferencePaperReference.setLocalDate(this.getDate());
        conferencePaperReference.setId(this.getId());
        conferencePaperReference.setNote(this.getNote());
        conferencePaperReference.setFromServer(this.isIsFromServer());
        conferencePaperReference.setModified(this.isIsModified());
        conferencePaperReference.setActive(this.isIsActive());

        conferencePaperReference.setVolume(this.getVolume());
        conferencePaperReference.setPublisher(this.getPublisher());
        conferencePaperReference.setAddress(this.getAddress());
        conferencePaperReference.setPages(this.getPages());

        return conferencePaperReference;
    }
}
