package io.github.codexrm.projectreference.view;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.model.utils.FieldValidations;
import io.github.codexrm.projectreference.viewmodel.BookLetReferenceVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceLibraryManagerVM;
import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.viewmodel.ReferenceVM;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DetailsBookLetReferenceController implements Initializable {

    @FXML
    private TextField title, author, howpublished, address, year, note;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private FieldValidations validations = new FieldValidations();

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == BookLetReferenceVM.class)) {

                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                year.textProperty().unbindBidirectional(oldReference.yearProperty());
                month.valueProperty().unbindBidirectional(oldReference.monthProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());

                author.textProperty().unbindBidirectional(((BookLetReferenceVM) oldReference).authorProperty());
                howpublished.textProperty().unbindBidirectional(((BookLetReferenceVM) oldReference).howpublishedProperty());
                address.textProperty().unbindBidirectional(((BookLetReferenceVM) oldReference).addressProperty());

                referenceType.valueProperty().unbindBidirectional(((BookLetReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == BookLetReferenceVM.class)) {

                title.textProperty().bindBidirectional(newReference.titleProperty());
                year.textProperty().bindBidirectional(newReference.yearProperty());
                month.valueProperty().bindBidirectional(newReference.monthProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());

                author.textProperty().bindBidirectional(((BookLetReferenceVM) newReference).authorProperty());
                howpublished.textProperty().bindBidirectional(((BookLetReferenceVM) newReference).howpublishedProperty());
                address.textProperty().bindBidirectional(((BookLetReferenceVM) newReference).addressProperty());

                referenceType.valueProperty().bindBidirectional(((BookLetReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            title.clear();
            author.clear();
            howpublished.clear();
            address.clear();
            year.clear();
            note.clear();
        }
    };

    public TextField getTitle() { return title; }

    public void setTitle(TextField title) { this.title = title; }

    public TextField getAuthor() { return author; }

    public void setAuthor(TextField author) { this.author = author; }

    public TextField getHowpublished() { return howpublished; }

    public void setHowpublished(TextField howpublished) { this.howpublished = howpublished; }

    public TextField getAddress() { return address; }

    public void setAddress(TextField address) { this.address = address; }

    public ComboBox<Months> getMonth() { return month; }

    public void setMonth(ComboBox<Months> month) { this.month = month; }

    public TextField getYear() { return year; }

    public void setYear(TextField year) { this.year = year; }

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

    public boolean validateFields(BookLetReferenceVM bookLet) {
        boolean isValidate = true;

        if (bookLet.getTitle().equals("No Title") || bookLet.getTitle().isBlank() || bookLet.getTitle().equals("CodexRM:Error")) {
            bookLet.setYear("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateAuthorOrEditor(bookLet.getAuthor())) {
            bookLet.setYear("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateAddress(bookLet.getAddress())) {
            bookLet.setYear("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateYear(bookLet.getYear())) {
            bookLet.setYear("CodexRM:Error");
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
            if (newReferenceType != ReferenceType.BOOKLET) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}