package io.github.codexrm.projectreference.ViewModel;

import io.github.codexrm.projectreference.Model.Model.Reference;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Objects;

public class ReferenceVM {

    protected final IntegerProperty id;
    protected final StringProperty author;
    protected final StringProperty title;
    protected ObjectProperty<LocalDate> date;
    protected final StringProperty note;

    public ReferenceVM() {
        id = new SimpleIntegerProperty();
        author = new SimpleStringProperty();
        title = new SimpleStringProperty();
        date = new SimpleObjectProperty<>();
        note = new SimpleStringProperty();
    }

    public ReferenceVM(final int id, final String author, final String title, LocalDate date, String note) {

        this.id = new SimpleIntegerProperty();
        this.author = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>(date);
        this.note = new SimpleStringProperty();

        setId(id);
        setAuthor(author);
        setTitle(title);
        setDate(date);
        setNote(note);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(final int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(final String author) {
        this.author.set(author);
    }

    public StringProperty authorProperty() {
        return author;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(final String title) {
        this.title.set(title);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public String getNote() {
        return note.get();
    }

    public StringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public Reference toModel() {
        return new Reference(this.getId(), this.getAuthor(), this.getTitle(), this.getDate(), this.getNote());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof ReferenceVM)) return false;
        final ReferenceVM that = (ReferenceVM) o;
        return (getId() == that.getId()) &&
                Objects.equals(getAuthor(), that.getAuthor()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getNote(), that.getNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthor(), getTitle(), getDate(), getNote());
    }
}
