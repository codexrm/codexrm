package io.github.codexrm.projectreference.view;

import io.github.codexrm.projectreference.ViewModel.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

        DetailsBookReferenceController bookDetailViewController = new DetailsBookReferenceController();
        DetailsArticleReferenceController articleDetailViewController = new DetailsArticleReferenceController();
        DetailsBookSectionReferenceController bookSectionDetailViewController = new DetailsBookSectionReferenceController();
        DetailsBookLetReferenceController bookLetDetailViewController = new DetailsBookLetReferenceController();
        DetailsThesisReferenceController thesisDetailViewController = new DetailsThesisReferenceController();
        DetailsConferenceProceedingsReferenceController conferenceDetailViewController = new DetailsConferenceProceedingsReferenceController();


        bookDetailViewController.setDataModel(managerVM);
        articleDetailViewController.setDataModel(managerVM);
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
                if (newValue.getClass() == BookSectionReferenceVM.class) {
                    showReferenceDetails(bookSectionDetail);
                } else if (newValue.getClass() == ArticleReferenceVM.class) {
                    showReferenceDetails(articleDetail);
                } else if (newValue.getClass() == BookLetReferenceVM.class) {
                    showReferenceDetails(bookLetDetail);
                } else if (newValue.getClass() == ConferenceProceedingsReferenceVM.class) {
                    showReferenceDetails(conferenceDetail);
                } else if (newValue.getClass() == ThesisReferenceVM.class) {
                    showReferenceDetails(thesisDetail);
                }    else if(newValue.getClass() ==  BookReferenceVM.class){
                    showReferenceDetails(bookDetail);
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
        loadReferencePane(bookLetDetail);
        loadReferencePane(conferenceDetail);
        loadReferencePane(thesisDetail);
        loadReferencePane(bookSectionDetail);
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

    @FXML
    private void save() {
        try {
            managerVM.saveDataToModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
