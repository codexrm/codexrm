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

public class DetailsArticleReferenceController implements Initializable {

    @FXML
    private TextField author;

    @FXML
    private TextField title;

    @FXML
    private DatePicker date;

    @FXML
    private TextField note;

    @FXML
    private TextField journal;

    @FXML
    private TextField volume;

    @FXML
    private TextField number;

    @FXML
    private TextField pages;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM referenceManager;
    private ArticleReferenceVM currentArticleReference;
    private ArticleReferenceVM previousArticleReference;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference instanceof ArticleReferenceVM)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional((oldReference).dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                journal.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).journalProperty());
                volume.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).volumeProperty());
                number.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).numberProperty());
                pages.textProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).pagesProperty());
                referenceType.valueProperty().unbindBidirectional(((ArticleReferenceVM) oldReference).referenceTypeProperty());

                previousArticleReference = new ArticleReferenceVM(oldReference.getId(), oldReference.getAuthorIdList(), oldReference.getTitle(),
                        oldReference.getDate(),oldReference.getNote(),
                        ((ArticleReferenceVM) oldReference).getJournal(),
                        ((ArticleReferenceVM) oldReference).getVolume(),
                        ((ArticleReferenceVM) oldReference).getNumber(),
                        ((ArticleReferenceVM) oldReference).getPages(),
                        referenceManager.getAuthorLibrary());
            }
        }

        if (newReference != null) {
            if ((newReference instanceof ArticleReferenceVM)) {
                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                journal.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).journalProperty());
                volume.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).volumeProperty());
                number.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).numberProperty());
                pages.textProperty().bindBidirectional(((ArticleReferenceVM) newReference).pagesProperty());
                referenceType.valueProperty().bindBidirectional(((ArticleReferenceVM) newReference).referenceTypeProperty());

                currentArticleReference = new ArticleReferenceVM(
                        newReference.getId(),
                        newReference.getAuthorIdList(),
                        newReference.getTitle(),
                        newReference.getDate(),
                        newReference.getNote(),
                        ((ArticleReferenceVM) newReference).getJournal(),
                        ((ArticleReferenceVM) newReference).getVolume(),
                        ((ArticleReferenceVM) newReference).getNumber(),
                        ((ArticleReferenceVM) newReference).getPages(),
                        referenceManager.getAuthorLibrary());
            }
        } else {
            author.clear();
            title.clear();
            //falta date
            note.clear();
            journal.clear();
            volume.clear();
            number.clear();
            pages.clear();
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

    public TextField getJournal() {
        return journal;
    }

    public void setJournal(TextField journal) {
        this.journal = journal;
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

    public TextField getPages() {
        return pages;
    }

    public void setPages(TextField pages) {
        this.pages = pages;
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
        journal.setOnAction(e -> updateReference());
        volume.setOnAction(e -> updateReference());
        number.setOnAction(e -> updateReference());
        pages.setOnAction(e -> updateReference());
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
        journal.focusedProperty().addListener(bookDetailListener);
        volume.focusedProperty().addListener(bookDetailListener);
        number.focusedProperty().addListener(bookDetailListener);
        pages.focusedProperty().addListener(bookDetailListener);
    }

    private void updateReference() {
        ArticleReferenceVM articleReference = new ArticleReferenceVM();
        articleReference.setId(this.currentArticleReference.getId());
        articleReference.setAuthor(author.getText());
        articleReference.setTitle(title.getText());
        articleReference.setDate(date.getValue());
        articleReference.setNote(note.getText());
        articleReference.setJournal(journal.getText());
        articleReference.setVolume(volume.getText());
        articleReference.setNumber(number.getText());
        articleReference.setNumber(pages.getText());

        /*Esta condicional es que se pierde el foco del TextField porque se selecciona
         * una fila en la tabla, que es de tipo book y se pierden los valores anteriores
         * que estaban almacenados en la interfaz grafica*/
        if (previousArticleReference != null && articleReference.getId() != previousArticleReference.getId()) {
            try {
                /*Aqui no tengo como determinar si hay cambios o no, con lo cual siempre salvo la informacion*/
                referenceManager.updateReference(previousArticleReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        } else if (!articleReference.equals(currentArticleReference)) { //Sobrescribi el metodo equals
            try {
                currentArticleReference = articleReference;
                referenceManager.updateReference(articleReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        }
    }

    private void loadReferenceType() {
        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldValue, newValue) -> {
            if (newValue != ReferenceType.ARTICLE) {
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
                    default: updatedReference = new ConferenceProceedingsReferenceVM();
                }

                if (updatedReference != null) {
                    updatedReference.setId(currentArticleReference.getId());
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
