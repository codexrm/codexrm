package io.github.codexrm.projectreference.view;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

import io.github.codexrm.projectreference.ViewModel.*;
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
    private ReferenceLibraryManagerVM referenceManager;
    private BookReferenceVM currentBookReference;
    private BookReferenceVM previousBookReference;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference instanceof ArticleReferenceVM)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional((oldReference).dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                publisher.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).publisherProperty());
                volume.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).volumeProperty());
                series.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).seriesProperty());
                address.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).addressProperty());
                edition.textProperty().unbindBidirectional(((BookReferenceVM) oldReference).editionProperty());
                referenceType.valueProperty().unbindBidirectional(((BookReferenceVM) oldReference).referenceTypeProperty());

                previousBookReference = new BookReferenceVM(oldReference.getId(), oldReference.getAuthor(), oldReference.getTitle(),
                        oldReference.getDate(),oldReference.getNote(),
                        ((BookReferenceVM) oldReference).getPublisher(),
                        ((BookReferenceVM) oldReference).getVolume(),
                        ((BookReferenceVM) oldReference).getSeries(),
                        ((BookReferenceVM) oldReference).getAddress(),
                        ((BookReferenceVM) oldReference).getEdition());
            }
        }

        if (newReference != null) {
            if ((newReference instanceof BookReferenceVM)) {
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

                currentBookReference = new BookReferenceVM(
                        newReference.getId(),
                        newReference.getAuthor(),
                        newReference.getTitle(),
                        newReference.getDate(),
                        newReference.getNote(),
                        ((BookReferenceVM) newReference).getPublisher(),
                        ((BookReferenceVM) newReference).getVolume(),
                        ((BookReferenceVM) newReference).getSeries(),
                        ((BookReferenceVM) newReference).getAddress(),
                        ((BookReferenceVM) newReference).getEdition());
            }
        } else {
            author.clear();
            title.clear();
            //falta date
            note.clear();
            publisher.clear();
            volume.clear();
            series.clear();
            address.clear();
            edition.clear();
        }
    };

    public TextField getAuthor() {
        return author;
    }

    public void setAuthor(TextField author) {
        this.author = author;
    }

    public TextField getTitle() {
        return title;
    }

    public void setTitle(TextField title) {
        this.title = title;
    }

    public DatePicker getDate() {
        return date;
    }

    public void setDate(DatePicker date) {
        this.date = date;
    }

    public TextField getNote() {
        return note;
    }

    public void setNote(TextField note) {
        this.note = note;
    }

    public TextField getPublisher() {
        return publisher;
    }

    public void setPublisher(TextField publisher) {
        this.publisher = publisher;
    }

    public TextField getVolume() {
        return volume;
    }

    public void setVolume(TextField volume) {
        this.volume = volume;
    }

    public TextField getSeries() {
        return series;
    }

    public void setSeries(TextField series) {
        this.series = series;
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

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType.getSelectionModel().select(referenceType);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReferenceType();
        initializeFocusLost();
        initializeOnAction();
    }

    public void setDataModel(ReferenceLibraryManagerVM dataViewModel) {
        if (this.referenceManager != null) {
            this.referenceManager.currentReferenceProperty().removeListener(referenceVMListener);
        }

        this.referenceManager = dataViewModel;
        this.referenceManager.currentReferenceProperty().addListener(referenceVMListener);
    }

    private void initializeOnAction() {
        author.setOnAction(e -> updateReference());
        title.setOnAction(e -> updateReference());
        date.setOnAction(e -> updateReference());
        note.setOnAction(e -> updateReference());
        publisher.setOnAction(e -> updateReference());
        volume.setOnAction(e -> updateReference());
        series.setOnAction(e -> updateReference());
        address.setOnAction(e -> updateReference());
        edition.setOnAction(e -> updateReference());
    }

    private void initializeFocusLost() {
        ChangeListener<Boolean> bookDetailListener = (observable, oldValue, newValue) -> {
            if (newValue != null && !newValue) {
                updateReference();
            }
        };

        author.focusedProperty().addListener(bookDetailListener);
        title.focusedProperty().addListener(bookDetailListener);
        date.focusedProperty().addListener(bookDetailListener);
        note.focusedProperty().addListener(bookDetailListener);
        publisher.focusedProperty().addListener(bookDetailListener);
        volume.focusedProperty().addListener(bookDetailListener);
        series.focusedProperty().addListener(bookDetailListener);
        address.focusedProperty().addListener(bookDetailListener);
        edition.focusedProperty().addListener(bookDetailListener);
    }

    private void updateReference() {
        BookReferenceVM bookReference = new BookReferenceVM();
        bookReference.setId(this.currentBookReference.getId());
        bookReference.setAuthor(author.getText());
        bookReference.setTitle(title.getText());
        bookReference.setDate(date.getValue());
        bookReference.setNote(note.getText());
        bookReference.setPublisher(publisher.getText());
        bookReference.setVolume(volume.getText());
        bookReference.setSeries(series.getText());
        bookReference.setAddress(address.getText());
        bookReference.setEdition(edition.getText());

        /*Esta condicional es que se pierde el foco del TextField porque se selecciona
         * una fila en la tabla, que es de tipo book y se pierden los valores anteriores
         * que estaban almacenados en la interfaz grafica*/
        if (previousBookReference != null && bookReference.getId() != previousBookReference.getId()) {
            try {
                /*Aqui no tengo como determinar si hay cambios o no, con lo cual siempre salvo la informacion*/
                referenceManager.updateReference(previousBookReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        } else if (!bookReference.equals(currentBookReference)) { //Sobrescribi el metodo equals
            try {
                currentBookReference = bookReference;
                referenceManager.updateReference(bookReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        }
    }

    private void loadReferenceType() {
        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldValue, newValue) -> {
            if (newValue != ReferenceType.BOOK) {
                ReferenceVM updatedReference = null;
                switch (newValue) { // Aqui se podria hacer un factory que devuelva un objeto dado el tipo
                    case ARTICLE:
                        updatedReference = new ArticleReferenceVM();
                        break;
                    case BOOKSECTION:
                        updatedReference = new BookSectionReferenceVM();
                        break;
                    case BOOKLET:
                        updatedReference = new BookLetReferenceVM();
                        break;
                    case THESIS:
                        updatedReference = new ThesisReferenceVM();
                        break;
                    default: updatedReference = new ConferenceProceedingsReferenceVM();
                }

                if (updatedReference != null) {
                    updatedReference.setId(currentBookReference.getId());
                }

                try {
                    referenceManager.replaceReferenceType(updatedReference);
                } catch (IOException e) {
                    /* Mostrar algun dialogo de error al usuario */
                    e.printStackTrace();
                }
            }
        };

        referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
    }

}
