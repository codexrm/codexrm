package io.github.codexrm.projectreference.view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.viewmodel.ConferenceProceedingsReferenceVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceLibraryManagerVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceType;
import io.github.codexrm.projectreference.viewmodel.ReferenceVM;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DetailsConferenceProceedingsReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private TextField volume;

    @FXML
    private TextField serie;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference.getClass() == ConferenceProceedingsReferenceVM.class)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional(oldReference.dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                volume.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).volumeProperty());
                serie.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).serieProperty());
                address.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).addressProperty());

                referenceType.valueProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == ConferenceProceedingsReferenceVM.class)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                volume.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).volumeProperty());
                serie.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).serieProperty());
                address.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).addressProperty());

                referenceType.valueProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            date.setValue(LocalDate.now());
            note.clear();
            volume.clear();
            serie.clear();
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

    public String getVolume() {
        return volume.getText();
    }

    public void setVolume(String volume) {this.volume.setText(volume);}

    public String getSerie() {
        return serie.getText();
    }

    public void setSerie(String serie) {
        this.serie.setText(serie);
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
    }

    public void setDataModel(ReferenceLibraryManagerVM dataViewModel) {
        if (this.managerVM != null) {
            this.managerVM.currentReferenceProperty().removeListener(referenceVMListener);
        }

        this.managerVM = dataViewModel;
        this.managerVM.currentReferenceProperty().addListener(referenceVMListener);
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


