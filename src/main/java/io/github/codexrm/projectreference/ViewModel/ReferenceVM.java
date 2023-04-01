package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.model.Reference;
import javafx.beans.property.*;

public class ReferenceVM {

    protected final IntegerProperty id;
    protected final StringProperty title;
    protected final StringProperty year;
    protected final ObjectProperty<Months> month;
    protected final StringProperty note;
    protected BooleanProperty isFromServer;
    protected BooleanProperty isModified ;
    protected BooleanProperty isActive ;

    public ReferenceVM() {
        id = new SimpleIntegerProperty();
        title = new SimpleStringProperty();
        year = new SimpleStringProperty();
        month = new SimpleObjectProperty();
        note = new SimpleStringProperty();
        isFromServer = new SimpleBooleanProperty();
        isActive = new SimpleBooleanProperty();
        isModified = new SimpleBooleanProperty();
    }

    public ReferenceVM(final int id, final String title, final String year , Months month, String note, boolean isFromServer, boolean isActive, boolean isModified) {

        this.id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty();
        this.year = new SimpleStringProperty();
        this.month = new SimpleObjectProperty();
        this.note = new SimpleStringProperty();
        this.isFromServer = new SimpleBooleanProperty();
        this.isActive = new SimpleBooleanProperty();
        this.isModified = new SimpleBooleanProperty();

        setId(id);
        setTitle(title);
        setYear(year);
        setMonth(month);
        setNote(note);
        setIsFromServer(isFromServer);
        setIsActive(isActive);
        setIsModified(isModified);
    }

    public int getId() {return id.get();}

    public IntegerProperty idProperty() {return id;}

    public void setId(int id) {this.id.set(id);}

    public String getTitle() {return title.get();}

    public StringProperty titleProperty() {return title;}

    public void setTitle(String title) {this.title.set(title);}

    public String getYear() { return year.get(); }

    public StringProperty yearProperty() { return year; }

    public void setYear(String year) { this.year.set(year); }

    public Months getMonth() { return month.get(); }

    public ObjectProperty<Months> monthProperty() { return month; }

    public void setMonth(Months month) { this.month.set(month); }

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
        return new Reference(this.getTitle(), this.getYear(), this.getMonth(), this.getNote(), this.getId(), this.isIsFromServer(), this.isIsModified(), this.isIsActive()); }
}
