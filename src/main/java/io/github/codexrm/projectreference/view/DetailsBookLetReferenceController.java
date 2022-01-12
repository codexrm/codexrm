package io.github.codexrm.projectreference.view;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import io.github.codexrm.projectreference.Controller.ReferenceLibraryManager;
import io.github.codexrm.projectreference.Enum.*;
import io.github.codexrm.projectreference.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DetailsBookLetReferenceController implements Initializable {

  private final ReferenceLibraryManager manager;
  private final RootLayoutController rootLayoutController;
  private int referenceId;
  @FXML
  private TextField author;

  @FXML
  private TextField title;

  @FXML
  private TextField year;

  @FXML
  private ComboBox<Month> month;

  @FXML
  private TextField note;

  @FXML
  private TextField howpublished;

  @FXML
  private TextField address;
  @FXML
  private ComboBox<ReferenceType> referenceType;
  private ChangeListener<ReferenceType> referenceTypeListener;

  public DetailsBookLetReferenceController(ReferenceLibraryManager manager,
      RootLayoutController rootLayoutController) {
    this.manager = manager;
    this.rootLayoutController = rootLayoutController;
  }

  public void setReferenceId(int referenceId) {
    this.referenceId = referenceId;
  }

  public String getAuthor() {
    return author.getText();
  }

  public void setAuthor(String author) {
    this.author.setText(author);
  }

  public String getTitle() {
    return title.getText();
  }

  public void setTitle(String title) {
    this.title.setText(title);
  }

  public String getYear() {
    return year.getText();
  }

  public void setYear(String year) {
    this.year.setText(year);
  }

  public Month getMonth() {
    return month.getValue();
  }

  public void setMonth(Month month) {
   this.month.getSelectionModel().select(month);
  }

  public String getNote() {
    return note.getText();
  }

  public void setNote(String note) {
    this.note.setText(note);
  }

  public String getHowpublished() {
    return howpublished.getText();
  }

  public void setHowpublished(String howpublished) {
    this.howpublished.setText(howpublished);
  }

  public String getAddress() {
    return address.getText();
  }

  public void setAddress(String address) {
    this.address.setText(address);
  }

  public void setReferenceType(ReferenceType referenceType) {
    this.referenceType.getSelectionModel().selectedItemProperty()
        .removeListener(referenceTypeListener);
    this.referenceType.getSelectionModel().select(referenceType);
    this.referenceType.getSelectionModel().selectedItemProperty()
        .addListener(referenceTypeListener);
  }

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    loadMonth();
    loadReferenceType();
    initializeOnAction();
    initializeFocusLost();
  }
  
  private void loadMonth() {
    this.month.getItems().setAll(Month.JAN, Month.FEB, Month.MAR, Month.APR, Month.MAY, Month.JUN,
        Month.JUL, Month.AUG, Month.SEP, Month.OCT, Month.NOV, Month.DEC);
  }
  private void loadReferenceType() {
    for (ReferenceType ref : ReferenceType.values()) {
      referenceType.getItems().add(ref);
    }

    referenceTypeListener = (options, oldValue, newValue) -> {
      if (newValue != ReferenceType.BOOKLET) {
        Reference updatedReference = null;
        switch (newValue) {
          case BOOK:
            updatedReference = new BookReference();
            break;
          case BOOKSECCION:
            updatedReference = new BookSectionReference();
            break;
          case ARTICLE:
            updatedReference = new ArticleReference();
            break;
          case CONFERENCEPROCEEDINGS:
            updatedReference = new ConferenceProceedingsReference();
            break;
          case THESIS:
            updatedReference = new ThesisReference();
            break;
          default:

        }

        if (updatedReference != null) {
          updatedReference.setId(referenceId);
        }

        try {
          rootLayoutController.updateReference(updatedReference, updatedReference, true);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    };

    referenceType.getSelectionModel().selectedItemProperty().addListener(referenceTypeListener);
  }

  private void initializeOnAction() {
    author.setOnAction(e -> updateReference());
    title.setOnAction(e -> updateReference());
    year.setOnAction(e -> updateReference());
    month.setOnAction(e -> updateReference());
    note.setOnAction(e -> updateReference());
    howpublished.setOnAction(e -> updateReference());
    address.setOnAction(e -> updateReference());
  }

  public boolean isDifferentReference(BookLetReference bookLet) {
    if (bookLet == null)
      return true;

    return (referenceId != bookLet.getId())
        || !Objects.equals(author.getText(), bookLet.getAuthor())
        || !Objects.equals(title.getText(), bookLet.getTitle())
        || !Objects.equals(year.getText(), bookLet.getYear())
        || !Objects.equals(month, bookLet.getMonth())
        || !Objects.equals(note.getText(), bookLet.getNote())
        || !Objects.equals(howpublished.getText(), bookLet.getHowpublished())
        || !Objects.equals(address.getText(), bookLet.getAddress());
  }

  private void updateReference() {
    BookLetReference reference = (BookLetReference) manager.getReference(referenceId);

    if (isDifferentReference(reference)) {
      try {
        rootLayoutController.updateReference(reference, null, false);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void initializeFocusLost() {
    ChangeListener<Boolean> bookDetailListener = (observable, oldValue, newValue) -> {
      if (newValue != null && !newValue) {
        updateReference();
      }
    };

    author.focusedProperty().addListener(bookDetailListener);
    title.focusedProperty().addListener(bookDetailListener);
    year.focusedProperty().addListener(bookDetailListener);
    month.focusedProperty().addListener(bookDetailListener);
    note.focusedProperty().addListener(bookDetailListener);
    howpublished.focusedProperty().addListener(bookDetailListener);
    address.focusedProperty().addListener(bookDetailListener);
  }
}
