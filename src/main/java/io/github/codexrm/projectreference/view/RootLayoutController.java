package io.github.codexrm.projectreference.view;

import io.github.codexrm.projectreference.model.enums.Format;
import io.github.codexrm.projectreference.model.utils.AlertMessage;
import io.github.codexrm.projectreference.viewmodel.*;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;
import org.jbibtex.ParseException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

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
    private final DetailsBookReferenceController bookDetailViewController;
    private final DetailsArticleReferenceController articleDetailViewController;
    private final DetailsBookSectionReferenceController bookSectionDetailViewController;
    private final DetailsBookLetReferenceController bookLetDetailViewController;
    private final DetailsThesisReferenceController thesisDetailViewController;
    private final DetailsConferenceProceedingsReferenceController conferenceProceedingsDetailViewController;
    private final DetailsConferencePaperReferenceController conferencePaperDetailViewController;
    private final DetailsWebPageReferenceController webPageDetailViewController;

    private Stage stage;
    private Stage loginStage;
    private AlertMessage alert;
    private static boolean isStartedLoginScene = false;

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
        loginStage = new Stage();
        alert = new AlertMessage();

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

        bookDetailViewController = new DetailsBookReferenceController();
        articleDetailViewController = new DetailsArticleReferenceController();
        bookSectionDetailViewController = new DetailsBookSectionReferenceController();
        bookLetDetailViewController = new DetailsBookLetReferenceController();
        thesisDetailViewController = new DetailsThesisReferenceController();
        conferenceProceedingsDetailViewController = new DetailsConferenceProceedingsReferenceController();
        conferencePaperDetailViewController = new DetailsConferencePaperReferenceController();
        webPageDetailViewController = new DetailsWebPageReferenceController();
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

    //Reference
    @FXML
    public void addReference(){

        referenceTable.getSelectionModel().selectedItemProperty().removeListener(updateViewListener);

        managerVM.addEmptyBookReference();

        referenceTable.refresh();
        referenceTable.getSelectionModel().clearSelection();
        referenceTable.getSelectionModel().selectedItemProperty().addListener(updateViewListener);

        showReferenceDetails(noReferenceDetailInfo);
    }

    @FXML
    public void deleteReference(){
        Alert altDelete = alert.getAlert(Alert.AlertType.CONFIRMATION, "Atención","", "Desea eliminar las referencias que han sido seleccionadas ?" );
        if (alert.getResult(altDelete).equals(ButtonType.OK)) {
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
    }

    @FXML
    private void save() {
        Alert altSave = alert.getAlert(Alert.AlertType.CONFIRMATION, "Atención","", "Desea salvar la lista de referencias ?" );
        if (alert.getResult(altSave).equals(ButtonType.OK)) {
            if(!verificateValidations()){
                alert.getAlert(Alert.AlertType.ERROR, "Error de Validacion", "Referencias invalidas", "Verifique los campos de las referencias. Es posible que alguna no sea valida");
            }else{
                try {
                    managerVM.saveDataToModel();

                } catch (IOException e) {
                    alert.getAlert(Alert.AlertType.ERROR, "Error","", "Hubo un error al intentar salvar las referencias" );
                }
            }
        }
    }

    @FXML
    public void sync() {

        try {
            if(!managerVM.verificateAutentication()){
                if(login()){
                    if(!managerVM.userLogin(loginDialogController.getUser())){
                        alert.getAlert(Alert.AlertType.ERROR, "Error de Acceso al usuario", "Usuario no autorizado", "Verifique nombre de usuario y contraseña nuevamente. Es posible que el usuario se encuentre deshabilitado o no exista");
                    }else{
                        if(!managerVM.syncDB())
                            alert.getAlert(Alert.AlertType.ERROR, "Error","", "Hubo un error al intentar sincronizar las referencias con el servidor");
                    }
                }
            }

        } catch (IOException e) {
            alert.getAlert(Alert.AlertType.ERROR, "Error","", "Hubo un error al intentar sincronizar las referencias con el servidor");

        }
    }

    @FXML
    public void exportRis(){
        try {
            exportTo(Format.RIS);
        } catch (IOException e) {
            alert.getAlert(Alert.AlertType.ERROR, "Error","", "Hubo un error al intentar exportar las referencias" );
        }
    }

    @FXML
    public void exportBibTex(){
        try {
            exportTo(Format.BIBTEX);
        } catch (IOException e) {
            alert.getAlert(Alert.AlertType.ERROR, "Error","", "Hubo un error al intentar exportar las referencias" );
        }
    }

    @FXML
    public void importRis() {
        try {
            importTo(Format.RIS);
        } catch (IOException | ParseException e) {
            alert.getAlert(Alert.AlertType.ERROR, "Error","", "Hubo un error al intentar importar las referencias" );
        }
    }

    @FXML
    public void importBibTex() {
        try {
            importTo(Format.BIBTEX);
        } catch (IOException | ParseException e) {
            alert.getAlert(Alert.AlertType.ERROR, "Error","", "Hubo un error al intentar importar las referencias" );
        }
    }

    //User
    @FXML
    public void logout(){
        Alert altSave = alert.getAlert(Alert.AlertType.CONFIRMATION, "Atención","", "Desea cerrar la sesion ?" );
        if (alert.getResult(altSave).equals(ButtonType.OK)) {
            try {
                if(!managerVM.userLogout())
                    alert.getAlert(Alert.AlertType.ERROR, "Error","", "No fue posible cerrar la sesion" );

            } catch (ExecutionException | InterruptedException | IOException e) {
                e.printStackTrace();
                alert.getAlert(Alert.AlertType.ERROR, "Error","", "No fue posible cerrar la sesion" );
            }

        }
    }

    public boolean closeApp(){
        try {
            return managerVM.userLogout();
        } catch (ExecutionException | InterruptedException | IOException e) {
            return false;
        }
    }

    //App
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
                deleteReference();
            }
        });
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

    //Reference

    private void exportTo(Format format) throws IOException {

        ObservableList<ReferenceVM> referenceList = referenceTable.getSelectionModel().getSelectedItems();
        if (referenceList != null) {
            managerVM.exportReferenceList(referenceList,format);
        }
    }

    private void importTo(Format format) throws IOException, ParseException {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Import");
           List<File> selectedFile = fileChooser.showOpenMultipleDialog(stage);
           if (!selectedFile.isEmpty()) {
               managerVM.importReferences(selectedFile, format);
               referenceTable.refresh();
           }
    }

    private boolean verificateValidations() {
        boolean validation = true;
        ObservableList<ReferenceVM> referenceList = referenceTable.getItems();
        for(ReferenceVM reference: referenceList){
            if(reference instanceof BookSectionReferenceVM && bookSectionDetailViewController.getValidationSupport().isInvalid()){
                validation = false;
            }else{
                if(reference instanceof ArticleReferenceVM && articleDetailViewController.getValidationSupport().isInvalid()){
                    validation = false;
                }else{
                    if(reference instanceof BookReferenceVM && bookDetailViewController.getValidationSupport().isInvalid()){
                        validation = false;
                    } else{
                        if(reference instanceof BookLetReferenceVM && bookLetDetailViewController.getValidationSupport().isInvalid()){
                            validation = false;
                        } else{
                            if(reference instanceof ThesisReferenceVM && thesisDetailViewController.getValidationSupport().isInvalid()){
                                validation = false;
                            } else{
                                if(reference instanceof ConferencePaperReferenceVM && conferencePaperDetailViewController.getValidationSupport().isInvalid()){
                                    validation = false;
                                } else{
                                    if(reference instanceof ConferenceProceedingsReferenceVM && conferenceProceedingsDetailViewController.getValidationSupport().isInvalid()){
                                        validation = false;
                                    } else{
                                        if(reference instanceof WebPageReferenceVM && webPageDetailViewController.getValidationSupport().isInvalid()){
                                            validation = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return validation;
    }

    //User
    private boolean login() {

        loginDialogController.clearField();
        if(!isStartedLoginScene)
            createScene();

        // Show the dialog and wait until the user closes it
        loginStage.showAndWait();

        loginStage.setOnCloseRequest(e -> {
            Alert altClosed = alert.getAlert(Alert.AlertType.CONFIRMATION, "Atención","", "No se desea autenticar ?" );
            if (alert.getResult(altClosed).equals(ButtonType.OK)) {
                loginStage.setScene(null);
                loginStage.close();
            }
        });

        return loginDialogController.isOkClicked();
    }

    private void createScene(){
        // Create the dialog Stage.

        loginStage.setTitle("Login");
        loginStage.initModality(Modality.WINDOW_MODAL);
        loginStage.initOwner(stage);
        Scene scene = new Scene(login);
        loginStage.setScene(scene);

        loginDialogController.setDialogStage(loginStage);
        isStartedLoginScene = true;
    }
}
