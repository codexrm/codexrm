package io.github.codexrm.projectreference.view;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.model.enums.*;
import io.github.codexrm.projectreference.viewmodel.ReferenceLibraryManagerVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceVM;
import io.github.codexrm.projectreference.viewmodel.ThesisReferenceVM;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class DetailsThesisReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private TextField school;

    @FXML
    private TextField year;

    @FXML
    private ComboBox<ThesisType> type;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private TextField note;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private ValidationSupport validationSupport = new ValidationSupport();

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

    public ValidationSupport getValidationSupport() { return validationSupport; }

    public void setValidationSupport(ValidationSupport validationSupport) { this.validationSupport = validationSupport; }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        validationSupport.registerValidator(author, true, Validator.createRegexValidator("El texto no cumple con la sintaxis requerida", "^[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+[;(?=[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+)[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+]*", Severity.ERROR));
        validationSupport.registerValidator(title,true, Validator.createEmptyValidator("Campo requerido"));
        validationSupport.registerValidator(school,true, Validator.createEmptyValidator("Campo requerido"));
        validationSupport.registerValidator(year,true,  Validator.createRegexValidator("Solo se pueden introducir un año o un rango de años", "\\d{4}|\\d{4}--\\d{4}", Severity.ERROR));

        validationSupport.registerValidator(address, false, Validator.createRegexValidator("El texto no cumple con la sintaxis requerida", "^$|^[A-ZÁÉÍÓÚÜÑ][A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]*[A-ZÁÉÍÓÚÜÑa-záéíóúüñ]+,\\s[[A-Za-záéíóúüñÁÉÍÓÚÜÑ]+]*", Severity.ERROR));

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


