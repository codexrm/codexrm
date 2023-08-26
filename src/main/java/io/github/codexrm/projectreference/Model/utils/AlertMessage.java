package io.github.codexrm.projectreference.model.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AlertMessage {

    public AlertMessage() {}

    public Alert getAlert(Stage stage, Alert.AlertType type, String title, String headerTest, String contentText){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerTest);
        alert.setContentText(contentText);

        stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:Logo.jpg"));

        Button okButton = (Button) alert.getDialogPane().lookupButton( ButtonType.OK );
        okButton.setText("Aceptar");
        okButton.setStyle("-fx-background-color: #0cb7f2;-fx-font-weight:bold");

        if(!type.equals(Alert.AlertType.ERROR)){
            Button cancelButton = (Button) alert.getDialogPane().lookupButton( ButtonType.CANCEL);
            cancelButton.setText("Cancelar");
            cancelButton.setStyle("-fx-background-color: #0cb7f2;-fx-font-weight:bold");

        }

        alert.showAndWait();
        return alert;
    }

    public ButtonType getResult(Alert alert){ return alert.getResult(); }
}

