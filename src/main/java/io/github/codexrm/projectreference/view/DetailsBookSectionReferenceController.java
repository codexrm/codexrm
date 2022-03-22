package io.github.codexrm.projectreference.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.Model.Enum.ThesisType;
import io.github.codexrm.projectreference.ViewModel.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DetailsBookSectionReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private TextField publisher;

    @FXML
    private TextField volume;

    @FXML
    private TextField series;

    @FXML
    private TextField address;

    @FXML
    private TextField edition;

    @FXML
    private TextField chapter;

    @FXML
    private TextField pages;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference instanceof BookSectionReferenceVM)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional(oldReference.dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                publisher.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).publisherProperty());
                volume.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).volumeProperty());
                series.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).seriesProperty());
                address.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).addressProperty());
                edition.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).editionProperty());
                chapter.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).chapterProperty());
                pages.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).pagesProperty());

                referenceType.valueProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference instanceof BookSectionReferenceVM)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                publisher.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).publisherProperty());
                volume.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).volumeProperty());
                series.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).seriesProperty());
                address.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).addressProperty());
                edition.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).editionProperty());
                chapter.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).chapterProperty());
                pages.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).pagesProperty());

                referenceType.valueProperty().bindBidirectional(((BookSectionReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            date.setValue(LocalDate.now());
            note.clear();
            publisher.clear();
            volume.clear();
            series.clear();
            address.clear();
            edition.clear();
            chapter.clear();
            pages.clear();
        }
    };

    public String getAuthor() {
        return author.getText();
    }

    public void setAuthor(String author) {
        this.author.setText(author);
    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public LocalDate getDate() {
        return date.getValue();
    }

    public void setDate(LocalDate date) {
        this.date.setValue(date);
    }

    public String getNote() {
        return note.getText();
    }

    public void setNote(String note) {
        this.note.setText(note);
    }

    public String getPublisher() {
        return publisher.getText();
    }

    public void setPublisher(String publisher) {
        this.publisher.setText(publisher);
    }

    public String getVolume() {
        return volume.getText();
    }

    public void setVolume(String volume) {
        this.volume.setText(volume);
    }

    public String getSeries() {
        return series.getText();
    }

    public void setSeries(String series) {
        this.series.setText(series);
    }

    public String getAddress() {
        return address.getText();
    }

    public void setAddress(String address) {
        this.address.setText(address);
    }

    public String getEdition() {
        return edition.getText();
    }

    public void setEdition(String edition) {
        this.edition.setText(edition);
    }

    public String getChapter() {
        return chapter.getText();
    }

    public void setChapter(String chapter) {
        this.chapter.setText(chapter);
    }

    public String getPages() {
        return pages.getText();
    }

    public void setPages(String pages) {
        this.pages.setText(pages);
    }

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
            if (newReferenceType != ReferenceType.BOOKSECTION) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };

        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }}