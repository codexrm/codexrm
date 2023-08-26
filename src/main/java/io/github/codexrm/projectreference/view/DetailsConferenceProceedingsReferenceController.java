package io.github.codexrm.projectreference.view;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.utils.FieldValidations;
import io.github.codexrm.projectreference.viewmodel.ConferenceProceedingsReferenceVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceLibraryManagerVM;
import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.viewmodel.ReferenceVM;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DetailsConferenceProceedingsReferenceController implements Initializable {

    @FXML
    private TextField title, year, editor, volume, number, series, address, publisher, organization, isbn, note;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private FieldValidations validations = new FieldValidations();

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == ConferenceProceedingsReferenceVM.class)) {

                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                year.textProperty().unbindBidirectional(oldReference.yearProperty());
                month.valueProperty().unbindBidirectional(oldReference.monthProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());

                editor.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).editorProperty());
                volume.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).volumeProperty());
                number.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).numberProperty());
                series.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).seriesProperty());
                address.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).addressProperty());
                publisher.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).publisherProperty());
                organization.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).organizationProperty());
                isbn.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).isbnProperty());

                referenceType.valueProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == ConferenceProceedingsReferenceVM.class)) {

                title.textProperty().bindBidirectional(newReference.titleProperty());
                year.textProperty().bindBidirectional(newReference.yearProperty());
                month.valueProperty().bindBidirectional(newReference.monthProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());

                editor.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).editorProperty());
                volume.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).volumeProperty());
                number.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).numberProperty());
                series.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).seriesProperty());
                address.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).addressProperty());
                publisher.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).publisherProperty());
                organization.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).organizationProperty());
                isbn.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).isbnProperty());

                referenceType.valueProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).referenceTypeProperty());
            }
        } else {

            title.clear();
            year.clear();
            editor.clear();
            volume.clear();
            number.clear();
            series.clear();
            address.clear();
            publisher.clear();
            organization.clear();
            isbn.clear();
            note.clear();
        }
    };

    public TextField getTitle() { return title; }

    public void setTitle(TextField title) { this.title = title; }

    public TextField getYear() { return year; }

    public void setYear(TextField year) { this.year = year; }

    public TextField getEditor() { return editor; }

    public void setEditor(TextField editor) { this.editor = editor; }

    public TextField getVolume() { return volume; }

    public void setVolume(TextField volume) { this.volume = volume; }

    public TextField getNumber() { return number; }

    public void setNumber(TextField number) { this.number = number; }

    public TextField getSeries() { return series; }

    public void setSeries(TextField series) { this.series = series; }

    public TextField getAddress() { return address; }

    public void setAddress(TextField address) { this.address = address; }

    public TextField getPublisher() { return publisher; }

    public void setPublisher(TextField publisher) { this.publisher = publisher; }

    public ComboBox<Months> getMonth() { return month; }

    public void setMonth(ComboBox<Months> month) { this.month = month; }

    public TextField getOrganization() { return organization; }

    public void setOrganization(TextField organization) { this.organization = organization; }

    public TextField getIsbn() { return isbn; }

    public void setIsbn(TextField isbn) { this.isbn = isbn; }

    public TextField getNote() { return note; }

    public void setNote(TextField note) { this.note = note; }

    public ComboBox<ReferenceType> getReferenceType() { return referenceType; }

    public void setReferenceType(ComboBox<ReferenceType> referenceType) { this.referenceType = referenceType; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReferenceType();
        loadMonths();
    }

    public void setDataModel(ReferenceLibraryManagerVM dataViewModel) {

        if (this.managerVM != null) {
            this.managerVM.currentReferenceProperty().removeListener(referenceVMListener);
        }
        this.managerVM = dataViewModel;
        this.managerVM.currentReferenceProperty().addListener(referenceVMListener);
    }

    public boolean validateFields(ConferenceProceedingsReferenceVM proceedings) {
        boolean isValidate = true;

        if (proceedings.getTitle().equals("No Title") || proceedings.getTitle().isBlank() || proceedings.getTitle().equals("CodexRM:Error")) {
            proceedings.setTitle("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateYearRequired(proceedings.getYear())) {
            proceedings.setYear("CodexRM:Error");
            isValidate = false;
        }

        if(!validations.validateAuthorOrEditor(proceedings.getEditor())){
            proceedings.setEditor("CodexRM:Error");
            isValidate = false;
        }

        if(!validations.validateChapterOrVolume(proceedings.getVolume())){
            proceedings.setVolume("CodexRM:Error");
            isValidate = false;
        }
        if (!validations.validateNumber(proceedings.getNumber())) {
            proceedings.setNumber("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateSeries(proceedings.getSeries())) {
            proceedings.setSeries("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateAddress(proceedings.getAddress())) {
            proceedings.setAddress("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateIsbn(proceedings.getIsbn())) {
            proceedings.setIsbn("CodexRM:Error");
            isValidate = false;
        }

        return isValidate;
    }

    private void loadMonths(){
        month.getItems().add(null);
        month.getItems().addAll(Months.values());
    }

    private void loadReferenceType() {

        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldReferenceType, newReferenceType) -> {
            if (newReferenceType != ReferenceType.CONFERENCEPROCEEDINGS) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}


