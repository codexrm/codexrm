package io.github.codexrm.projectreference.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.Model.Controller.ReferenceLibraryManager;
import io.github.codexrm.projectreference.Model.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class RootLayoutController implements Initializable {


    private final ReferenceLibraryManager manager;
    private final ScrollPane bookDetail;
    private final ScrollPane articleDetail;
    private final ScrollPane bookSectionDetail;
    private final ScrollPane bookLetDetail;
    private final ScrollPane proceedingsDetail;
    private final ScrollPane thesisDetail;
    private final DetailsArticleReferenceController articleDetailController;
    private final DetailsBookReferenceController bookDetailController;
    private final DetailsBookSectionReferenceController bookSectionDetailController;
    private final DetailsBookLetReferenceController bookLetDetailController;
    private final DetailsConferenceProceedingsReferenceController proceedingsDetailController;
    private final DetailsThesisReferenceController thesisDetailController;
    @FXML
    private TableView<Reference> referenceTable;
    @FXML
    private AnchorPane referenceDetail;
    @FXML
    private Label noReferenceDetailInfo;
    private ChangeListener<Reference> updateViewListener;

    public RootLayoutController(ReferenceLibraryManager manager) throws IOException {
        this.manager = manager;
        this.manager.loadTables();

        FXMLLoader bookDetailLoader = new FXMLLoader();
        FXMLLoader articleDetailLoader = new FXMLLoader();
        FXMLLoader bookSectionDetailLoader = new FXMLLoader();
        FXMLLoader bookLetDetailLoader = new FXMLLoader();
        FXMLLoader proceedingsDetailLoader = new FXMLLoader();
        FXMLLoader thesisDetailLoader = new FXMLLoader();

        bookDetailLoader.setLocation(getClass().getResource("DetailsBookReference.fxml"));
        articleDetailLoader.setLocation(getClass().getResource("DetailsArticleReference.fxml"));
        bookSectionDetailLoader.setLocation(getClass().getResource("DetailsBookSeccionReference.fxml"));
        bookLetDetailLoader.setLocation(getClass().getResource("DetailsBookLetReference.fxml"));
        proceedingsDetailLoader.setLocation(getClass().getResource("DetailsConferenceReference.fxml"));
        thesisDetailLoader.setLocation(getClass().getResource("DetailsThesisReference.fxml"));

        articleDetailController = new DetailsArticleReferenceController(manager, this);
        bookDetailController = new DetailsBookReferenceController(manager, this);
        bookSectionDetailController = new DetailsBookSectionReferenceController(manager, this);
        bookLetDetailController = new DetailsBookLetReferenceController(manager, this);
        proceedingsDetailController =
                new DetailsConferenceProceedingsReferenceController(manager, this);
        thesisDetailController = new DetailsThesisReferenceController(manager, this);

        bookDetailLoader.setController(bookDetailController);
        articleDetailLoader.setController(articleDetailController);
        bookSectionDetailLoader.setController(bookSectionDetailController);
        bookLetDetailLoader.setController(bookLetDetailController);
        proceedingsDetailLoader.setController(proceedingsDetailController);
        thesisDetailLoader.setController(thesisDetailController);

        articleDetail = articleDetailLoader.load();
        bookDetail = bookDetailLoader.load();
        bookSectionDetail = bookSectionDetailLoader.load();
        bookLetDetail = bookLetDetailLoader.load();
        proceedingsDetail = proceedingsDetailLoader.load();
        thesisDetail = thesisDetailLoader.load();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadReferenceTable();
        loadReferenceDetail();
    }

    private void loadReferenceTable() {
        referenceTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        Enumeration<Integer> key = manager.getReferenceTable().keys();
        Integer keyReference;
        Reference valorReference;
        while (key.hasMoreElements()) {
            keyReference = key.nextElement();
            valorReference = manager.getReferenceTable().get(keyReference);
            referenceTable.getItems().add(valorReference);
        }
        updateViewListener = (observable, oldValue, newValue) -> {
            try {
                updateReference(oldValue, newValue, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);

        referenceTable.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DELETE) {
                ObservableList<Reference> selectedReferences =
                        referenceTable.getSelectionModel().getSelectedItems();
                deleteReference(selectedReferences);
            }
        });
    }

    private void loadReferenceDetail() {
        loadReferencePane(articleDetail);
        loadReferencePane(bookDetail);
        loadReferencePane(bookSectionDetail);
        loadReferencePane(bookLetDetail);
        loadReferencePane(proceedingsDetail);
        loadReferencePane(thesisDetail);
    }

    private void loadReferencePane(Node node) {
        node.setVisible(false);

        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);

        referenceDetail.getChildren().add(node);
    }

    public void deleteReference(ObservableList<Reference> referenceList) {
        if (referenceList != null) {

            List<Integer> referenceIdToDelete = new ArrayList<>();
            for (Reference reference : referenceList) {
                referenceIdToDelete.add(reference.getId());
            }

            referenceTable.getSelectionModel().selectedItemProperty().removeListener(updateViewListener);

            for (Integer id : referenceIdToDelete) {
                referenceTable.getItems().removeIf(reference -> reference.getId().equals(id));
                manager.getReferenceTable().remove(id);
            }

            referenceTable.refresh();

            referenceTable.getSelectionModel().clearSelection();

            referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);

            showReferenceDetails(noReferenceDetailInfo);

            try {
                updateModel();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addReference() {
        referenceTable.getSelectionModel().selectedItemProperty().removeListener(updateViewListener);

        Reference emptyReference = manager.addEmptyBookReference();
        referenceTable.getItems().add(emptyReference);


        referenceTable.refresh();

        referenceTable.getSelectionModel().clearSelection();

        referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);

        showReferenceDetails(noReferenceDetailInfo);

        try {
            updateModel();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateReference(Reference oldReference, Reference newReference, boolean replaceType)
            throws IOException {
        updateModelFromView(oldReference, replaceType);
        updateViewFromModel(newReference, replaceType);
    }

    private void updateViewFromModel(Reference reference, boolean replaceType) {
        updateReferenceTableFromModel(reference, replaceType);
        updateReferenceDetailFromModel(reference);
    }

    private void updateReferenceDetailFromModel(Reference reference) {
        if (reference != null) {
            if (reference instanceof BookSectionReference) {
                updateBookSectionReferenceDetailFromModel((BookSectionReference) reference);

            } else if (reference instanceof ArticleReference) {
                updateArticleReferenceDetailFromModel((ArticleReference) reference);

            } else if (reference instanceof BookReference) {
                updateBookReferenceDetailFromModel((BookReference) reference);

            } else if (reference instanceof BookLetReference) {
                updateBookLetReferenceDetailFromModel((BookLetReference) reference);

            } else if (reference instanceof ConferenceProceedingsReference) {
                updateProceedingsReferenceDetailFromModel((ConferenceProceedingsReference) reference);
            } else {
                updateThesisReferenceDetailFromModel((ThesisReference) reference);

            }
        }
    }

    private void updateBookReferenceDetailFromModel(BookReference reference) {
        if (reference != null) {
            bookDetailController.setReferenceId(reference.getId());
            bookDetailController.setReferenceType(ReferenceType.BOOK);
            bookDetailController.setAuthor(reference.getAuthor());
            bookDetailController.setTitle(reference.getTitle());
            bookDetailController.setYear(reference.getYear());
            bookDetailController.setNote(reference.getNote());
            bookDetailController.setPublisher(reference.getPublisher());
            bookDetailController.setVolume(reference.getVolume());
            bookDetailController.setSeries(reference.getSeries());
            bookDetailController.setAddress(reference.getAddress());
            bookDetailController.setEdition(reference.getEdition());
            bookDetailController.setMonth(reference.getMonth());

            showReferenceDetails(bookDetail);
        }
    }

    private void updateArticleReferenceDetailFromModel(ArticleReference reference) {
        if (reference != null) {
            articleDetailController.setReferenceId(reference.getId());
            articleDetailController.setReferenceType(ReferenceType.ARTICLE);
            articleDetailController.setAuthor(reference.getAuthor());
            articleDetailController.setTitle(reference.getTitle());
            articleDetailController.setYear(reference.getYear());
            articleDetailController.setNote(reference.getNote());
            articleDetailController.setJournal(reference.getJournal());
            articleDetailController.setVolume(reference.getVolume());
            articleDetailController.setNumber(reference.getNumber());
            articleDetailController.setPages(reference.getPages());
            articleDetailController.setMonth(reference.getMonth());

            showReferenceDetails(articleDetail);
        }
    }

    private void updateBookSectionReferenceDetailFromModel(BookSectionReference reference) {
        if (reference != null) {
            bookSectionDetailController.setReferenceId(reference.getId());
            bookSectionDetailController.setReferenceType(ReferenceType.BOOKSECCION);
            bookSectionDetailController.setAuthor(reference.getAuthor());
            bookSectionDetailController.setTitle(reference.getTitle());
            bookSectionDetailController.setYear(reference.getYear());
            bookSectionDetailController.setNote(reference.getNote());
            bookSectionDetailController.setPublisher(reference.getPublisher());
            bookSectionDetailController.setVolume(reference.getVolume());
            bookSectionDetailController.setSeries(reference.getSeries());
            bookSectionDetailController.setAddress(reference.getAddress());
            bookSectionDetailController.setEdition(reference.getEdition());
            bookSectionDetailController.setChapter(reference.getChapter());
            bookSectionDetailController.setPages(reference.getPages());
            bookSectionDetailController.setMonth(reference.getMonth());

            showReferenceDetails(bookSectionDetail);
        }
    }

    private void updateBookLetReferenceDetailFromModel(BookLetReference reference) {
        if (reference != null) {
            bookLetDetailController.setReferenceId(reference.getId());
            bookLetDetailController.setReferenceType(ReferenceType.BOOKLET);
            bookLetDetailController.setAuthor(reference.getAuthor());
            bookLetDetailController.setTitle(reference.getTitle());
            bookLetDetailController.setYear(reference.getYear());
            bookLetDetailController.setNote(reference.getNote());
            bookLetDetailController.setHowpublished(reference.getHowpublished());
            bookLetDetailController.setAddress(reference.getAddress());
            bookLetDetailController.setMonth(reference.getMonth());

            showReferenceDetails(bookLetDetail);
        }
    }

    private void updateProceedingsReferenceDetailFromModel(ConferenceProceedingsReference reference) {
        if (reference != null) {
            proceedingsDetailController.setReferenceId(reference.getId());
            proceedingsDetailController.setReferenceType(ReferenceType.CONFERENCEPROCEEDINGS);
            proceedingsDetailController.setAuthor(reference.getAuthor());
            proceedingsDetailController.setTitle(reference.getTitle());
            proceedingsDetailController.setYear(reference.getYear());
            proceedingsDetailController.setNote(reference.getNote());
            proceedingsDetailController.setVolume(reference.getVolume());
            proceedingsDetailController.setSerie(reference.getSerie());
            proceedingsDetailController.setAddress(reference.getAddress());
            proceedingsDetailController.setMonth(reference.getMonth());

            showReferenceDetails(proceedingsDetail);
        }
    }

    private void updateThesisReferenceDetailFromModel(ThesisReference reference) {
        if (reference != null) {
            thesisDetailController.setReferenceId(reference.getId());
            thesisDetailController.setReferenceType(ReferenceType.THESIS);
            thesisDetailController.setAuthor(reference.getAuthor());
            thesisDetailController.setTitle(reference.getTitle());
            thesisDetailController.setYear(reference.getYear());
            thesisDetailController.setNote(reference.getNote());
            thesisDetailController.setSchool(reference.getSchool());
            thesisDetailController.setAddress(reference.getAddress());
            thesisDetailController.setMonth(reference.getMonth());
            thesisDetailController.setType(reference.getType());

            showReferenceDetails(thesisDetail);
        }
    }

    private void showReferenceDetails(Node node) {
        for (Node currentNode : referenceDetail.getChildren()) {
            currentNode.setVisible(false);
        }
        node.setVisible(true);

    }

    private void updateReferenceTableFromModel(Reference reference, boolean replaceType) {
        if (reference != null && replaceType) {

            for (Reference currentReference : referenceTable.getItems()) {
                if (currentReference.getId().equals(reference.getId())) {
                    int index = referenceTable.getItems().indexOf(currentReference);
                    referenceTable.getSelectionModel().selectedItemProperty()
                            .removeListener(updateViewListener);
                    referenceTable.getItems().set(index, reference);
                    referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);
                    break;
                }

            }
        }

        referenceTable.refresh();
    }

    private void updateModelFromView(Reference reference, boolean replaceType) throws IOException {
        if (reference != null) {
            if (reference instanceof BookSectionReference) {
                updateModelFromBookSectionReferenceDetail((BookSectionReference) reference, replaceType);
            } else if (reference instanceof ArticleReference) {
                updateModelFromArticleReferenceDetail((ArticleReference) reference, replaceType);
            } else if (reference instanceof BookReference) {
                updateModelFromBookReferenceDetail((BookReference) reference, replaceType);
            } else if (reference instanceof BookLetReference) {
                updateModelFromBookLetReferenceDetail((BookLetReference) reference, replaceType);
            } else if (reference instanceof ConferenceProceedingsReference) {
                updateModelFromProceedingsReferenceDetail((ConferenceProceedingsReference) reference,
                        replaceType);
            } else {
                updateModelFromThesisReferenceDetail((ThesisReference) reference, replaceType);
            }
        }
    }

    private void updateModelFromBookReferenceDetail(BookReference reference, boolean replaceType)
            throws IOException {
        if (reference != null) {
            if (replaceType) {
                manager.replaceReferenceType(reference);
            } else if (bookDetailController.isDifferentReference(reference)) {
                reference.setAuthor(bookDetailController.getAuthor());
                reference.setTitle(bookDetailController.getTitle());
                reference.setYear(bookDetailController.getYear());
                reference.setMonth(bookDetailController.getMonth());
                reference.setNote(bookDetailController.getNote());
                reference.setPublisher(bookDetailController.getPublisher());
                reference.setVolume(bookDetailController.getVolume());
                reference.setSeries(bookDetailController.getSeries());
                reference.setAddress(bookDetailController.getAddress());
                reference.setEdition(bookDetailController.getEdition());
            }

            updateModel();
        }
    }

    private void updateModelFromArticleReferenceDetail(ArticleReference reference,
                                                       boolean replaceType) throws IOException {
        if (reference != null) {
            if (replaceType) {
                manager.replaceReferenceType(reference);
            } else if (articleDetailController.isDifferentReference(reference)) {
                reference.setAuthor(articleDetailController.getAuthor());
                reference.setTitle(articleDetailController.getTitle());
                reference.setYear(articleDetailController.getYear());
                reference.setMonth(articleDetailController.getMonth());
                reference.setNote(articleDetailController.getNote());
                reference.setJournal(articleDetailController.getJournal());
                reference.setNumber(articleDetailController.getNumber());
                reference.setVolume(articleDetailController.getVolume());
                reference.setPages(articleDetailController.getPages());
            }

            updateModel();
        }
    }

    private void updateModelFromBookSectionReferenceDetail(BookSectionReference reference,
                                                           boolean replaceType) throws IOException {
        if (reference != null) {
            if (replaceType) {
                manager.replaceReferenceType(reference);
            } else if (bookSectionDetailController.isDifferentReference(reference)) {
                reference.setAuthor(bookSectionDetailController.getAuthor());
                reference.setTitle(bookSectionDetailController.getTitle());
                reference.setYear(bookSectionDetailController.getYear());
                reference.setMonth(bookSectionDetailController.getMonth());
                reference.setNote(bookSectionDetailController.getNote());
                reference.setPublisher(bookSectionDetailController.getPublisher());
                reference.setVolume(bookSectionDetailController.getVolume());
                reference.setSeries(bookSectionDetailController.getSeries());
                reference.setAddress(bookSectionDetailController.getAddress());
                reference.setEdition(bookSectionDetailController.getEdition());
                reference.setChapter(bookSectionDetailController.getChapter());
                reference.setPages(bookSectionDetailController.getPages());

            }

            updateModel();
        }
    }

    private void updateModelFromBookLetReferenceDetail(BookLetReference reference,
                                                       boolean replaceType) throws IOException {
        if (reference != null) {
            if (replaceType) {
                manager.replaceReferenceType(reference);
            } else if (bookLetDetailController.isDifferentReference(reference)) {
                reference.setAuthor(bookLetDetailController.getAuthor());
                reference.setTitle(bookLetDetailController.getTitle());
                reference.setYear(bookLetDetailController.getYear());
                reference.setMonth(bookLetDetailController.getMonth());
                reference.setNote(bookLetDetailController.getNote());
                reference.setHowpublished(bookLetDetailController.getHowpublished());
                reference.setAddress(bookLetDetailController.getAddress());
            }

            updateModel();
        }
    }

    private void updateModelFromProceedingsReferenceDetail(ConferenceProceedingsReference reference,
                                                           boolean replaceType) throws IOException {
        if (reference != null) {
            if (replaceType) {
                manager.replaceReferenceType(reference);
            } else if (proceedingsDetailController.isDifferentReference(reference)) {
                reference.setAuthor(proceedingsDetailController.getAuthor());
                reference.setTitle(proceedingsDetailController.getTitle());
                reference.setYear(proceedingsDetailController.getYear());
                reference.setMonth(proceedingsDetailController.getMonth());
                reference.setNote(proceedingsDetailController.getNote());
                reference.setVolume(proceedingsDetailController.getVolume());
                reference.setSerie(proceedingsDetailController.getSerie());
                reference.setAddress(proceedingsDetailController.getAddress());
            }

            updateModel();
        }
    }

    private void updateModelFromThesisReferenceDetail(ThesisReference reference, boolean replaceType)
            throws IOException {
        if (reference != null) {
            if (replaceType) {
                manager.replaceReferenceType(reference);
            } else if (thesisDetailController.isDifferentReference(reference)) {
                reference.setAuthor(thesisDetailController.getAuthor());
                reference.setTitle(thesisDetailController.getTitle());
                reference.setYear(thesisDetailController.getYear());
                reference.setMonth(thesisDetailController.getMonth());
                reference.setNote(thesisDetailController.getNote());
                reference.setSchool(thesisDetailController.getSchool());
                reference.setType(thesisDetailController.getType());
                reference.setAddress(thesisDetailController.getAddress());
            }

            updateModel();
        }
    }

    private void updateModel() throws IOException {
        manager.saveTables();
    }
}
