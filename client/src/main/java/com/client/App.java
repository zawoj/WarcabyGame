package com.client;

import com.client.helpers.Routes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Stage stage = new Stage();

        // Routes
        Routes route = new Routes();

        // FXML loader
        Parent sceneRoot = FXMLLoader.load(route.viewsRoute("firstView.fxml"));

        // Create Group node which will be the root
        Group root = new Group();

        // Basic scene
        Scene scene = new Scene(sceneRoot, 500, 500);

        // Text
        Text text = new Text();
        text.setText("LogIn");
        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Verdana", 50));
        text.setFill(Color.GOLD);

        // Icone
        Image icon = new Image(route.imageRoute("pawn.png"));
        // imageRoute();

        // Settings for root
        root.getChildren().add(text);

        // Stage settings
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("WarcabyGame");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.centerOnScreen();
        // primaryStage.setX(50);
        // primaryStage.setY(50);
        // primaryStage.setFullScreen(true);
        // primaryStage.setFullScreenExitHint("press q");
        // primaryStage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));

    }

}
