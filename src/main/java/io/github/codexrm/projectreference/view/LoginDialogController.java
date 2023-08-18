package io.github.codexrm.projectreference.view;

import io.github.codexrm.projectreference.viewmodel.UserLoginVM;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

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

    private ValidationSupport validationSupport = new ValidationSupport();

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
    public void initialize(URL url, ResourceBundle resourceBundle) {

        validationSupport.registerValidator(username, true, Validator.createEmptyValidator("Campo requerido"));
        validationSupport.registerValidator(password,true, Validator.createEmptyValidator("Campo requerido"));
    }

    public void clearField() {
    username.clear();
    password.clear();
    }

    @FXML
    private void handleOk() {
        if (!validationSupport.isInvalid()) {
            user.setUsername(username.getText());
            user.setPassword(password.getText());

            okClicked = true;
            dialogStage.close();
        }
    }
}
