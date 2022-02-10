package io.github.codexrm.projectreference;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.IOException;

import io.github.codexrm.projectreference.Model.Controller.ReferenceLibraryManager;
import io.github.codexrm.projectreference.view.RootLayoutController;

public class App extends Application {
    private final KeyCombination ctrlN = KeyCodeCombination.keyCombination("Ctrl+N");
    private RootLayoutController rootLayoutController;
    private VBox rootLayout;


    public App() {
        FXMLLoader rootLayoutLoader = new FXMLLoader();
        rootLayoutLoader.setLocation(getClass().getResource("view/RootLayout.fxml"));

        try {
            ReferenceLibraryManager manager = ReferenceLibraryManager.getReferenceLibraryManager();
            rootLayoutController = new RootLayoutController(manager);
            rootLayoutLoader.setController(rootLayoutController);
            rootLayout = rootLayoutLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Codex");

        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (ctrlN.match(keyEvent)) {
                rootLayoutController.addReference();
            }
        });

        Scene scene = new Scene(rootLayout);
        stage.setScene(scene);
        stage.show();
    }

}

class AppLauncher {
    public static void main(String[] args) {
        App.main(args);
    }
}
