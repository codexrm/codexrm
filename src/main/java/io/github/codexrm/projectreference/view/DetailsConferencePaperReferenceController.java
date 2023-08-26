package io.github.codexrm.projectreference.view;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.model.utils.FieldValidations;
import io.github.codexrm.projectreference.viewmodel.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsConferencePaperReferenceController implements Initializable {

    @FXML
    private TextField author, title, bookTitle, year, editor, volume, number, series, pages, address, organization, publisher, note;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private FieldValidations validations = new FieldValidations();

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

    public boolean validateFields(ConferencePaperReferenceVM paper) {
        boolean isValidate = true;

        if (!validations.validateAuthorOrEditorRequired(paper.getAuthor())) {
            paper.setAuthor("CodexRM:Error");
            isValidate = false;
        }

        if (paper.getTitle().equals("No Title") || paper.getTitle().isBlank() || paper.getTitle().equals("CodexRM:Error")) {
            paper.setTitle("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateFieldRequired(paper.getBookTitle())) {
            paper.setBookTitle("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateYearRequired(paper.getYear())) {
            paper.setYear("CodexRM:Error");
            isValidate = false;
        }

        if(!validations.validateAuthorOrEditor(paper.getEditor())){
            paper.setEditor("CodexRM:Error");
            isValidate = false;
        }

        if(!validations.validateChapterOrVolume(paper.getVolume())){
            paper.setVolume("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateNumber(paper.getNumber())) {
            paper.setNumber("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateSeries(paper.getSeries())) {
            paper.setSeries("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validatePages(paper.getPages())) {
            paper.setPages("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateAddress(paper.getAddress())) {
            paper.setAddress("CodexRM:Error");
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
            if (newReferenceType != ReferenceType.CONFERENCEPAPER) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}


