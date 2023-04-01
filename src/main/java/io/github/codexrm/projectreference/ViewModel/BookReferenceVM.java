package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.model.model.BookReference;
import javafx.beans.property.*;

public class BookReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    protected StringProperty author;
    protected StringProperty editor;
    protected StringProperty publisher;
    protected StringProperty volume;
    protected StringProperty number;
    protected StringProperty series;
    protected StringProperty address;
    protected StringProperty edition;
    protected StringProperty isbn;

    public BookReferenceVM() {
        super();
        createEmptyBookReferenceVM();
    }

    public BookReferenceVM(int id, String title, String year, Months month, String note, boolean isFromServer, boolean isActive, boolean isModified, String author, String editor, String publisher, String volume, String number, String series, String address, String edition, String isbn) {
        super(id, title, year, month, note, isFromServer, isActive, isModified);

        createEmptyBookReferenceVM();

        setAuthor(author);
        setEditor(editor);
        setPublisher(publisher);
        setVolume(volume);
        setNumber(number);
        setSeries(series);
        setAddress(address);
        setEdition(edition);
        setIsbn(isbn);
    }

    public BookReferenceVM(BookReference bookReference) {
        super(bookReference.getId(), bookReference.getTitle(), bookReference.getYear(), bookReference.getMonth(), bookReference.getNote(), bookReference.isFromServer(), bookReference.isActive(), bookReference.isModified());

        createEmptyBookReferenceVM();

        setAuthor(bookReference.getAuthor());
        setEditor(bookReference.getEditor());
        setPublisher(bookReference.getPublisher());
        setVolume(bookReference.getVolume());
        setNumber(bookReference.getNumber());
        setSeries(bookReference.getSeries());
        setAddress(bookReference.getAddress());
        setEdition(bookReference.getEdition());
        setIsbn(bookReference.getIsbn());
    }

    private void createEmptyBookReferenceVM() {

        this.author = new SimpleStringProperty();
        this.editor = new SimpleStringProperty();
        this.publisher = new SimpleStringProperty();
        this.volume = new SimpleStringProperty();
        this.number = new SimpleStringProperty();
        this.series = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.edition = new SimpleStringProperty();
        this.isbn = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.BOOK);
    }

    public String getAuthor() { return author.get(); }

    public StringProperty authorProperty() { return author; }

    public void setAuthor(String author) { this.author.set(author); }

    public String getEditor() { return editor.get(); }

    public StringProperty editorProperty() { return editor; }

    public void setEditor(String editor) { this.editor.set(editor); }

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

    public String getEdition() {
        return edition.get();
    }

    public StringProperty editionProperty() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition.set(edition);
    }

    public String getIsbn() { return isbn.get(); }

    public StringProperty isbnProperty() { return isbn; }

    public void setIsbn(String isbn) { this.isbn.set(isbn); }

    public ReferenceType getReferenceType() {
        return referenceType.get();
    }

    public void setReferenceType(final ReferenceType referenceType) {this.referenceType.set(referenceType);}

    public ObjectProperty<ReferenceType> referenceTypeProperty() {
        return referenceType;
    }

    @Override
    public BookReference toModel() {

        BookReference bookReference = new BookReference();

        bookReference.setTitle(this.getTitle());
        bookReference.setYear(this.getYear());
        bookReference.setMonth(this.getMonth());
        bookReference.setId(this.getId());
        bookReference.setNote(this.getNote());
        bookReference.setFromServer(this.isIsFromServer());
        bookReference.setModified(this.isIsModified());
        bookReference.setActive(this.isIsActive());


        bookReference.setAuthor(this.getAuthor());
        bookReference.setEditor(this.getEditor());
        bookReference.setPublisher(this.getPublisher());
        bookReference.setVolume(this.getVolume());
        bookReference.setNumber(this.getNumber());
        bookReference.setSeries(this.getSeries());
        bookReference.setAddress(this.getAddress());
        bookReference.setEdition(this.getEdition());
        bookReference.setIsbn(this.getIsbn());

        return bookReference;
    }
}
