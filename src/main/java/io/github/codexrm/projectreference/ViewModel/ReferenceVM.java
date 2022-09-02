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
    protected BooleanProperty isFromServer;
    protected BooleanProperty isModified ;
    protected BooleanProperty isActive ;

    public ReferenceVM() {
        id = new SimpleIntegerProperty();
        author = new SimpleStringProperty();
        title = new SimpleStringProperty();
        date = new SimpleObjectProperty<>();
        note = new SimpleStringProperty();
        isFromServer = new SimpleBooleanProperty();
        isActive = new SimpleBooleanProperty();
        isModified = new SimpleBooleanProperty();
    }

    public ReferenceVM(final int id, final String author , final String title, LocalDate date, String note, boolean isFromServer, boolean isActive, boolean isModified) {

        this.id = new SimpleIntegerProperty();
        this.author = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
        this.note = new SimpleStringProperty();
        this.isFromServer = new SimpleBooleanProperty();
        this.isActive = new SimpleBooleanProperty();
        this.isModified = new SimpleBooleanProperty();

        setId(id);
        setAuthor(author);
        setTitle(title);
        setDate(date);
        setNote(note);
        setIsFromServer(isFromServer);
        setIsActive(isActive);
        setIsModified(isModified);
    }

    public int getId() {return id.get();}

    public IntegerProperty idProperty() {return id;}

    public void setId(int id) {this.id.set(id);}

    public String getAuthor() {return author.get();}

    public StringProperty authorProperty() {return author;}

    public void setAuthor(String author) {this.author.set(author);}

    public String getTitle() {return title.get();}

    public StringProperty titleProperty() {return title;}

    public void setTitle(String title) {this.title.set(title);}

    public LocalDate getDate() {return date.get();}

    public ObjectProperty<LocalDate> dateProperty() {return date;}

    public void setDate(LocalDate date) {this.date.set(date);}

    public String getNote() {return note.get();}

    public StringProperty noteProperty() {return note;}

    public void setNote(String note) {this.note.set(note);}

    public boolean isIsFromServer() {return isFromServer.get();}

    public BooleanProperty isFromServerProperty() {return isFromServer;}

    public void setIsFromServer(boolean isFromServer) {this.isFromServer.set(isFromServer);}

    public boolean isIsModified() {return isModified.get();}

    public BooleanProperty isModifiedProperty() {return isModified;}

    public void setIsModified(boolean isModified) {this.isModified.set(isModified);}

    public boolean isIsActive() {return isActive.get();}

    public BooleanProperty isActiveProperty() {return isActive;}

    public void setIsActive(boolean isActive) {this.isActive.set(isActive);}

    public Reference toModel() {
        return new Reference(this.getAuthor(), this.getTitle(), this.getDate(), this.getNote(),this.getId(),this.isIsFromServer(),this.isIsModified(),this.isIsActive());

    }

}
