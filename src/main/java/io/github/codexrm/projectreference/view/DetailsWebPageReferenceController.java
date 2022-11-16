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

public class DetailsWebPageReferenceController implements Initializable {
    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private DatePicker accessDate ;

    @FXML
    private TextField url;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == WebPageReferenceVM.class)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional(oldReference.dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                accessDate.valueProperty().unbindBidirectional(((WebPageReferenceVM) oldReference).accessDateProperty());
                url.textProperty().unbindBidirectional(((WebPageReferenceVM) oldReference).urlProperty());

                referenceType.valueProperty().unbindBidirectional(((WebPageReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == WebPageReferenceVM.class)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                accessDate.valueProperty().bindBidirectional(((WebPageReferenceVM) newReference).accessDateProperty());
                url.textProperty().bindBidirectional(((WebPageReferenceVM) newReference).urlProperty());

                referenceType.valueProperty().bindBidirectional(((WebPageReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            date.setValue(LocalDate.now());
            note.clear();
            accessDate.setValue(LocalDate.now());
            url.clear();
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

    public DatePicker getAccessDate() { return accessDate; }

    public void setAccessDate(DatePicker accessDate) { this.accessDate = accessDate; }

    public TextField getUrl() { return url; }

    public void setUrl(TextField url) { this.url = url; }

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
            if (newReferenceType != ReferenceType.WEBPAGE) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}
