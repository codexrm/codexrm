package io.github.codexrm.projectreference.view;

import java.io.IOException;
import java.net.URL;
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
    private ReferenceLibraryManagerVM referenceManager;
    private ThesisReferenceVM currentThesisReference;
    private ThesisReferenceVM previousThesisReference;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference instanceof ThesisReferenceVM)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional((oldReference).dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                school.textProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).schoolProperty());
                type.valueProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).typeProperty());
                address.textProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).addressProperty());
                referenceType.valueProperty().unbindBidirectional(((ThesisReferenceVM) oldReference).referenceTypeProperty());

                previousThesisReference = new ThesisReferenceVM(oldReference.getId(), oldReference.getAuthorIdList(), oldReference.getTitle(),
                        oldReference.getDate(),oldReference.getNote(),
                        ((ThesisReferenceVM) oldReference).getSchool(),
                        ((ThesisReferenceVM) oldReference).getType(),
                        ((ThesisReferenceVM) oldReference).getAddress(),
                        referenceManager.getAuthorLibrary());
            }
        }

        if (newReference != null) {
            if ((newReference instanceof ThesisReferenceVM)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                school.textProperty().bindBidirectional(((ThesisReferenceVM) newReference).schoolProperty());
                type.valueProperty().bindBidirectional(((ThesisReferenceVM) newReference).typeProperty());
                address.textProperty().bindBidirectional(((ThesisReferenceVM) newReference).addressProperty());
                referenceType.valueProperty().bindBidirectional(((ThesisReferenceVM) newReference).referenceTypeProperty());

                currentThesisReference = new ThesisReferenceVM(
                        newReference.getId(),
                        newReference.getAuthorIdList(),
                        newReference.getTitle(),
                        newReference.getDate(),
                        newReference.getNote(),
                        ((ThesisReferenceVM) newReference).getSchool(),
                        ((ThesisReferenceVM) newReference).getType(),
                        ((ThesisReferenceVM) newReference).getAddress(),
                        referenceManager.getAuthorLibrary());
            }
        } else {
            author.clear();
            title.clear();
            //falta date
            note.clear();
            school.clear();
            //falta type
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

    public TextField getSchool() {
        return school;
    }

    public void setSchool(TextField school) {
        this.school = school;
    }

    public ComboBox<ThesisType> getType() {
        return type;
    }

    public void setType(ComboBox<ThesisType> type) {
        this.type = type;
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
        school.setOnAction(e -> updateReference());
        type.setOnAction(e -> updateReference());
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
        school.focusedProperty().addListener(bookDetailListener);
        type.focusedProperty().addListener(bookDetailListener);
        address.focusedProperty().addListener(bookDetailListener);
    }

    private void updateReference() {
        ThesisReferenceVM thesisReference = new ThesisReferenceVM();
        thesisReference.setId(this.currentThesisReference.getId());
        thesisReference.setAuthor(author.getText());
        thesisReference.setTitle(title.getText());
        thesisReference.setDate(date.getValue());
        thesisReference.setNote(note.getText());
        thesisReference.setSchool(school.getText());
        thesisReference.setType(type.getValue());
        thesisReference.setAddress(address.getText());

        /*Esta condicional es que se pierde el foco del TextField porque se selecciona
         * una fila en la tabla, que es de tipo book y se pierden los valores anteriores
         * que estaban almacenados en la interfaz grafica*/
        if (previousThesisReference != null && thesisReference.getId() != previousThesisReference.getId()) {
            try {
                /*Aqui no tengo como determinar si hay cambios o no, con lo cual siempre salvo la informacion*/
                referenceManager.updateReference(previousThesisReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        } else if (!thesisReference.equals(currentThesisReference)) { //Sobrescribi el metodo equals
            try {
                currentThesisReference = thesisReference;
                referenceManager.updateReference(thesisReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        }
    }

    private void loadReferenceType() {
        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldValue, newValue) -> {
            if (newValue != ReferenceType.THESIS) {
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
                    case ARTICLE:
                        updatedReference = new ArticleReferenceVM();
                        break;
                    default: updatedReference = new ConferenceProceedingsReferenceVM();
                }

                if (updatedReference != null) {
                    updatedReference.setId(currentThesisReference.getId());
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
