package com.client;

import com.client.helpers.Routes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.hexworks.mixite.core.api.*;
import org.hexworks.mixite.core.api.defaults.DefaultSatelliteData;

public class Window extends Application {

    public void createWindow() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Stage stage = new Stage();

        // FXML loader
        Parent sceneRoot = FXMLLoader.load(Routes.viewsRoute("testView.fxml"));

        // Basic scene
        Scene scene = new Scene(sceneRoot, 500, 500);

        // Icone
        Image icon = new Image(Routes.imageRoute("pawn.png"));
        // imageRoute();


        HexagonalGridBuilder<DefaultSatelliteData> builder = new HexagonalGridBuilder<DefaultSatelliteData>()
                .setGridHeight(11)
                .setGridWidth(11)
                .setGridLayout(HexagonalGridLayout.HEXAGONAL)
                .setOrientation(HexagonOrientation.FLAT_TOP)
                .setRadius(30);
        Group group = new Group();
        Scene scene1 = new Scene(group);
        HexagonalGrid<DefaultSatelliteData> grid = builder.build();
        for (Hexagon<DefaultSatelliteData> hex: grid.getHexagons()) {
            group.getChildren().add(new Circle(hex.getCenterX(),hex.getCenterY(), 20, Color.VIOLET));
            group.getChildren().add(new Text(hex.getCenterX(),hex.getCenterY(),hex.getGridX() + "," + hex.getGridY() + "," + hex.getGridZ()));
        }
        // Stage settings
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("WarcabyGame");
        primaryStage.setScene(scene1);
        primaryStage.show();
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.centerOnScreen();

    }

}
