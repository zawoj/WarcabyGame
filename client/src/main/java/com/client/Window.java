package com.client;

import com.client.helpers.Routes;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Window extends Application {

    public void createWindow() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Stage stage = new Stage();
        ClientCore.getInstance().programStage = primaryStage;
        // Custom Fonr load
        Font.loadFont(getClass().getResourceAsStream(Routes.styleRoute("LatoRegular.ttf")), 14);
        // FXML loader
        Parent sceneRoot = FXMLLoader.load(Routes.viewsRoute("StartView.fxml"));

        // Basic scene
        Scene scene = new Scene(sceneRoot, 800, 600);

        scene.getStylesheets().add(Routes.styleRoute("app.css"));

        // Icone
        Image icon = new Image(Routes.imageRoute("LogoIcon.png"));

        // Stage settings
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("WarcabyGame");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                ClientCore.getInstance().close();
            }
        });
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();

    }

}
