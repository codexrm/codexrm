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

public class DetailsWebPageReferenceController implements Initializable {

    @FXML
    private TextField author, title, year, url, note;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private final FieldValidations validations = new FieldValidations();

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == WebPageReferenceVM.class)) {

                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                year.textProperty().unbindBidirectional(oldReference.yearProperty());
                month.valueProperty().unbindBidirectional(oldReference.monthProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());

                author.textProperty().unbindBidirectional(((WebPageReferenceVM) oldReference).authorProperty() );
                url.textProperty().unbindBidirectional(((WebPageReferenceVM) oldReference).urlProperty());

                referenceType.valueProperty().unbindBidirectional(((WebPageReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == WebPageReferenceVM.class)) {

                title.textProperty().bindBidirectional(newReference.titleProperty());
                year.textProperty().bindBidirectional(newReference.yearProperty());
                month.valueProperty().bindBidirectional(newReference.monthProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());

                author.textProperty().bindBidirectional(((WebPageReferenceVM) newReference).authorProperty());
                url.textProperty().bindBidirectional(((WebPageReferenceVM) newReference).urlProperty());

                referenceType.valueProperty().bindBidirectional(((WebPageReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            year.clear();
            url.clear();
            note.clear();
        }
    };

    public TextField getAuthor() { return author; }

    public void setAuthor(TextField author) { this.author = author; }

    public TextField getTitle() { return title; }

    public void setTitle(TextField title) { this.title = title; }

    public ComboBox<Months> getMonth() { return month; }

    public void setMonth(ComboBox<Months> month) { this.month = month; }

    public TextField getYear() { return year; }

    public void setYear(TextField year) { this.year = year; }

    public TextField getUrl() { return url; }

    public void setUrl(TextField url) { this.url = url; }

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

    public boolean validateFields(WebPageReferenceVM webPage) {
        boolean isValidate = true;

        if (!validations.validateAuthorOrEditor(webPage.getAuthor())) {
            webPage.setAuthor("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateYear(webPage.getYear())) {
            webPage.setYear("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateUrl(webPage.getUrl())) {
            webPage.setUrl("CodexRM:Error");
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
            if (newReferenceType != ReferenceType.WEBPAGE) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}
