package io.github.codexrm.projectreference.view;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.viewmodel.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsConferencePaperReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private TextField bookTitle;

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
    private TextField pages;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private TextField organization;

    @FXML
    private TextField publisher;

    @FXML
    private TextField note;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private ValidationSupport validationSupport = new ValidationSupport();

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == ConferencePaperReferenceVM.class)) {

                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                year.textProperty().unbindBidirectional(oldReference.yearProperty());
                month.valueProperty().unbindBidirectional(oldReference.monthProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());

                author.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).authorProperty());
                bookTitle.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).bookTitleProperty());
                editor.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).editorProperty());
                volume.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).volumeProperty());
                number.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).numberProperty());
                series.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).seriesProperty());
                pages.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).pagesProperty());
                address.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).addressProperty());
                organization.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).organizationProperty());
                publisher.textProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).publisherProperty());

                referenceType.valueProperty().unbindBidirectional(((ConferencePaperReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == ConferencePaperReferenceVM.class)) {

                title.textProperty().bindBidirectional(newReference.titleProperty());
                year.textProperty().bindBidirectional(newReference.yearProperty());
                month.valueProperty().bindBidirectional(newReference.monthProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());

                author.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).authorProperty());
                bookTitle.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).bookTitleProperty());
                editor.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).editorProperty());
                volume.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).volumeProperty());
                number.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).numberProperty());
                series.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).seriesProperty());
                pages.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).pagesProperty());
                address.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).addressProperty());
                organization.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).organizationProperty());
                publisher.textProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).publisherProperty());

                referenceType.valueProperty().bindBidirectional(((ConferencePaperReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            bookTitle.clear();
            year.clear();
            editor.clear();
            volume.clear();
            number.clear();
            series.clear();
            pages.clear();
            address.clear();
            organization.clear();
            publisher.clear();
            note.clear();
        }
    };

    public TextField getAuthor() { return author; }

    public void setAuthor(TextField author) { this.author = author; }

    public TextField getTitle() { return title; }

    public void setTitle(TextField title) { this.title = title; }

    public TextField getBookTitle() { return bookTitle; }

    public void setBookTitle(TextField bookTitle) { this.bookTitle = bookTitle; }

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

    public TextField getPages() { return pages; }

    public void setPages(TextField pages) { this.pages = pages; }

    public TextField getAddress() { return address; }

    public void setAddress(TextField address) { this.address = address; }

    public ComboBox<Months> getMonth() { return month; }

    public void setMonth(ComboBox<Months> month) { this.month = month; }

    public TextField getOrganization() { return organization; }

    public void setOrganization(TextField organization) { this.organization = organization; }

    public TextField getPublisher() { return publisher; }

    public void setPublisher(TextField publisher) { this.publisher = publisher; }

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
        validationSupport.registerValidator(bookTitle,true, Validator.createEmptyValidator("Campo requerido"));
        validationSupport.registerValidator(year,true,  Validator.createRegexValidator("Solo se pueden introducir un año o un rango de años", "\\d{4}|\\d{4}--\\d{4}", Severity.ERROR));

        validationSupport.registerValidator(editor, false, Validator.createRegexValidator("El texto no cumple con la sintaxis requerida", "^$|^[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+[;(?=[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+)[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+,[A-ZÁÉÍÓÚÜÑ][a-záéíóúüñ]+]*", Severity.ERROR));
        validationSupport.registerValidator(volume, false, Validator.createRegexValidator("Solo se puede introduccir números", "^$|[\\d]*", Severity.ERROR));
        validationSupport.registerValidator(number, false, Validator.createRegexValidator("Solo se puede introducir números, letras y el caracter '-'", "^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0-9\\s-]+", Severity.ERROR));
        validationSupport.registerValidator(series, false, Validator.createRegexValidator("Solo se puede introduccir letras", "^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]+", Severity.ERROR));
        validationSupport.registerValidator(pages, false, Validator.createRegexValidator("Solo se puede introducir número(incluido romano) y los caracteres '-', ','", "^$|[IVXMLCD]+|[IVXMLCD]+,[IVXMLCD]+|[IVXMLCD]+-[IVXMLCD]+|[0-9]+|[0-9]+,[0-9]+|[0-9]+-[0-9]+", Severity.ERROR));
        validationSupport.registerValidator(address, false, Validator.createRegexValidator("El texto no cumple con la sintaxis requerida", "^$|^[A-ZÁÉÍÓÚÜÑ][A-ZÁÉÍÓÚÜÑa-záéíóúüñ\\s]*[A-ZÁÉÍÓÚÜÑa-záéíóúüñ]+,\\s[[A-Za-záéíóúüñÁÉÍÓÚÜÑ]+]*", Severity.ERROR));

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
            if (newReferenceType != ReferenceType.CONFERENCEPAPER) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}
