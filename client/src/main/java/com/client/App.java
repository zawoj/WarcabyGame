package com.client;

import com.client.helpers.Routes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*// Stage stage = new Stage();

        // Routes
        Routes route = new Routes();

        // FXML loader
        Parent sceneRoot = FXMLLoader.load(route.viewsRoute("testView.fxml"));

        // Basic scene
        Scene scene = new Scene(sceneRoot, 500, 500);

        // Icone
        Image icon = new Image(route.imageRoute("pawn.png"));
        // imageRoute();

        // Stage settings
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("WarcabyGame");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.centerOnScreen();*/

    }

}
