package io.github.codexrm.projectreference.viewmodel;

import io.github.codexrm.projectreference.model.Rest.UserLogin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserLoginVM {

    private StringProperty username;
    private StringProperty password;

    public UserLoginVM() {
        createEmptyUserLoginVM();
    }

    public UserLoginVM(UserLogin userLogin) {
        createEmptyUserLoginVM();

        setUsername(userLogin.getUsername());
        setPassword(userLogin.getPassword());
    }

    private void createEmptyUserLoginVM() {
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
    }

    public String getUsername() { return username.get(); }

    public StringProperty usernameProperty() { return username; }

    public void setUsername(String username) { this.username.set(username); }

    public String getPassword() { return password.get(); }

    public StringProperty passwordProperty() { return password; }

    public void setPassword(String password) { this.password.set(password); }

    public UserLogin toModel() {
        return new UserLogin(this.getUsername(),this.getPassword());
    }
}
