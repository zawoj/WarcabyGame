package com.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;

/**
 * server terminal window class
 */
public class TerminalWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(new File("src/main/java/com/server/Terminal.fxml").toURI().toURL());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> ServerCore.getInstance().close());
        stage.show();
    }

    /**
     * launches the window
     */
    public void launchWindow(){
        launch();
    }
}