package io.github.codexrm.projectreference.viewmodel;


import io.github.codexrm.projectreference.model.model.AuthorLibrary;
import io.github.codexrm.projectreference.model.model.BookLetReference;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class BookLetReferenceVM extends ReferenceVM {

    ObjectProperty<ReferenceType> referenceType;
    private StringProperty howpublished;
    private StringProperty address;

    public BookLetReferenceVM() {
        super();
        createEmptyBookLetReferenceVM();
    }

    public BookLetReferenceVM(final int id, final ArrayList<Integer> authorIdList, final String title, LocalDate date, String note,
                              final String howpublished, final String address, final AuthorLibrary authorLibrary) {
        super(id, authorIdList, title, date, note, authorLibrary);

        createEmptyBookLetReferenceVM();
        setHowpublished(howpublished);
        setAddress(address);



    }

    public BookLetReferenceVM(BookLetReference bookLetReference, AuthorLibrary authorLibrary) {
        super(bookLetReference.getId(), bookLetReference.getAuthorIdList(), bookLetReference.getTitle(), bookLetReference.getLocalDate(), bookLetReference.getNote(),authorLibrary);

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
        BookLetReference bookLetReference = new BookLetReference(this.getId(), this.getAuthorIdList(), this.getTitle(), this.getDate(), this.getNote());
        bookLetReference.setHowpublished(this.getHowpublished());
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
