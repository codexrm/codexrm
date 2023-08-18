package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.model.model.ConferencePaperReference;
import javafx.beans.property.*;

public class ConferencePaperReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty author;
    private StringProperty bookTitle;
    private StringProperty editor;
    private StringProperty volume;
    private StringProperty number;
    private StringProperty series;
    private StringProperty pages;
    private StringProperty address;
    private StringProperty organization;
    private StringProperty publisher;



    public ConferencePaperReferenceVM() {
        super();
        createEmptyConferencePaperReferenceVM();
    }

    public ConferencePaperReferenceVM(ConferencePaperReference conferencePaperReference) {
        super(conferencePaperReference.getId(), conferencePaperReference.getTitle(), conferencePaperReference.getYear(), conferencePaperReference.getMonth(), conferencePaperReference.getNote(),
                conferencePaperReference.isFromServer(), conferencePaperReference.isActive(), conferencePaperReference.isModified());

        createEmptyConferencePaperReferenceVM();

        setAuthor(conferencePaperReference.getAuthor());
        setBookTitle(conferencePaperReference.getBookTitle());
        setEditor(conferencePaperReference.getEditor());
        setVolume(conferencePaperReference.getVolume());
        setNumber(conferencePaperReference.getNumber());
        setSeries(conferencePaperReference.getSeries());
        setPages(conferencePaperReference.getPages());
        setAddress(conferencePaperReference.getAddress());
        setOrganization(conferencePaperReference.getOrganization());
        setPublisher(conferencePaperReference.getPublisher());
    }

    private void createEmptyConferencePaperReferenceVM() {

        this.author = new SimpleStringProperty();
        this.bookTitle = new SimpleStringProperty();
        this.editor = new SimpleStringProperty();
        this.volume = new SimpleStringProperty();
        this.number = new SimpleStringProperty();
        this.series = new SimpleStringProperty();
        this.pages = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.organization = new SimpleStringProperty();
        this.publisher = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.CONFERENCEPAPER);
    }

    public String getAuthor() { return author.get(); }

    public StringProperty authorProperty() { return author; }

    public void setAuthor(String author) { this.author.set(author); }

    public String getBookTitle() { return bookTitle.get(); }

    public StringProperty bookTitleProperty() { return bookTitle; }

    public void setBookTitle(String bookTitle) { this.bookTitle.set(bookTitle); }

    public String getEditor() { return editor.get(); }

    public StringProperty editorProperty() { return editor; }

    public void setEditor(String editor) { this.editor.set(editor); }

    public String getVolume() { return volume.get(); }

    public StringProperty volumeProperty() { return volume; }

    public void setVolume(String volume) { this.volume.set(volume); }

    public String getNumber() { return number.get(); }

    public StringProperty numberProperty() { return number; }

    public void setNumber(String number) { this.number.set(number); }

    public String getSeries() { return series.get(); }

    public StringProperty seriesProperty() { return series; }

    public void setSeries(String series) { this.series.set(series); }

    public String getPages() { return pages.get(); }

    public StringProperty pagesProperty() { return pages; }

    public void setPages(String pages) { this.pages.set(pages); }

    public String getAddress() { return address.get(); }

    public StringProperty addressProperty() { return address; }

    public void setAddress(String address) { this.address.set(address); }

    public String getOrganization() { return organization.get(); }

    public StringProperty organizationProperty() { return organization; }

    public void setOrganization(String organization) { this.organization.set(organization); }

    public String getPublisher() { return publisher.get(); }

    public StringProperty publisherProperty() { return publisher; }

    public void setPublisher(String publisher) { this.publisher.set(publisher); }

    public ReferenceType getReferenceType() { return referenceType.get(); }

    public ObjectProperty<ReferenceType> referenceTypeProperty() { return referenceType; }

    public void setReferenceType(ReferenceType referenceType) { this.referenceType.set(referenceType); }

    @Override
    public ConferencePaperReference toModel() {

        ConferencePaperReference conferencePaperReference = new ConferencePaperReference();

        conferencePaperReference.setTitle(this.getTitle());
        conferencePaperReference.setYear(this.getYear());
        conferencePaperReference.setMonth(this.getMonth());
        conferencePaperReference.setId(this.getId());
        conferencePaperReference.setNote(this.getNote());
        conferencePaperReference.setFromServer(this.isIsFromServer());
        conferencePaperReference.setModified(this.isIsModified());
        conferencePaperReference.setActive(this.isIsActive());

        conferencePaperReference.setAuthor(this.getAuthor());
        conferencePaperReference.setBookTitle(this.getBookTitle());
        conferencePaperReference.setEditor(this.getEditor());
        conferencePaperReference.setVolume(this.getVolume());
        conferencePaperReference.setNumber(this.getNumber());
        conferencePaperReference.setSeries(this.getSeries());
        conferencePaperReference.setPages(this.getPages());
        conferencePaperReference.setAddress(this.getAddress());
        conferencePaperReference.setOrganization(this.getOrganization());
        conferencePaperReference.setPublisher(this.getPublisher());

        return conferencePaperReference;
    }
}
