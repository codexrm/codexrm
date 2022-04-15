package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.model.AuthorLibrary;
import io.github.codexrm.projectreference.model.model.Reference;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ReferenceVM {

    protected final IntegerProperty id;
    protected final StringProperty author;
    protected final StringProperty title;
    protected final ObjectProperty<LocalDate> date;
    protected final StringProperty note;
    protected AuthorLibrary authorLibrary;

    public ReferenceVM() {
        id = new SimpleIntegerProperty();
        author = new SimpleStringProperty();
        title = new SimpleStringProperty();
        date = new SimpleObjectProperty<>();
        note = new SimpleStringProperty();
        this.authorLibrary = null;
    }

    public ReferenceVM(final int id, final ArrayList<Integer> authorIdList , final String title, LocalDate date, String note,AuthorLibrary authorLibrary ) {

        this.id = new SimpleIntegerProperty();
        this.author = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
        this.date = new SimpleObjectProperty<>();
        this.note = new SimpleStringProperty();
        this.authorLibrary = authorLibrary;

        setId(id);
        setAuthor(authorIdList);
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

    public void setAuthor(final ArrayList<Integer> authorIdList) {
        if ( authorIdList == null) {
            this.author.set("lastName1,Name1;lastNameN,nameN...");
        } else{
            this.author.set(authorLibrary.readAuthorsViewList(authorIdList));
        }
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

    public void setTitle(final String title) {this.title.set(title);}

    public LocalDate getDate() {return date.get();}

    public ObjectProperty<LocalDate> dateProperty() {return date;}

    public void setDate(LocalDate date) {this.date.set(date);}

    public String getNote() {return note.get();}

    public StringProperty noteProperty() {return note;}

    public void setNote(String note) {this.note.set(note);}

    public StringProperty titleProperty() {return title;}

    public AuthorLibrary getAuthorLibrary() {
        return authorLibrary;
    }
    public void setAuthorLibrary(AuthorLibrary authorLibrary) {
        this.authorLibrary = authorLibrary;
    }

    public ArrayList<Integer> getAuthorIdList() {
        if (this.getAuthor() == null)
            return null;
        else {
            if (!this.getAuthor().equals("lastName1,Name1;lastNameN,nameN...")) {
                return authorLibrary.createAuthor(this.getAuthor());
            } else {
                return null;
            }
        }
    }

    public Reference toModel() {
        return new Reference(this.getId(), this.getAuthorIdList(), this.getTitle(), this.getDate(), this.getNote());
    }
}
