package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.model.BookReference;
import javafx.beans.property.*;

import java.time.LocalDate;

public class BookReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    protected StringProperty publisher;
    protected StringProperty volume;
    protected StringProperty series;
    protected StringProperty address;
    protected StringProperty edition;

    public BookReferenceVM() {
        super();
        createEmptyBookReferenceVM();
    }

    public BookReferenceVM(int id, String author, String title, LocalDate date, String note, boolean isFromServer, boolean isActive, boolean isModified, String publisher, String volume, String series, String address, String edition) {
        super(id, author, title, date, note, isFromServer, isActive, isModified);
        createEmptyBookReferenceVM();

        setPublisher(publisher);
        setVolume(volume);
        setSeries(series);
        setAddress(address);
        setEdition(edition);
    }

    public BookReferenceVM(BookReference bookReference) {
        super(bookReference.getId(), bookReference.getAuthor(), bookReference.getTitle(), bookReference.getLocalDate(), bookReference.getNote(), bookReference.isFromServer(), bookReference.isActive(), bookReference.isModified());

        createEmptyBookReferenceVM();

        setPublisher(bookReference.getPublisher());
        setVolume(bookReference.getVolume());
        setSeries(bookReference.getSeries());
        setAddress(bookReference.getAddress());
        setEdition(bookReference.getEdition());
    }

    private void createEmptyBookReferenceVM() {
        this.publisher = new SimpleStringProperty();
        this.volume = new SimpleStringProperty();
        this.series = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.edition = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.BOOK);
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

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
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

    public String getEdition() {
        return edition.get();
    }

    public StringProperty editionProperty() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition.set(edition);
    }

    @Override
    public BookReference toModel() {
        BookReference bookReference = new BookReference();

        bookReference.setAuthor(this.getAuthor());
        bookReference.setTitle(this.getTitle());
        bookReference.setLocalDate(this.getDate());
        bookReference.setId(this.getId());
        bookReference.setNote(this.getNote());
        bookReference.setFromServer(this.isIsFromServer());
        bookReference.setModified(this.isIsModified());
        bookReference.setActive(this.isIsActive());

        bookReference.setPublisher(this.getPublisher());
        bookReference.setVolume(this.getVolume());
        bookReference.setSeries(this.getSeries());
        bookReference.setAddress(this.getAddress());
        bookReference.setEdition(this.getEdition());

        return bookReference;
    }
}
