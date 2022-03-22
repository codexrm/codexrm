package io.github.codexrm.projectreference.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.Model.Enum.*;
import io.github.codexrm.projectreference.ViewModel.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DetailsThesisReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private TextField school;

    @FXML
    private ComboBox<ThesisType> type;


    @FXML
    private TextField address;

    @FXML
    private ComboBox<ReferenceType> referenceType;
    private ReferenceLibraryManagerVM managerVM;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference.getClass() == ThesisReferenceVM.class)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional(oldReference.dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                school.textProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).schoolProperty());
                type.valueProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).typeProperty());
                address.textProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).addressProperty());

                referenceType.valueProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == ThesisReferenceVM.class)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                school.textProperty().bindBidirectional(((ThesisReferenceVM) newReference).schoolProperty());
                type.valueProperty().bindBidirectional(((ThesisReferenceVM) newReference).typeProperty());
                address.textProperty().bindBidirectional(((ThesisReferenceVM) newReference).addressProperty());

                referenceType.valueProperty().bindBidirectional(((ThesisReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            date.setValue(LocalDate.now());
            note.clear();
            school.clear();
            address.clear();
        }
    };


    public String getAuthor() {
        return author.getText();
    }

    public void setAuthor(String author) {this.author.setText(author);}

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {this.title.setText(title);}

    public LocalDate getDate() {
        return date.getValue();
    }

    public void setDate(LocalDate date) {this.date.setValue(date);}

    public String getNote() {
        return note.getText();
    }

    public void setNote(String note) {
        this.note.setText(note);
    }

    public String getSchool() {
        return school.getText();
    }

    public void setSchool(String school) {this.school.setText(school);}

    public ThesisType getType() {
        return type.getValue();
    }

    public void setType(ThesisType type) {
        this.type.getSelectionModel().select(type);
    }

    public String getAddress() {
        return address.getText();
    }

    public void setAddress(String address) {this.address.setText(address);}

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType.getSelectionModel().select(referenceType);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReferenceType();
        loadThesisType();
    }

    public void setDataModel(ReferenceLibraryManagerVM dataViewModel) {
        if (this.managerVM != null) {
            this.managerVM.currentReferenceProperty().removeListener(referenceVMListener);
        }

        this.managerVM = dataViewModel;
        this.managerVM.currentReferenceProperty().addListener(referenceVMListener);
    }

    private void loadThesisType(){
        type.getItems().addAll(ThesisType.values());
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


