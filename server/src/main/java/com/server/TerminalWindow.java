package com.server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.helpers.Routes;

/**
 * server terminal window class
 */
public class TerminalWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Routes.viewsRoute("Terminal.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> {
            ServerCore.getInstance().close(false);
            Platform.exit();
        });
        stage.show();
    }

    /**
     * launches the window
     */
    public void launchWindow() {
        launch();
    }
}