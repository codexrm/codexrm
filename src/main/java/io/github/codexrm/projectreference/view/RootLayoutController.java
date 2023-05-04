package io.github.codexrm.projectreference.view;

import io.github.codexrm.projectreference.model.enums.Format;
import io.github.codexrm.projectreference.viewmodel.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.jbibtex.ParseException;

import java.io.File;
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
    private final ScrollPane conferenceProceedingsDetail;
    private final ScrollPane thesisDetail;
    private final ScrollPane conferencePaperDetail;
    private final ScrollPane webPageDetail;
    private final AnchorPane login;
    private final LoginDialogController loginDialogController;
    private Stage stage;

    @FXML
    private TableView<ReferenceVM> referenceTable;

    @FXML
    private TableColumn<ReferenceVM, String> yearColumn;

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
        stage = new Stage();

        FXMLLoader bookDetailLoader = new FXMLLoader();
        FXMLLoader articleDetailLoader = new FXMLLoader();
        FXMLLoader bookSectionDetailLoader = new FXMLLoader();
        FXMLLoader bookLetDetailLoader = new FXMLLoader();
        FXMLLoader thesisDetailLoader = new FXMLLoader();
        FXMLLoader conferenceProceedingsDetailLoader = new FXMLLoader();
        FXMLLoader conferencePaperDetailLoader = new FXMLLoader();
        FXMLLoader webPageDetailLoader = new FXMLLoader();
        FXMLLoader loginLoader = new FXMLLoader();

        bookDetailLoader.setLocation(getClass().getResource("DetailsBookReference.fxml"));
        articleDetailLoader.setLocation(getClass().getResource("DetailsArticleReference.fxml"));
        bookSectionDetailLoader.setLocation(getClass().getResource("DetailsBookSectionReference.fxml"));
        bookLetDetailLoader.setLocation(getClass().getResource("DetailsBookLetReference.fxml"));
        thesisDetailLoader.setLocation(getClass().getResource("DetailsThesisReference.fxml"));
        conferenceProceedingsDetailLoader.setLocation(getClass().getResource("DetailsConferenceProceedingsReference.fxml"));
        conferencePaperDetailLoader.setLocation(getClass().getResource("DetailsConferencePaperReference.fxml"));
        webPageDetailLoader.setLocation(getClass().getResource("DetailsWebPageReference.fxml"));
        loginLoader.setLocation(getClass().getResource("LoginDialog.fxml"));

        DetailsBookReferenceController bookDetailViewController = new DetailsBookReferenceController();
        DetailsArticleReferenceController articleDetailViewController = new DetailsArticleReferenceController();
        DetailsBookSectionReferenceController bookSectionDetailViewController = new DetailsBookSectionReferenceController();
        DetailsBookLetReferenceController bookLetDetailViewController = new DetailsBookLetReferenceController();
        DetailsThesisReferenceController thesisDetailViewController = new DetailsThesisReferenceController();
        DetailsConferenceProceedingsReferenceController conferenceProceedingsDetailViewController = new DetailsConferenceProceedingsReferenceController();
        DetailsConferencePaperReferenceController conferencePaperDetailViewController = new DetailsConferencePaperReferenceController();
        DetailsWebPageReferenceController webPageDetailViewController = new DetailsWebPageReferenceController();
        loginDialogController = new LoginDialogController();

        bookDetailViewController.setDataModel(managerVM);
        articleDetailViewController.setDataModel(managerVM);
        bookSectionDetailViewController.setDataModel(managerVM);
        bookLetDetailViewController.setDataModel(managerVM);
        thesisDetailViewController.setDataModel(managerVM);
        conferenceProceedingsDetailViewController.setDataModel(managerVM);
        conferencePaperDetailViewController.setDataModel(managerVM);
        webPageDetailViewController.setDataModel(managerVM);

        bookDetailLoader.setController(bookDetailViewController);
        articleDetailLoader.setController(articleDetailViewController);
        bookLetDetailLoader.setController(bookLetDetailViewController);
        bookSectionDetailLoader.setController(bookSectionDetailViewController);
        thesisDetailLoader.setController(thesisDetailViewController);
        conferenceProceedingsDetailLoader.setController(conferenceProceedingsDetailViewController);
        conferencePaperDetailLoader.setController(conferencePaperDetailViewController);
        webPageDetailLoader.setController(webPageDetailViewController);
        loginLoader.setController(loginDialogController);

        bookDetail = bookDetailLoader.load();
        articleDetail = articleDetailLoader.load();
        bookSectionDetail = bookSectionDetailLoader.load();
        bookLetDetail = bookLetDetailLoader.load();
        thesisDetail = thesisDetailLoader.load();
        conferenceProceedingsDetail = conferenceProceedingsDetailLoader.load();
        conferencePaperDetail = conferencePaperDetailLoader.load();
        webPageDetail = webPageDetailLoader.load();
        login = loginLoader.load();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());

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
                    showReferenceDetails(conferenceProceedingsDetail);
                } else if (newValue.getClass() == ThesisReferenceVM.class) {
                    showReferenceDetails(thesisDetail);
                }    else if(newValue.getClass() ==  BookReferenceVM.class){
                    showReferenceDetails(bookDetail);
                } else if(newValue.getClass() ==  ConferencePaperReferenceVM.class){
                    showReferenceDetails(conferencePaperDetail);
                } else if(newValue.getClass() ==  WebPageReferenceVM.class){
                    showReferenceDetails(webPageDetail);
                }
            }
        };

        referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);

        referenceTable.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.DELETE) {
                try {
                    deleteReference();
                } catch (IOException e) {
                    /* Mostrar algun dialogo de error al usuario */
                    e.printStackTrace();
                }
            }
        });
    }

    @FXML
    public void deleteReference() throws IOException {

        ObservableList<ReferenceVM> referenceList = referenceTable.getSelectionModel().getSelectedItems();
        if (referenceList != null) {
            referenceTable.getSelectionModel().selectedItemProperty().removeListener(updateViewListener);

            managerVM.deleteReferences(referenceList);

            referenceTable.refresh();
            referenceTable.getSelectionModel().clearSelection();
            referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);

            showReferenceDetails(noReferenceDetailInfo);
        }
    }
    @FXML
    public void exportRis() throws IOException { exportTo(Format.RIS); }

    @FXML
    public void exportBibTex() throws IOException { exportTo(Format.BIBTEX); }

    private void exportTo(Format format) throws IOException {

        ObservableList<ReferenceVM> referenceList = referenceTable.getSelectionModel().getSelectedItems();
        if (referenceList != null) {
            managerVM.exportReferenceList(referenceList,format);
        }
    }

    @FXML
    public void importRis() throws IOException, ParseException { importTo(Format.RIS); }

    @FXML
    public void importBibTex() throws IOException, ParseException { importTo(Format.BIBTEX); }

    private void importTo(Format format) throws IOException, ParseException {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Import");
           List<File> selectedFile = fileChooser.showOpenMultipleDialog(stage);
           if (!selectedFile.isEmpty()) {
               managerVM.importReferences(selectedFile, format);
               referenceTable.refresh();
           }
    }

    private void loadReferenceDetail() {

        loadReferencePane(bookDetail);
        loadReferencePane(articleDetail);
        loadReferencePane(bookLetDetail);
        loadReferencePane(conferenceProceedingsDetail);
        loadReferencePane(thesisDetail);
        loadReferencePane(bookSectionDetail);
        loadReferencePane(conferencePaperDetail);
        loadReferencePane(webPageDetail);
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

    @FXML
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

    @FXML
    private void sync() {

        try {
            if(showPersonEditDialog()){
                managerVM.userLogin(loginDialogController.getUser());
            }
            managerVM.syncDB();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showPersonEditDialog() {
        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Login");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(stage);
        Scene scene = new Scene(login);
        dialogStage.setScene(scene);

        loginDialogController.setDialogStage(dialogStage);


        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();
        return loginDialogController.isOkClicked();
    }
}
