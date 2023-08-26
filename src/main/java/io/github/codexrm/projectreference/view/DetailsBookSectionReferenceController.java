package io.github.codexrm.projectreference.view;

import java.net.URL;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.model.enums.*;
import io.github.codexrm.projectreference.model.utils.FieldValidations;
import io.github.codexrm.projectreference.viewmodel.BookSectionReferenceVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceLibraryManagerVM;
import io.github.codexrm.projectreference.viewmodel.ReferenceVM;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DetailsBookSectionReferenceController implements Initializable {

    @FXML
    private TextField chapter, pages, author, editor, title, publisher, year, volume, number, series, address, edition, isbn, note;

    @FXML
    private ComboBox<BookSectionType> type;

    @FXML
    private ComboBox<Months> month;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM managerVM;

    private FieldValidations validations = new FieldValidations();

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {

        if (oldReference != null) {
            if ((oldReference.getClass() == BookSectionReferenceVM.class)) {

                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                year.textProperty().unbindBidirectional(oldReference.yearProperty());
                month.valueProperty().unbindBidirectional(oldReference.monthProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());

                chapter.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).chapterProperty());
                pages.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).pagesProperty());
                author.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).authorProperty());
                editor.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).editorProperty());
                publisher.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).publisherProperty());
                volume.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).volumeProperty());
                number.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).numberProperty());
                series.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).seriesProperty());
                type.valueProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).typeProperty());
                address.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).addressProperty());
                edition.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).editionProperty());
                isbn.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).isbnProperty());

                referenceType.valueProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).referenceTypeProperty());
            }
        }

        if (newReference != null) {
            if ((newReference.getClass() == BookSectionReferenceVM.class)) {

                title.textProperty().bindBidirectional(newReference.titleProperty());
                year.textProperty().bindBidirectional(newReference.yearProperty());
                month.valueProperty().bindBidirectional(newReference.monthProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());

                chapter.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).chapterProperty());
                pages.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).pagesProperty());
                author.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).authorProperty());
                editor.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).editorProperty());
                publisher.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).publisherProperty());
                volume.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).volumeProperty());
                number.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).numberProperty());
                series.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).seriesProperty());
                type.valueProperty().bindBidirectional(((BookSectionReferenceVM) newReference).typeProperty());
                address.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).addressProperty());
                edition.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).editionProperty());
                isbn.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).isbnProperty());

                referenceType.valueProperty().bindBidirectional(((BookSectionReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            chapter.clear();
            pages.clear();
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

    public TextField getChapter() {
        return chapter;
    }

    public void setChapter(TextField chapter) {
        this.chapter = chapter;
    }

    public TextField getPages() {
        return pages;
    }

    public void setPages(TextField pages) {
        this.pages = pages;
    }

    public TextField getAuthor() {
        return author;
    }

    public void setAuthor(TextField author) {
        this.author = author;
    }

    public TextField getEditor() {
        return editor;
    }

    public void setEditor(TextField editor) {
        this.editor = editor;
    }

    public TextField getTitle() {
        return title;
    }

    public void setTitle(TextField title) {
        this.title = title;
    }

    public TextField getPublisher() {
        return publisher;
    }

    public void setPublisher(TextField publisher) {
        this.publisher = publisher;
    }

    public TextField getYear() {
        return year;
    }

    public void setYear(TextField year) {
        this.year = year;
    }

    public TextField getVolume() {
        return volume;
    }

    public void setVolume(TextField volume) {
        this.volume = volume;
    }

    public TextField getNumber() {
        return number;
    }

    public void setNumber(TextField number) {
        this.number = number;
    }

    public TextField getSeries() {
        return series;
    }

    public void setSeries(TextField series) {
        this.series = series;
    }

    public ComboBox<BookSectionType> getType() {
        return type;
    }

    public void setType(ComboBox<BookSectionType> type) {
        this.type = type;
    }

    public TextField getAddress() {
        return address;
    }

    public void setAddress(TextField address) {
        this.address = address;
    }

    public TextField getEdition() {
        return edition;
    }

    public void setEdition(TextField edition) {
        this.edition = edition;
    }

    public ComboBox<Months> getMonth() {
        return month;
    }

    public void setMonth(ComboBox<Months> month) {
        this.month = month;
    }

    public TextField getIsbn() {
        return isbn;
    }

    public void setIsbn(TextField isbn) {
        this.isbn = isbn;
    }

    public TextField getNote() {
        return note;
    }

    public void setNote(TextField note) {
        this.note = note;
    }

    public ComboBox<ReferenceType> getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(ComboBox<ReferenceType> referenceType) {
        this.referenceType = referenceType;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReferenceType();
        loadBookSectionType();
        loadMonths();
    }

    public void setDataModel(ReferenceLibraryManagerVM dataViewModel) {

        if (this.managerVM != null) {
            this.managerVM.currentReferenceProperty().removeListener(referenceVMListener);
        }

        this.managerVM = dataViewModel;
        this.managerVM.currentReferenceProperty().addListener(referenceVMListener);
    }

    private void loadMonths() {
        month.getItems().add(null);
        month.getItems().addAll(Months.values());
    }

    public boolean validateFields(BookSectionReferenceVM bookSection) {
        boolean isValidate = true;

        if (!validations.validateChapterRequired(bookSection.getChapter())) {
            bookSection.setChapter("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validatePagesRequired(bookSection.getPages())) {
            bookSection.setPages("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateAuthorOrEditorRequired(bookSection.getAuthor())) {
            bookSection.setAuthor("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateAuthorOrEditorRequired(bookSection.getEditor())) {
            bookSection.setEditor("CodexRM:Error");
            isValidate = false;
        }

        if (bookSection.getTitle().equals("No Title") || bookSection.getTitle().isBlank() || bookSection.getTitle().equals("CodexRM:Error")) {
            bookSection.setTitle("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateFieldRequired(bookSection.getPublisher())) {
            bookSection.setPublisher("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateYearRequired(bookSection.getYear())) {
            bookSection.setYear("CodexRM:Error");
            isValidate = false;
        }

        if(!validations.validateChapterOrVolume(bookSection.getVolume())){
            bookSection.setVolume("CodexRM:Error");
            isValidate = false;
        }

        if(!validations.validateSeries(bookSection.getSeries())){
            bookSection.setSeries("CodexRM:Error");
            isValidate = false;
        }

        if(!validations.validateAddress(bookSection.getAddress())){
            bookSection.setAddress("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateNumber(bookSection.getNumber())) {
            bookSection.setNumber("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateEdition(bookSection.getEdition())) {
            bookSection.setEdition("CodexRM:Error");
            isValidate = false;
        }

        if (!validations.validateIsbn(bookSection.getIsbn())) {
            bookSection.setIsbn("CodexRM:Error");
            isValidate = false;
        }

        return isValidate;
    }

    private void loadBookSectionType() {
        type.getItems().add(null);
        type.getItems().addAll(BookSectionType.values());
    }

    private void loadReferenceType() {

        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldReferenceType, newReferenceType) -> {
            if (newReferenceType != ReferenceType.BOOKSECTION) {
                managerVM.replaceCurrentReferenceType(newReferenceType);
            }
        };
        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }
}