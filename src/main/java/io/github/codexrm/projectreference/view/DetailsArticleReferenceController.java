package io.github.codexrm.projectreference.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.viewmodel.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DetailsArticleReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private TextField journal;

    @FXML
    private TextField volume;

    @FXML
    private TextField number;

    @FXML
    private TextField pages;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference.getClass() == ArticleReferenceVM.class)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional(oldReference.dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                journal.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).journalProperty());
                volume.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).volumeProperty());
                number.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).numberProperty());
                pages.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).pagesProperty());

                referenceType.valueProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == ArticleReferenceVM.class)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                journal.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).journalProperty());
                volume.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).volumeProperty());
                number.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).numberProperty());
                pages.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).pagesProperty());

                referenceType.valueProperty().bindBidirectional(((ArticleReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            date.setValue(LocalDate.now());
            note.clear();
            journal.clear();
            volume.clear();
            number.clear();
            pages.clear();
        }
    };

    public String getAuthor() {return author.getText();}

    public void setAuthor(String author) {this.author.setText(author);}

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {this.title.setText(title);}

    public LocalDate getDate() {
        return date.getValue();
    }

    public void setDate(LocalDate date) {this.date.setValue(date);}

    public String getNote() {
        return note.getText();
    }

    public void setNote(String note) {this.note.setText(note);}

    public String getJournal() {
        return journal.getText();
    }

    public void setJournal(String journal) {this.journal.setText(journal);}

    public String getVolume() {
        return volume.getText();
    }

    public void setVolume(String volume) {this.volume.setText(volume);}

    public String getNumber() {
        return number.getText();
    }

    public void setNumber(String number) {this.number.setText(number);}

    public String getPages() {
        return pages.getText();
    }

    public void setPages(String pages) {this.pages.setText(pages);}

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType.getSelectionModel().select(referenceType);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReferenceType();
    }

    public void setDataModel(ReferenceLibraryManagerVM dataViewModel) {
        if (this.managerVM != null) {
            this.managerVM.currentReferenceProperty().removeListener(referenceVMListener);
        }

        this.managerVM = dataViewModel;
        this.managerVM.currentReferenceProperty().addListener(referenceVMListener);
    }

    private void loadReferenceType() {
        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldReferenceType, newReferenceType) -> {
            if (newReferenceType != ReferenceType.ARTICLE) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };

        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
    }

