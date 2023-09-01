package io.github.codexrm.projectreference.view;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.model.enums.*;
import io.github.codexrm.projectreference.model.utils.FieldValidations;
import io.github.codexrm.projectreference.viewmodel.ReferenceLibraryManagerVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceVM;
import io.github.codexrm.projectreference.viewmodel.ThesisReferenceVM;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DetailsThesisReferenceController implements Initializable {

    @FXML
    private TextField author, title, school, year, address, note;

    @FXML
    private ComboBox<ThesisType> type;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private final FieldValidations validations = new FieldValidations();

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == ThesisReferenceVM.class)) {

                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                year.textProperty().unbindBidirectional(oldReference.yearProperty());
                month.valueProperty().unbindBidirectional(oldReference.monthProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());

                author.textProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).authorProperty());
                school.textProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).schoolProperty());
                type.valueProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).typeProperty());
                address.textProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).addressProperty());

                referenceType.valueProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == ThesisReferenceVM.class)) {

                title.textProperty().bindBidirectional(newReference.titleProperty());
                year.textProperty().bindBidirectional(newReference.yearProperty());
                month.valueProperty().bindBidirectional(newReference.monthProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());

                author.textProperty().bindBidirectional(((ThesisReferenceVM) newReference).authorProperty());
                school.textProperty().bindBidirectional(((ThesisReferenceVM) newReference).schoolProperty());
                type.valueProperty().bindBidirectional(((ThesisReferenceVM) newReference).typeProperty());
                address.textProperty().bindBidirectional(((ThesisReferenceVM) newReference).addressProperty());

                referenceType.valueProperty().bindBidirectional(((ThesisReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            school.clear();
            year.clear();
            address.clear();
            note.clear();
        }
    };

    public TextField getAuthor() { return author; }

    public void setAuthor(TextField author) { this.author = author; }

    public TextField getTitle() { return title; }

    public void setTitle(TextField title) { this.title = title; }

    public TextField getSchool() { return school; }

    public void setSchool(TextField school) { this.school = school; }

    public TextField getYear() { return year; }

    public void setYear(TextField year) { this.year = year; }

    public ComboBox<ThesisType> getType() { return type; }

    public void setType(ComboBox<ThesisType> type) { this.type = type; }

    public TextField getAddress() { return address; }

    public void setAddress(TextField address) { this.address = address; }

    public ComboBox<Months> getMonth() { return month; }

    public void setMonth(ComboBox<Months> month) { this.month = month; }

    public TextField getNote() { return note; }

    public void setNote(TextField note) { this.note = note; }

    public ComboBox<ReferenceType> getReferenceType() { return referenceType; }

    public void setReferenceType(ComboBox<ReferenceType> referenceType) { this.referenceType = referenceType; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReferenceType();
        loadThesisType();
        loadMonths();
    }

    public void setDataModel(ReferenceLibraryManagerVM dataViewModel) {

        if (this.managerVM != null) {
            this.managerVM.currentReferenceProperty().removeListener(referenceVMListener);
        }

        this.managerVM = dataViewModel;
        this.managerVM.currentReferenceProperty().addListener(referenceVMListener);
    }

    public boolean validateFields(ThesisReferenceVM thesis) {
        boolean isValidate = true;

        if (!validations.validateAuthorOrEditorRequired(thesis.getAuthor())) {
            thesis.setAuthor("CodexRM:Error");
            isValidate = false;
        }

        if (thesis.getTitle().equals("No Title") || thesis.getTitle().isBlank() || thesis.getTitle().equals("CodexRM:Error")) {
            thesis.setTitle("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateFieldRequired(thesis.getSchool())) {
            thesis.setSchool("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateYearRequired(thesis.getYear())) {
            thesis.setYear("CodexRM:Error");
            isValidate = false;
        }

        if(!validations.validateAddress(thesis.getAddress())){
            thesis.setAddress("CodexRM:Error");
            isValidate = false;
        }

        return isValidate;
    }

    private void loadThesisType(){
        type.getItems().add(null);
        type.getItems().addAll(ThesisType.values()); }

    private void loadMonths(){
        month.getItems().add(null);
        month.getItems().addAll(Months.values());
    }

    private void loadReferenceType() {

        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldReferenceType, newReferenceType) -> {
            if (newReferenceType != ReferenceType.THESIS) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }


}


