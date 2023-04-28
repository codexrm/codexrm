package io.github.codexrm.projectreference.view;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.viewmodel.ConferenceProceedingsReferenceVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceLibraryManagerVM;
import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.viewmodel.ReferenceVM;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

public class DetailsConferenceProceedingsReferenceController implements Initializable {

    @FXML
    private TextField title;

    @FXML
    private TextField year;

    @FXML
    private TextField editor;

    @FXML
    private TextField volume;

    @FXML
    private TextField number;

    @FXML
    private TextField series;

    @FXML
    private TextField address;

    @FXML
    private TextField publisher;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private TextField organization;

    @FXML
    private TextField isbn;

    @FXML
    private TextField note;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private ValidationSupport validationSupport = new ValidationSupport();
    private Validations v = new Validations();

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

        validationSupport.registerValidator(title,true, Validator.createEmptyValidator("Campo requerido"));
        validationSupport.registerValidator(year,true,  Validator.createRegexValidator("Solo se pueden introducir un año o un rango de años", "\\d{4}|\\d{4}-\\d{4}|\\d{4}--\\d{4}", Severity.ERROR));

        validationSupport.registerValidator(editor, false, Validator.createRegexValidator("El texto no cumple con la sintaxis requerida", "^$|^[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+[;(?=[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+)[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+]*", Severity.ERROR));
        validationSupport.registerValidator(volume, false, Validator.createRegexValidator("Solo se puede introduccir números", "^$|[\\d]*", Severity.ERROR));
        validationSupport.registerValidator(number, false, Validator.createRegexValidator("Solo se puede introducir números, letras y el caracter '-'", "^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0-9\\s-]+", Severity.ERROR));
        validationSupport.registerValidator(series, false, Validator.createRegexValidator("Solo se puede introduccir letras", "^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]+", Severity.ERROR));
        validationSupport.registerValidator(address, false, Validator.createRegexValidator("El texto no cumple con la sintaxis requerida", "^$|^[A-ZÁÉÍÓÚÜÑ][A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]+[,\\s*[A-Za-záéíóúüñÁÉÍÓÚÜÑ]+]*", Severity.ERROR));
        validationSupport.registerValidator(publisher, false, Validator.createRegexValidator("Solo se puede introducir letras y el caracter '.'", "^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s\\.]+", Severity.ERROR));
        validationSupport.registerValidator(organization, false, Validator.createRegexValidator("Solo se puede introduccir letras", "^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]+", Severity.ERROR));
        validationSupport.registerValidator(isbn, false, Validator.createRegexValidator("El texto no cumple con la sintaxis requerida", "^$|\\d{4}-\\d{4}", Severity.ERROR));

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


