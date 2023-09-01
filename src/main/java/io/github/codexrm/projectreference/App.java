package io.github.codexrm.projectreference;

import io.github.codexrm.projectreference.model.utils.AlertMessage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.IOException;

import io.github.codexrm.projectreference.view.RootLayoutController;

public class App extends Application {

    private RootLayoutController rootLayoutController;
    private VBox rootLayout;
    private AlertMessage alert;

    public App() {
        FXMLLoader rootLayoutLoader = new FXMLLoader();
        rootLayoutLoader.setLocation(getClass().getResource("view/RootLayout.fxml"));
        alert = new AlertMessage();

        try {
            rootLayoutController = new RootLayoutController();
            rootLayoutLoader.setController(rootLayoutController);
            rootLayout = rootLayoutLoader.load();
        } catch (IOException e) {
            alert.getAlert(Alert.AlertType.ERROR, "Error","", "Hubo un error al intentar cargar aplicación. Los sentimos" );
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) {
        stage.setTitle("Codex RM");
        stage.getIcons().add(new Image("file:Logo.jpg"));


        stage.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (KeyCodeCombination.keyCombination("Ctrl+N").match(keyEvent)) {
                rootLayoutController.addReference();
            }
        });

        Scene scene = new Scene(rootLayout);
        rootLayoutController.setStage(stage);
        stage.setScene(scene);
        stage.show();



        // cierra la aplicaión y el programa previa confirmacion de alert
        stage.setOnCloseRequest(e -> {
           Alert altClosed = alert.getAlert(Alert.AlertType.CONFIRMATION, "Atención","", "Desea salir de la aplicaión?" );
            if (alert.getResult(altClosed).equals(ButtonType.OK)) {
                if(rootLayoutController.closeApp()){
                    stage.close();
                    Platform.exit();
                }else{
                   alert.getAlert(Alert.AlertType.ERROR, "Error","", "Hubo un error al intentar cerrar de la aplicaión?" );
                }

            }

        });

    }
}

class AppLauncher {
    public static void main(String[] args) {
        App.main(args);
    }
}
