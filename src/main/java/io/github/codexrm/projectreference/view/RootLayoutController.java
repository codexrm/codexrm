package io.github.codexrm.projectreference.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import io.github.codexrm.projectreference.ViewModel.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class RootLayoutController implements Initializable {


    private final ReferenceLibraryManagerVM managerVM;
    private final ScrollPane bookDetail;
    private final ScrollPane articleDetail;
    private final ScrollPane bookSectionDetail;
    private final ScrollPane bookLetDetail;
    private final ScrollPane conferenceDetail;
    private final ScrollPane thesisDetail;
    @FXML
    private TableView<ReferenceVM> referenceTable;
    @FXML
    private TableColumn<ReferenceVM, String> authorColumn;
    @FXML
    private TableColumn<ReferenceVM, String> titleColumn;
    @FXML
    private AnchorPane referenceDetail;
    @FXML
    private Label noReferenceDetailInfo;

    @FXML
    private Button add;
    @FXML
    private Button delete;

    private ChangeListener<ReferenceVM> updateViewListener;
    private int selectedIndex;

    public RootLayoutController() throws IOException {

        managerVM = new ReferenceLibraryManagerVM();

        FXMLLoader bookDetailLoader = new FXMLLoader();
        FXMLLoader articleDetailLoader = new FXMLLoader();
        FXMLLoader bookSectionDetailLoader = new FXMLLoader();
        FXMLLoader bookLetDetailLoader = new FXMLLoader();
        FXMLLoader thesisDetailLoader = new FXMLLoader();
        FXMLLoader conferenceDetailLoader = new FXMLLoader();

        bookDetailLoader.setLocation(getClass().getResource("DetailsBookReference.fxml"));
        articleDetailLoader.setLocation(getClass().getResource("DetailsArticleReference.fxml"));
        bookSectionDetailLoader.setLocation(getClass().getResource("DetailsBookSectionReference.fxml"));
        bookLetDetailLoader.setLocation(getClass().getResource("DetailsBookLetReference.fxml"));
        thesisDetailLoader.setLocation(getClass().getResource("DetailsThesisReference.fxml"));
        conferenceDetailLoader.setLocation(getClass().getResource("DetailsConferenceReference.fxml"));

        DetailsArticleReferenceController articleDetailViewController = new DetailsArticleReferenceController();
        DetailsBookReferenceController bookDetailViewController = new DetailsBookReferenceController();
        DetailsBookSectionReferenceController bookSectionDetailViewController = new DetailsBookSectionReferenceController();
        DetailsBookLetReferenceController bookLetDetailViewController = new DetailsBookLetReferenceController();
        DetailsThesisReferenceController thesisDetailViewController = new DetailsThesisReferenceController();
        DetailsConferenceProceedingsReferenceController conferenceDetailViewController = new DetailsConferenceProceedingsReferenceController();

        articleDetailViewController.setDataModel(managerVM);
        bookDetailViewController.setDataModel(managerVM);
        bookSectionDetailViewController.setDataModel(managerVM);
        bookLetDetailViewController.setDataModel(managerVM);
        thesisDetailViewController.setDataModel(managerVM);
        conferenceDetailViewController.setDataModel(managerVM);

        bookDetailLoader.setController(bookDetailViewController);
        articleDetailLoader.setController(articleDetailViewController);
        bookLetDetailLoader.setController(bookLetDetailViewController);
        bookSectionDetailLoader.setController(bookSectionDetailViewController);
        thesisDetailLoader.setController(thesisDetailViewController);
        conferenceDetailLoader.setController(conferenceDetailViewController);

        bookDetail = bookDetailLoader.load();
        articleDetail = articleDetailLoader.load();
        bookSectionDetail = bookSectionDetailLoader.load();
        bookLetDetail = bookLetDetailLoader.load();
        thesisDetail = thesisDetailLoader.load();
        conferenceDetail = conferenceDetailLoader.load();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());

        managerVM.currentReferenceProperty().bind(referenceTable.getSelectionModel().selectedItemProperty());
        referenceTable.setItems(managerVM.getReferenceList());

        ChangeListener<Boolean> changeListener = (observable, oldValue, newValue) -> {
            if (!oldValue && newValue) {
                referenceTable.getSelectionModel().select(selectedIndex);
                managerVM.setReferenceTypeReplaced(false);
            }
        };

        managerVM.referenceTypeReplacedProperty().addListener(changeListener);

        loadReferenceTable();
        loadReferenceDetail();
    }

    private void loadReferenceTable() {
        referenceTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        updateViewListener = (observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedIndex = referenceTable.getSelectionModel().getSelectedIndex();
                if (newValue instanceof BookReferenceVM) {
                    showReferenceDetails(bookDetail);
                } else if (newValue instanceof ArticleReferenceVM) {
                    showReferenceDetails(articleDetail);
                } else if (newValue instanceof BookSectionReferenceVM) {
                    showReferenceDetails(bookSectionDetail);
                } else if (newValue instanceof BookLetReferenceVM) {
                    showReferenceDetails(bookLetDetail);
                } else if (newValue instanceof ThesisReferenceVM) {
                    showReferenceDetails(thesisDetail);
                } else if (newValue instanceof ConferenceProceedingsReferenceVM) {
                    showReferenceDetails(conferenceDetail);
                }
            }
        };

        referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);

        referenceTable.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DELETE) {
                ObservableList<ReferenceVM> selectedReferences = referenceTable.getSelectionModel().getSelectedItems();
                try {
                    deleteReference(selectedReferences);
                } catch (IOException e) {
                    /* Mostrar algun dialogo de error al usuario */
                    e.printStackTrace();
                }
            }
        });
    }

    public void deleteReference(List<ReferenceVM> referenceList) throws IOException {
        if (referenceList != null) {
            referenceTable.getSelectionModel().selectedItemProperty().removeListener(updateViewListener);

            managerVM.deleteReferences(referenceList);

            referenceTable.refresh();
            referenceTable.getSelectionModel().clearSelection();
            referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);

            showReferenceDetails(noReferenceDetailInfo);
        }
    }

    private void loadReferenceDetail() {
        loadReferencePane(bookDetail);
        loadReferencePane(articleDetail);
        loadReferencePane(bookSectionDetail);
        loadReferencePane(bookLetDetail);
        loadReferencePane(thesisDetail);
        loadReferencePane(conferenceDetail);

    }

    private void loadReferencePane(Node node) {
        node.setVisible(false);

        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);

        referenceDetail.getChildren().add(node);
    }

    private void showReferenceDetails(Node node) {
        for (Node currentNode : referenceDetail.getChildren()) {
            currentNode.setVisible(false);
        }
        node.setVisible(true);
    }

    public void addReference() throws IOException {
        referenceTable.getSelectionModel().selectedItemProperty().removeListener(updateViewListener);

        managerVM.addEmptyBookReference();

        referenceTable.refresh();
        referenceTable.getSelectionModel().clearSelection();
        referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);

        showReferenceDetails(noReferenceDetailInfo);
    }
}
