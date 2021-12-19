package com.client.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.client.helpers.Routes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StartViewController implements Initializable {

    @FXML
    private Button connectServerButton = new Button("Connect",
            new ImageView(new Image(Routes.imageRoute("Arrow.png"))));

    @FXML
    private void StartViewControllerButtons(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        stage = (Stage) connectServerButton.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("loginIntoLauncher.fxml"));
        System.out.println("Connect Server");
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));

        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

}
