package io.github.codexrm.projectreference.model.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertMessage {

    public AlertMessage() {}

    public Alert getAlert(Alert.AlertType type, String title, String headerTest, String contentText){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(headerTest);
        alert.setContentText(contentText);

        alert.showAndWait();
        return alert;
    }

    public ButtonType getResult(Alert alert){ return alert.getResult(); }
}

