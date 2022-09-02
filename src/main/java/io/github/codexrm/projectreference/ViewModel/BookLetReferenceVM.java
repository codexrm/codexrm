package io.github.codexrm.projectreference.viewmodel;


import io.github.codexrm.projectreference.model.model.BookLetReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class BookLetReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty howpublished;
    private StringProperty address;

    public BookLetReferenceVM() {
        super();
        createEmptyBookLetReferenceVM();
    }

    public BookLetReferenceVM(BookLetReference bookLetReference) {
        super(bookLetReference.getId(), bookLetReference.getAuthor(), bookLetReference.getTitle(), bookLetReference.getLocalDate(), bookLetReference.getNote(), bookLetReference.isFromServer(), bookLetReference.isActive(), bookLetReference.isModified());

        createEmptyBookLetReferenceVM();

        setHowpublished(bookLetReference.getHowpublisher());
        setAddress(bookLetReference.getAddress());
    }

    private void createEmptyBookLetReferenceVM() {

        this.howpublished = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.referenceType = new SimpleObjectProperty<>(ReferenceType.BOOKLET);
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

    public String getHowpublished() {
        return howpublished.get();
    }

    public StringProperty howpublishedProperty() {
        return howpublished;
    }

    public void setHowpublished(String howpublished) {
        this.howpublished.set(howpublished);
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

    @Override
    public BookLetReference toModel() {
        BookLetReference bookLetReference = new BookLetReference();
        bookLetReference.setAuthor(this.getAuthor());
        bookLetReference.setTitle(this.getTitle());
        bookLetReference.setLocalDate(this.getDate());
        bookLetReference.setId(this.getId());
        bookLetReference.setNote(this.getNote());
        bookLetReference.setFromServer(this.isIsFromServer());
        bookLetReference.setModified(this.isIsModified());
        bookLetReference.setActive(this.isIsActive());

        bookLetReference.setHowpublisher(this.getHowpublished());
        bookLetReference.setAddress(this.getAddress());

        return bookLetReference;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BookLetReferenceVM)) return false;
        if (!super.equals(o)) return false;
        final BookLetReferenceVM that = (BookLetReferenceVM) o;
        return getReferenceType().equals(that.getReferenceType()) &&
                Objects.equals(getHowpublished(), that.getHowpublished()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getReferenceType(), getHowpublished(), getAddress());
    }
}
