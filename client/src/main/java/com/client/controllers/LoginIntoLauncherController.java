package com.client.controllers;

import java.net.URL;

import com.client.helpers.Routes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginIntoLauncherController {

    @FXML
    private Button LoginButton, RegisteryButton;

    @FXML
    private URL location;

    @FXML
    private void LoginLauncherControllerButtons(ActionEvent event) throws Exception {

        Stage stage;
        Parent root;

        if (event.getSource() == LoginButton) {
            System.out.println("Login");
            stage = (Stage) LoginButton.getScene().getWindow();
            root = FXMLLoader.load(Routes.viewsRoute("DashboardView.fxml"));
            Scene scene = new Scene(root, 1200, 800);
            scene.getStylesheets().add(Routes.styleRoute("app.css"));
            stage.setScene(scene);
            stage.show();

        } else if (event.getSource() == RegisteryButton) {
            System.out.println("Registery");
            stage = (Stage) RegisteryButton.getScene().getWindow();
            root = FXMLLoader.load(Routes.viewsRoute("RegisterView.fxml"));
            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add(Routes.styleRoute("app.css"));
            stage.setScene(scene);
            stage.show();

        }

    }

    @FXML
    void initialize() {

    }

}
