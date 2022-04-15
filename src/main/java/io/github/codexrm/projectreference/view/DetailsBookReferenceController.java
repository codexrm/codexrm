package io.github.codexrm.projectreference.view;

import java.net.URL;

import java.time.LocalDate;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.viewmodel.BookReferenceVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceLibraryManagerVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceType;
import io.github.codexrm.projectreference.viewmodel.ReferenceVM;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DetailsBookReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private TextField publisher;

    @FXML
    private TextField volume;

    @FXML
    private TextField series;

    @FXML
    private TextField address;

    @FXML
    private TextField edition;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM maganerVM;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference.getClass() == BookReferenceVM.class)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional(oldReference.dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                publisher.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).publisherProperty());
                volume.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).volumeProperty());
                series.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).seriesProperty());
                address.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).addressProperty());
                edition.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).editionProperty());

                referenceType.valueProperty().unbindBidirectional(((BookReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == BookReferenceVM.class)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                publisher.textProperty().bindBidirectional(((BookReferenceVM) newReference).publisherProperty());
                volume.textProperty().bindBidirectional(((BookReferenceVM) newReference).volumeProperty());
                series.textProperty().bindBidirectional(((BookReferenceVM) newReference).seriesProperty());
                address.textProperty().bindBidirectional(((BookReferenceVM) newReference).addressProperty());
                edition.textProperty().bindBidirectional(((BookReferenceVM) newReference).editionProperty());

                referenceType.valueProperty().bindBidirectional(((BookReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            date.setValue(LocalDate.now());
            note.clear();
            publisher.clear();
            volume.clear();
            series.clear();
            address.clear();
            edition.clear();
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

    public void setDate(LocalDate date) {
        this.date.setValue(date);
    }

    public String getNote() {
        return note.getText();
    }

    public void setNote(String note) {
        this.note.setText(note);
    }

    public String getPublisher() {
        return publisher.getText();
    }

    public void setPublisher(String publisher) {
        this.publisher.setText(publisher);
    }

    public String getVolume() {
        return volume.getText();
    }

    public void setVolume(String volume) {
        this.volume.setText(volume);
    }

    public String getSeries() {
        return series.getText();
    }

    public void setSeries(String series) {
        this.series.setText(series);
    }

    public String getAddress() {
        return address.getText();
    }

    public void setAddress(String address) {this.address.setText(address);}

    public String getEdition() {
        return edition.getText();
    }

    public void setEdition(String edition) {this.edition.setText(edition);}

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType.getSelectionModel().select(referenceType);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReferenceType();
    }

    public void setDataModel(ReferenceLibraryManagerVM dataViewModel) {
        if (this.maganerVM != null) {
            this.maganerVM.currentReferenceProperty().removeListener(referenceVMListener);
        }

        this.maganerVM = dataViewModel;
        this.maganerVM.currentReferenceProperty().addListener(referenceVMListener);
    }

    private void loadReferenceType() {
        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldReferenceType, newReferenceType) -> {
            if (newReferenceType != ReferenceType.BOOK) {
                maganerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };

        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}
