package io.github.codexrm.projectreference.view;

import io.github.codexrm.projectreference.viewmodel.UserLoginVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginDialogController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    private Stage dialogStage;

    private UserLoginVM user = new UserLoginVM();

    private boolean okClicked = false;

    public TextField getUsername() { return username; }

    public void setUsername(TextField username) { this.username = username; }

    public PasswordField getPassword() { return password; }

    public void setPassword(PasswordField password) { this.password = password; }

    public UserLoginVM getUser() { return user; }

    public void setUser(UserLoginVM user) { this.user = user; }

    public boolean isOkClicked() { return okClicked; }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void clearField() {
    username.clear();
    password.clear();
    }

    @FXML
    private void handleOk() {
        if (validateFields()) {
            user.setUsername(username.getText());
            user.setPassword(password.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    private boolean validateFields(){
      boolean isValidate = true;

        if (username.getLength() == 0) {
            username.setStyle("-fx-border-color: red;-fx-border-width:2px");
            isValidate = false;
        } else {
            username.setStyle(null);
        }
        if (password.getLength() == 0) {
            password.setStyle("-fx-border-color: red;-fx-border-width:2px");
            isValidate = false;
        } else {
            password.setStyle(null);
        }
        return isValidate;
    }
}
