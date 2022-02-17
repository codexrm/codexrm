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

    private ReferenceLibraryManagerVM referenceManager;
    private ConferenceProceedingsReferenceVM currentConferenceProceedingsReference;
    private ConferenceProceedingsReferenceVM previousConferenceProceedingsReference;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference instanceof ConferenceProceedingsReferenceVM)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional((oldReference).dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                volume.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).volumeProperty());
                serie.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).serieProperty());
                address.textProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).addressProperty());
                referenceType.valueProperty().unbindBidirectional(((ConferenceProceedingsReferenceVM) oldReference).referenceTypeProperty());

                previousConferenceProceedingsReference = new ConferenceProceedingsReferenceVM(oldReference.getId(), oldReference.getAuthor(), oldReference.getTitle(),
                        oldReference.getDate(),oldReference.getNote(),
                        ((ConferenceProceedingsReferenceVM) oldReference).getVolume(),
                        ((ConferenceProceedingsReferenceVM) oldReference).getSerie(),
                        ((ConferenceProceedingsReferenceVM) oldReference).getAddress());
            }
        }

        if (newReference != null) {
            if ((newReference instanceof ConferenceProceedingsReferenceVM)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                volume.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).volumeProperty());
                serie.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).serieProperty());
                address.textProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).addressProperty());
                referenceType.valueProperty().bindBidirectional(((ConferenceProceedingsReferenceVM) newReference).referenceTypeProperty());

                currentConferenceProceedingsReference = new ConferenceProceedingsReferenceVM(
                        newReference.getId(),
                        newReference.getAuthor(),
                        newReference.getTitle(),
                        newReference.getDate(),
                        newReference.getNote(),
                        ((ConferenceProceedingsReferenceVM) newReference).getVolume(),
                        ((ConferenceProceedingsReferenceVM) newReference).getSerie(),
                        ((ConferenceProceedingsReferenceVM) newReference).getAddress());
            }
        } else {
            author.clear();
            title.clear();
            //falta date
            note.clear();
            volume.clear();
            serie.clear();
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

    public TextField getVolume() {
        return volume;
    }

    public void setVolume(TextField volume) {
        this.volume = volume;
    }

    public TextField getSerie() {
        return serie;
    }

    public void setSerie(TextField serie) {
        this.serie = serie;
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
        volume.setOnAction(e -> updateReference());
        serie.setOnAction(e -> updateReference());
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
        volume.focusedProperty().addListener(bookDetailListener);
        serie.focusedProperty().addListener(bookDetailListener);
        address.focusedProperty().addListener(bookDetailListener);
    }

    private void updateReference() {
        ConferenceProceedingsReferenceVM conferenceProceedingsReference = new ConferenceProceedingsReferenceVM();
        conferenceProceedingsReference.setId(this.currentConferenceProceedingsReference.getId());
        conferenceProceedingsReference.setAuthor(author.getText());
        conferenceProceedingsReference.setTitle(title.getText());
        conferenceProceedingsReference.setDate(date.getValue());
        conferenceProceedingsReference.setNote(note.getText());
        conferenceProceedingsReference.setVolume(volume.getText());
        conferenceProceedingsReference.setSerie(serie.getText());
        conferenceProceedingsReference.setAddress(address.getText());

        /*Esta condicional es que se pierde el foco del TextField porque se selecciona
         * una fila en la tabla, que es de tipo book y se pierden los valores anteriores
         * que estaban almacenados en la interfaz grafica*/
        if (previousConferenceProceedingsReference != null && conferenceProceedingsReference.getId() != previousConferenceProceedingsReference.getId()) {
            try {
                /*Aqui no tengo como determinar si hay cambios o no, con lo cual siempre salvo la informacion*/
                referenceManager.updateReference(previousConferenceProceedingsReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        } else if (!conferenceProceedingsReference.equals(currentConferenceProceedingsReference)) { //Sobrescribi el metodo equals
            try {
                currentConferenceProceedingsReference = conferenceProceedingsReference;
                referenceManager.updateReference(conferenceProceedingsReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        }
    }

    private void loadReferenceType() {
        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldValue, newValue) -> {
            if (newValue != ReferenceType.CONFERENCEPROCEEDINGS) {
                ReferenceVM updatedReference = null;
                switch (newValue) { // Aqui se podria hacer un factory que devuelva un objeto dado el tipo
                    case BOOK:
                        updatedReference = new BookReferenceVM();
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
                    default: updatedReference = new ArticleReferenceVM();
                }

                if (updatedReference != null) {
                    updatedReference.setId(currentConferenceProceedingsReference.getId());
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
