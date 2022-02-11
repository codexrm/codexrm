package io.github.codexrm.projectreference.ViewModel;


import io.github.codexrm.projectreference.Model.Model.BookLetReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.Objects;

public class BookLetVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty howpublished;
    private StringProperty address;

    public BookLetVM() {
        super();
        createEmptyBookLetReferenceVM();
    }

    public BookLetVM(final int id, final String author, final String title, LocalDate date, String note,
                     final String howpublished, final String address) {
        super(id, author, title, date, note);

        setHowpublished(howpublished);
        setAddress(address);

        createEmptyBookLetReferenceVM();

    }

    public BookLetVM(BookLetReference bookLetReference) {
        super(bookLetReference.getId(), bookLetReference.getAuthor(), bookLetReference.getTitle(), bookLetReference.getDate(), bookLetReference.getNote());

        createEmptyBookLetReferenceVM();

        setHowpublished(bookLetReference.getHowpublished());
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
        BookLetReference bookLetReference = new BookLetReference(this.getId(), this.getAuthor(), this.getTitle(), this.getDate(), this.getNote());
        bookLetReference.setHowpublished(this.getHowpublished());
        bookLetReference.setAddress(this.getAddress());

        return bookLetReference;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BookLetVM)) return false;
        if (!super.equals(o)) return false;
        final BookLetVM that = (BookLetVM) o;
        return getReferenceType().equals(that.getReferenceType()) &&
                Objects.equals(getHowpublished(), that.getHowpublished()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getReferenceType(), getHowpublished(), getAddress());
    }
}
