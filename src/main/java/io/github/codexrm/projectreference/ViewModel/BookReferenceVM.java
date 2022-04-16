package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.model.BookReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public BookReferenceVM(final int id, final String author, final String title, LocalDate date, String note,
                           final String publisher, final String volume, final String series, final String address, final String edition) {
        super(id, author, title, date, note);

        createEmptyBookReferenceVM();

        setPublisher(publisher);
        setVolume(volume);
        setSeries(series);
        setAddress(address);
        setEdition(edition);
    }

    public BookReferenceVM(BookReference bookReference) {
        super(bookReference.getId(), bookReference.getAuthor(), bookReference.getTitle(), bookReference.getLocalDate(), bookReference.getNote());

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
        BookReference bookReference = new BookReference(this.getId(), this.getAuthor(), this.getTitle(), this.getDate(), this.getNote());
        bookReference.setPublisher(this.getPublisher());
        bookReference.setVolume(this.getVolume());
        bookReference.setSeries(this.getSeries());
        bookReference.setAddress(this.getAddress());
        bookReference.setEdition(this.getEdition());

        return bookReference;
    }
}
