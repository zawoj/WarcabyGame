package com.client;

import com.client.helpers.Routes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Window extends Application {

    public void createWindow() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Stage stage = new Stage();

        // Custom Fonr load
        Font.loadFont(getClass().getResourceAsStream(Routes.styleRoute("LatoRegular.ttf")), 14);
        // FXML loader
        Parent sceneRoot = FXMLLoader.load(Routes.viewsRoute("StartView.fxml"));

        // Basic scene
        Scene scene = new Scene(sceneRoot, 800, 600);

        scene.getStylesheets().add(Routes.styleRoute("app.css"));

        // Icone
        Image icon = new Image(Routes.imageRoute("pawn.png"));

        // Stage settings
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("WarcabyGame");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

    }

}
