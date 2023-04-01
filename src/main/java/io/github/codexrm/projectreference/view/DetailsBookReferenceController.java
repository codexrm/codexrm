package io.github.codexrm.projectreference.view;

import java.net.URL;

import java.util.ResourceBundle;

import io.github.codexrm.projectreference.model.enums.Months;
import io.github.codexrm.projectreference.viewmodel.BookReferenceVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceLibraryManagerVM;
import io.github.codexrm.projectreference.model.enums.ReferenceType;
import io.github.codexrm.projectreference.viewmodel.ReferenceVM;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DetailsBookReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField editor;

    @FXML
    private TextField title;

    @FXML
    private TextField publisher;

    @FXML
    private TextField year;

    @FXML
    private TextField volume;

    @FXML
    private TextField number;

    @FXML
    private TextField series;

    @FXML
    private TextField address;

    @FXML
    private TextField edition;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private TextField isbn;

    @FXML
    private TextField note;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM maganerVM;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == BookReferenceVM.class)) {

                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                year.textProperty().unbindBidirectional(oldReference.yearProperty());
                month.valueProperty().unbindBidirectional(oldReference.monthProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                author.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).authorProperty());
                editor.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).editorProperty());
                publisher.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).publisherProperty());
                volume.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).volumeProperty());
                number.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).numberProperty());
                series.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).seriesProperty());
                address.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).addressProperty());
                edition.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).editionProperty());
                isbn.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).isbnProperty());

                referenceType.valueProperty().unbindBidirectional(((BookReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == BookReferenceVM.class)) {

                title.textProperty().bindBidirectional(newReference.titleProperty());
                year.textProperty().bindBidirectional(newReference.yearProperty());
                month.valueProperty().bindBidirectional(newReference.monthProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                author.textProperty().bindBidirectional(((BookReferenceVM) newReference).authorProperty());
                editor.textProperty().bindBidirectional(((BookReferenceVM) newReference).editorProperty());
                publisher.textProperty().bindBidirectional(((BookReferenceVM) newReference).publisherProperty());
                volume.textProperty().bindBidirectional(((BookReferenceVM) newReference).volumeProperty());
                number.textProperty().bindBidirectional(((BookReferenceVM) newReference).numberProperty());
                series.textProperty().bindBidirectional(((BookReferenceVM) newReference).seriesProperty());
                address.textProperty().bindBidirectional(((BookReferenceVM) newReference).addressProperty());
                edition.textProperty().bindBidirectional(((BookReferenceVM) newReference).editionProperty());
                isbn.textProperty().bindBidirectional(((BookReferenceVM) newReference).isbnProperty());

                referenceType.valueProperty().bindBidirectional(((BookReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            editor.clear();
            title.clear();
            publisher.clear();
            year.clear();
            volume.clear();
            number.clear();
            series.clear();
            address.clear();
            edition.clear();
            isbn.clear();
            note.clear();
        }
    };

    public TextField getAuthor() { return author; }

    public void setAuthor(TextField author) { this.author = author; }

    public TextField getEditor() { return editor; }

    public void setEditor(TextField editor) { this.editor = editor; }

    public TextField getTitle() { return title; }

    public void setTitle(TextField title) { this.title = title; }

    public TextField getPublisher() { return publisher; }

    public void setPublisher(TextField publisher) { this.publisher = publisher; }

    public TextField getYear() { return year; }

    public void setYear(TextField year) { this.year = year; }

    public TextField getVolume() { return volume; }

    public void setVolume(TextField volume) { this.volume = volume; }

    public TextField getNumber() { return number; }

    public void setNumber(TextField number) { this.number = number; }

    public TextField getSeries() { return series; }

    public void setSeries(TextField series) { this.series = series; }

    public TextField getAddress() { return address; }

    public void setAddress(TextField address) { this.address = address; }

    public TextField getEdition() { return edition; }

    public void setEdition(TextField edition) {this.edition = edition; }

    public ComboBox<Months> getMonth() { return month; }

    public void setMonth(ComboBox<Months> month) { this.month = month; }

    public TextField getIsbn() { return isbn; }

    public void setIsbn(TextField isbn) { this.isbn = isbn; }

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

        if (this.maganerVM != null) {
            this.maganerVM.currentReferenceProperty().removeListener(referenceVMListener);
        }
        this.maganerVM = dataViewModel;
        this.maganerVM.currentReferenceProperty().addListener(referenceVMListener);
    }

    private void loadMonths(){
        month.getItems().addAll(Months.values());
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
