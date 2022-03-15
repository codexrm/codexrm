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

public class DetailsBookSectionReferenceController implements Initializable {

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
    private TextField chapter;

    @FXML
    private TextField pages;

    @FXML
    private ComboBox<ReferenceType> referenceType;

    private ReferenceLibraryManagerVM referenceManager;
    private BookSectionReferenceVM currentBookSectionReference;
    private BookSectionReferenceVM previousBookSectionReference;

    private final ChangeListener<ReferenceVM> referenceVMListener = (obs, oldReference, newReference) -> {
        if (oldReference != null) {
            if ((oldReference instanceof BookSectionReferenceVM)) {
                author.textProperty().unbindBidirectional(oldReference.authorProperty());
                title.textProperty().unbindBidirectional(oldReference.titleProperty());
                date.valueProperty().unbindBidirectional((oldReference).dateProperty());
                note.textProperty().unbindBidirectional(oldReference.noteProperty());
                publisher.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).publisherProperty());
                volume.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).volumeProperty());
                series.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).seriesProperty());
                address.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).addressProperty());
                edition.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).editionProperty());
                chapter.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).chapterProperty());
                pages.textProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).pagesProperty());
                referenceType.valueProperty().unbindBidirectional(((BookSectionReferenceVM) oldReference).referenceTypeProperty());

                previousBookSectionReference = new BookSectionReferenceVM(oldReference.getId(), oldReference.getAuthorIdList(), oldReference.getTitle(),
                        oldReference.getDate(),oldReference.getNote(),
                        ((BookSectionReferenceVM) oldReference).getPublisher(),
                        ((BookSectionReferenceVM) oldReference).getVolume(),
                        ((BookSectionReferenceVM) oldReference).getSeries(),
                        ((BookSectionReferenceVM) oldReference).getAddress(),
                        ((BookSectionReferenceVM) oldReference).getEdition(),
                        ((BookSectionReferenceVM) oldReference).getChapter(),
                        ((BookSectionReferenceVM) oldReference).getPages(),
                        referenceManager.getAuthorLibrary());
            }
        }

        if (newReference != null) {
            if ((newReference instanceof BookSectionReferenceVM)) {
                currentBookSectionReference = new BookSectionReferenceVM(
                        newReference.getId(),
                        newReference.getAuthorIdList(),
                        newReference.getTitle(),
                        newReference.getDate(),
                        newReference.getNote(),
                        ((BookSectionReferenceVM) newReference).getPublisher(),
                        ((BookSectionReferenceVM) newReference).getVolume(),
                        ((BookSectionReferenceVM) newReference).getSeries(),
                        ((BookSectionReferenceVM) newReference).getAddress(),
                        ((BookSectionReferenceVM) newReference).getEdition(),
                        ((BookSectionReferenceVM) newReference).getChapter(),
                        ((BookSectionReferenceVM) newReference).getPages(),
                        referenceManager.getAuthorLibrary());

                author.textProperty().bindBidirectional(newReference.authorProperty());
                title.textProperty().bindBidirectional(newReference.titleProperty());
                date.valueProperty().bindBidirectional(newReference.dateProperty());
                note.textProperty().bindBidirectional(newReference.noteProperty());
                publisher.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).publisherProperty());
                volume.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).volumeProperty());
                series.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).seriesProperty());
                address.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).addressProperty());
                edition.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).editionProperty());
                chapter.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).chapterProperty());
                pages.textProperty().bindBidirectional(((BookSectionReferenceVM) newReference).pagesProperty());
                referenceType.valueProperty().bindBidirectional(((BookSectionReferenceVM) newReference).referenceTypeProperty());
            }
        } else {
            author.clear();
            title.clear();
            date.setValue(null);
            note.clear();
            publisher.clear();
            volume.clear();
            series.clear();
            address.clear();
            edition.clear();
            chapter.clear();
            pages.clear();
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

    public String getChapter() {
        return chapter.getText();
    }

    public void setChapter(String chapter) {this.chapter.setText(chapter);}

    public String getPages() {
        return pages.getText();
    }

    public void setPages(String pages) {this.pages.setText(pages);}

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
        chapter.setOnAction(e -> updateReference());
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
        publisher.focusedProperty().addListener(bookDetailListener);
        volume.focusedProperty().addListener(bookDetailListener);
        series.focusedProperty().addListener(bookDetailListener);
        address.focusedProperty().addListener(bookDetailListener);
        edition.focusedProperty().addListener(bookDetailListener);
        chapter.focusedProperty().addListener(bookDetailListener);
        pages.focusedProperty().addListener(bookDetailListener);
    }

    private void updateReference() {
        BookSectionReferenceVM bookSectionReference = new BookSectionReferenceVM();
        bookSectionReference.setId(this.currentBookSectionReference.getId());
        bookSectionReference.setAuthor(author.getText());
        bookSectionReference.setTitle(title.getText());
        bookSectionReference.setDate(date.getValue());
        bookSectionReference.setNote(note.getText());
        bookSectionReference.setPublisher(publisher.getText());
        bookSectionReference.setVolume(volume.getText());
        bookSectionReference.setSeries(series.getText());
        bookSectionReference.setAddress(address.getText());
        bookSectionReference.setEdition(edition.getText());
        bookSectionReference.setChapter(chapter.getText());
        bookSectionReference.setPages(pages.getText());
        bookSectionReference.setAuthorLibrary(referenceManager.getAuthorLibrary());

        /*Esta condicional es que se pierde el foco del TextField porque se selecciona
         * una fila en la tabla, que es de tipo book y se pierden los valores anteriores
         * que estaban almacenados en la interfaz grafica*/
        if (previousBookSectionReference != null && bookSectionReference.getId() != previousBookSectionReference.getId()) {
            try {
                /*Aqui no tengo como determinar si hay cambios o no, con lo cual siempre salvo la informacion*/
                referenceManager.updateReference(previousBookSectionReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        } else if (!bookSectionReference.equals(currentBookSectionReference)) { //Sobrescribi el metodo equals
            try {
                currentBookSectionReference = bookSectionReference;
                referenceManager.updateReference(bookSectionReference);
            } catch (IOException e) {
                /* Mostrar algun dialogo de error al usuario */
                e.printStackTrace();
            }
        }
    }

    private void loadReferenceType() {
        referenceType.getItems().addAll(ReferenceType.values());

        ChangeListener<ReferenceType> referenceTypeListener = (options, oldValue, newValue) -> {
            if (newValue != ReferenceType.BOOKSECTION) {
                ReferenceVM updatedReference = null;
                switch (newValue) { // Aqui se podria hacer un factory que devuelva un objeto dado el tipo
                    case BOOK:
                        updatedReference = new BookReferenceVM();
                        break;
                    case ARTICLE:
                        updatedReference = new ArticleReferenceVM();
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
                    updatedReference.setId(currentBookSectionReference.getId());
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
