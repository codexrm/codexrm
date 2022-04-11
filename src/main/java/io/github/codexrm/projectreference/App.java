package io.github.codexrm.projectreference;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.IOException;

import io.github.codexrm.projectreference.view.RootLayoutController;

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
    }
}

class AppLauncher {
    public static void main(String[] args) {
        App.main(args);
    }
}
