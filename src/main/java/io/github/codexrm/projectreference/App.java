package io.github.codexrm.projectreference;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.IOException;

import io.github.codexrm.projectreference.view.RootLayoutController;
import org.modelmapper.ModelMapper;

public class App extends Application {

    private RootLayoutController rootLayoutController;
    private VBox rootLayout;

    public App() {
        FXMLLoader rootLayoutLoader = new FXMLLoader();
        rootLayoutLoader.setLocation(getClass().getResource("view/RootLayout.fxml"));

        try {
            rootLayoutController = new RootLayoutController();
            rootLayoutLoader.setController(rootLayoutController);
            rootLayout = rootLayoutLoader.load();
        } catch (IOException e) {
            /* Mostrar algun dialogo de error al usuario */
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) {
        stage.setTitle("Codex RM");

        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (KeyCodeCombination.keyCombination("Ctrl+N").match(keyEvent)) {
                try {
                    rootLayoutController.addReference();
                } catch (IOException e) {
                    /* Mostrar algun dialogo de error al usuario */
                    e.printStackTrace();
                }
            }
        });

        Scene scene = new Scene(rootLayout);
        rootLayoutController.setStage(stage);
        stage.setScene(scene);
        stage.show();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Atención ");
        alert.setContentText("Desea salir de la aplicaión ?");

        // cierra la aplicaión y el programa previa confirmacion de alert
        stage.setOnCloseRequest(e -> {
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.OK)) {
                rootLayoutController.logout();
                stage.close();
                Platform.exit();
            }

        });

    }
}

class AppLauncher {
    public static void main(String[] args) {
        App.main(args);
    }
}
