package io.github.codexrm.projectreference.view;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
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
            if ((oldReference instanceof BookReferenceVM)) {
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

                previousBookReference = new BookReferenceVM(oldReference.getId(), oldReference.getAuthorIdList(), oldReference.getTitle(),
                        oldReference.getDate(),oldReference.getNote(),
                        ((BookReferenceVM) oldReference).getPublisher(),
                        ((BookReferenceVM) oldReference).getVolume(),
                        ((BookReferenceVM) oldReference).getSeries(),
                        ((BookReferenceVM) oldReference).getAddress(),
                        ((BookReferenceVM) oldReference).getEdition(),
                        referenceManager.getAuthorLibrary());
            }
        }

        if (newReference != null) {
            if ((newReference instanceof BookReferenceVM)) {
                currentBookReference = new BookReferenceVM(
                        newReference.getId(),
                        newReference.getAuthorIdList(),
                        newReference.getTitle(),
                        newReference.getDate(),
                        newReference.getNote(),
                        ((BookReferenceVM) newReference).getPublisher(),
                        ((BookReferenceVM) newReference).getVolume(),
                        ((BookReferenceVM) newReference).getSeries(),
                        ((BookReferenceVM) newReference).getAddress(),
                        ((BookReferenceVM) newReference).getEdition(),
                        referenceManager.getAuthorLibrary());

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
        bookReference.setAuthorLibrary(referenceManager.getAuthorLibrary());

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
