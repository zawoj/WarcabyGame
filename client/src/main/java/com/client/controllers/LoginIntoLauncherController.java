package com.client.controllers;

import java.net.URL;

import com.client.helpers.Routes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginIntoLauncherController {

    @FXML
    private Button LoginButton, RegisteryButton;
    @FXML
    private TextField NickNameTextField;
    @FXML
    private URL location;

    @FXML
    private void LoginLauncherControllerButtons(ActionEvent event) throws Exception {
        // TODO implement validation
        Stage stage;
        Parent root;

        // TODO clean code
        if (event.getSource() == LoginButton) {
            stage = (Stage) LoginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(Routes.viewsRoute("DashboardView.fxml"));
            root = loader.load();
            Scene scene = new Scene(root, 1200, 800);

            DashboardController dashboardController = loader.getController();
            dashboardController.displayNickName(NickNameTextField.getText());

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

        } else {

        }

    }

    @FXML
    void initialize() {

    }

}
