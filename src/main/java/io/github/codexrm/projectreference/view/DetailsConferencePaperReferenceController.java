package io.github.codexrm.projectreference.view;

import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.viewmodel.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DetailsConferencePaperReferenceController implements Initializable {
    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private TextField volume;

    @FXML
    private TextField publisher;

    @FXML
    private TextField address;

    @FXML
    private TextField pages;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == ConferencePaperReferenceVM.class)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional(oldReference.dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                publisher.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).publisherProperty());
                volume.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).volumeProperty());
                address.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).addressProperty());
                pages.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).pagesProperty());

                referenceType.valueProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == ConferencePaperReferenceVM.class)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                publisher.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).publisherProperty());
                volume.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).volumeProperty());
                address.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).addressProperty());
                pages.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).pagesProperty());

                referenceType.valueProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            date.setValue(LocalDate.now());
            note.clear();
            publisher.clear();
            volume.clear();
            address.clear();
            pages.clear();
        }
    };

    public TextField getAuthor() { return author; }

    public void setAuthor(TextField author) { this.author = author; }

    public TextField getTitle() { return title; }

    public void setTitle(TextField title) { this.title = title; }

    public DatePicker getDate() { return date; }

    public void setDate(DatePicker date) { this.date = date; }

    public TextField getNote() { return note; }

    public void setNote(TextField note) { this.note = note; }

    public TextField getVolume() { return volume; }

    public void setVolume(TextField volume) { this.volume = volume; }

    public TextField getPublisher() { return publisher; }

    public void setPublisher(TextField publisher) { this.publisher = publisher; }

    public TextField getAddress() { return address; }

    public void setAddress(TextField address) { this.address = address; }

    public TextField getPages() { return pages; }

    public void setPages(TextField pages) { this.pages = pages; }

    public ComboBox<ReferenceType> getReferenceType() { return referenceType; }

    public void setReferenceType(ComboBox<ReferenceType> referenceType) { this.referenceType = referenceType; }

    public ReferenceLibraryManagerVM getManagerVM() { return managerVM; }

    public void setManagerVM(ReferenceLibraryManagerVM managerVM) { this.managerVM = managerVM; }

    public ChangeListener<ReferenceVM> getReferenceVMListener() { return referenceVMListener; }

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
            if (newReferenceType != ReferenceType.CONFERENCEPAPER) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}
