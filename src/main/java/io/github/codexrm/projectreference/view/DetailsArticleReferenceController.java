package io.github.codexrm.projectreference.view;

import java.net.URL;
import java.util.ResourceBundle;

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

public class DetailsArticleReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private TextField journal;

    @FXML
    private TextField year;

    @FXML
    private TextField volume;

    @FXML
    private TextField number;

    @FXML
    private TextField pages;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private TextField issn;

    @FXML
    private TextField note;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private ValidationSupport validationSupport = new ValidationSupport();

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == ArticleReferenceVM.class)) {

                year.textProperty().unbindBidirectional(oldReference.yearProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                month.valueProperty().unbindBidirectional(oldReference.monthProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());

                author.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).authorProperty());
                journal.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).journalProperty());
                volume.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).volumeProperty());
                number.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).numberProperty());
                pages.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).pagesProperty());
                issn.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).issnProperty());

                referenceType.valueProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == ArticleReferenceVM.class)) {

                title.textProperty().bindBidirectional(newReference.titleProperty());
                year.textProperty().bindBidirectional(newReference.yearProperty());
                month.valueProperty().bindBidirectional(newReference.monthProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());

                author.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).authorProperty());
                journal.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).journalProperty());
                volume.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).volumeProperty());
                number.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).numberProperty());
                pages.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).pagesProperty());
                issn.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).issnProperty());

                referenceType.valueProperty().bindBidirectional(((ArticleReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            title.clear();
            year.clear();
            note.clear();
            author.clear();
            journal.clear();
            volume.clear();
            number.clear();
            pages.clear();
            issn.clear();
        }
    };

    public TextField getAuthor() { return author; }

    public void setAuthor(TextField author) { this.author = author; }

    public TextField getTitle() { return title; }

    public void setTitle(TextField title) { this.title = title; }

    public TextField getJournal() { return journal; }

    public void setJournal(TextField journal) { this.journal = journal; }

    public TextField getYear() { return year; }

    public void setYear(TextField year) { this.year = year; }

    public TextField getVolume() { return volume; }

    public void setVolume(TextField volume) { this.volume = volume; }

    public TextField getNumber() { return number; }

    public void setNumber(TextField number) { this.number = number; }

    public TextField getPages() { return pages; }

    public void setPages(TextField pages) { this.pages = pages; }

    public ComboBox<Months> getMonth() { return month; }

    public void setMonth(ComboBox<Months> month) { this.month = month; }

    public TextField getIssn() { return issn; }

    public void setIssn(TextField issn) { this.issn = issn; }

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
        validationSupport.registerValidator(journal,true, Validator.createEmptyValidator("Campo requerido"));
        validationSupport.registerValidator(year,true, Validator.createRegexValidator("Solo se pueden introducir un año o un rango de años", "\\d{4}|\\d{4}--\\d{4}", Severity.ERROR));

        validationSupport.registerValidator(volume, false, Validator.createRegexValidator("Solo se puede introduccir números", "^$|[\\d]*", Severity.ERROR));
        validationSupport.registerValidator(number, false, Validator.createRegexValidator("Solo se puede introducir números, letras y el caracter '-'", "^$|[A-ZÁÉÍÓÚÜÑa-záéíóúüñ0-9\\s-]+", Severity.ERROR));
        validationSupport.registerValidator(pages, false, Validator.createRegexValidator("Solo se puede introducir número(incluido romano) y los caracteres '-', ','", "^$|[IVXMLCD]+|[IVXMLCD]+,[IVXMLCD]+|[IVXMLCD]+-[IVXMLCD]+|[0-9]+|[0-9]+,[0-9]+|[0-9]+-[0-9]+", Severity.ERROR));
        validationSupport.registerValidator(issn, false, Validator.createRegexValidator("El texto no cumple con la sintaxis requerida", "^$|\\d{4}-\\d{4}|\\d{4}-\\d{3}X", Severity.ERROR));

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
            if (newReferenceType != ReferenceType.ARTICLE) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}

