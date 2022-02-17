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

public class DetailsBookLetReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private TextField howpublished;

    @FXML
    private TextField address;
    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM referenceManager;
    private BookLetReferenceVM currentBookLetReference;
    private BookLetReferenceVM previousBookLetReference;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference instanceof BookLetReferenceVM)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional((oldReference).dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                howpublished.textProperty().unbindBidirectional(((BookLetReferenceVM) oldReference).howpublishedProperty());
                address.textProperty().unbindBidirectional(((BookLetReferenceVM) oldReference).addressProperty());
                referenceType.valueProperty().unbindBidirectional(((BookLetReferenceVM) oldReference).referenceTypeProperty());

                previousBookLetReference = new BookLetReferenceVM(oldReference.getId(), oldReference.getAuthor(), oldReference.getTitle(),
                        oldReference.getDate(),oldReference.getNote(),
                        ((BookLetReferenceVM) oldReference).getHowpublished(),
                        ((BookLetReferenceVM) oldReference).getAddress());
            }
        }

        if (newReference != null) {
            if ((newReference instanceof BookLetReferenceVM)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                howpublished.textProperty().bindBidirectional(((BookLetReferenceVM) newReference).howpublishedProperty());
                address.textProperty().bindBidirectional(((BookLetReferenceVM) newReference).addressProperty());
                referenceType.valueProperty().bindBidirectional(((BookLetReferenceVM) newReference).referenceTypeProperty());

                currentBookLetReference = new BookLetReferenceVM(
                        newReference.getId(),
                        newReference.getAuthor(),
                        newReference.getTitle(),
                        newReference.getDate(),
                        newReference.getNote(),
                        ((BookLetReferenceVM) newReference).getHowpublished(),
                        ((BookLetReferenceVM) newReference).getAddress());
            }
        } else {
            author.clear();
            title.clear();
            //falta date
            note.clear();
            howpublished.clear();
            address.clear();
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

    public TextField getHowpublished() {
        return howpublished;
    }

    public void setHowpublished(TextField howpublished) {
        this.howpublished = howpublished;
    }

    public TextField getAddress() {
        return address;
    }

    public void setAddress(TextField address) {
        this.address = address;
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
        howpublished.setOnAction(e -> updateReference());
        address.setOnAction(e -> updateReference());
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
        howpublished.focusedProperty().addListener(bookDetailListener);
        address.focusedProperty().addListener(bookDetailListener);
    }

    private void updateReference() {
        BookLetReferenceVM bookLetReference = new BookLetReferenceVM();
        bookLetReference.setId(this.currentBookLetReference.getId());
        bookLetReference.setAuthor(author.getText());
        bookLetReference.setTitle(title.getText());
        bookLetReference.setDate(date.getValue());
        bookLetReference.setNote(note.getText());
        bookLetReference.setHowpublished(howpublished.getText());
        bookLetReference.setAddress(address.getText());

        /*Esta condicional es que se pierde el foco del TextField porque se selecciona
         * una fila en la tabla, que es de tipo book y se pierden los valores anteriores
         * que estaban almacenados en la interfaz grafica*/
        if (previousBookLetReference != null && bookLetReference.getId() != previousBookLetReference.getId()) {
            try {
                /*Aqui no tengo como determinar si hay cambios o no, con lo cual siempre salvo la informacion*/
                referenceManager.updateReference(previousBookLetReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        } else if (!bookLetReference.equals(currentBookLetReference)) { //Sobrescribi el metodo equals
            try {
                currentBookLetReference = bookLetReference;
                referenceManager.updateReference(bookLetReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        }
    }

    private void loadReferenceType() {
        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldValue, newValue) -> {
            if (newValue != ReferenceType.BOOKLET) {
                ReferenceVM updatedReference = null;
                switch (newValue) { // Aqui se podria hacer un factory que devuelva un objeto dado el tipo
                    case BOOK:
                        updatedReference = new BookReferenceVM();
                        break;
                    case BOOKSECTION:
                        updatedReference = new BookSectionReferenceVM();
                        break;
                    case ARTICLE:
                        updatedReference = new ArticleReferenceVM();
                        break;
                    case THESIS:
                        updatedReference = new ThesisReferenceVM();
                        break;
                    default: updatedReference = new ConferenceProceedingsReferenceVM();
                }

                if (updatedReference != null) {
                    updatedReference.setId(currentBookLetReference.getId());
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
