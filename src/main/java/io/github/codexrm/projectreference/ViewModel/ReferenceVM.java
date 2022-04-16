package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.model.Reference;
import javafx.beans.property.*;

import java.time.LocalDate;

public class ReferenceVM {

    protected final IntegerProperty id;
    protected final StringProperty author;
    protected final StringProperty title;
    protected final ObjectProperty<LocalDate> date;
    protected final StringProperty note;

    public ReferenceVM() {
        id = new SimpleIntegerProperty();
        author = new SimpleStringProperty();
        title = new SimpleStringProperty();
        date = new SimpleObjectProperty<>();
        note = new SimpleStringProperty();
    }

    public ReferenceVM(final int id, final String author , final String title, LocalDate date, String note) {

        this.id = new SimpleIntegerProperty();
        this.author = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
        this.note = new SimpleStringProperty();

        setId(id);
        setAuthor(author);
        setTitle(title);
        setDate(date);
        setNote(note);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
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

    public Reference toModel() {
        return new Reference(this.getId(), this.getAuthor(), this.getTitle(), this.getDate(), this.getNote());
    }
}
