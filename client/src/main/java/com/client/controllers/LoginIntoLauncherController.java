package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginIntoLauncherController {

    @FXML
    private Button LoginButton, RegisteryButton;
    @FXML
    private TextField LoginField, PasswordField;
    @FXML
    private URL location;
    @FXML
    private Pane ErrorPane;

    private static Stage stage;
    private static Parent root;

    @FXML
    private void LoginLauncherControllerButtons(ActionEvent event) throws Exception {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(ErrorPane);

        try {
            if (event.getSource() == LoginButton) {
                ClientCore.getInstance().reqLogin(LoginField.getText(), PasswordField.getText());
                LoadDashboardScene();
            } else if (event.getSource() == RegisteryButton) {
                LoadRegisteryScene();
            }
        } catch (Exception e) {
            transition.setToX(-285);
            transition.play();
        }

    }

    @FXML
    private void errorButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(ErrorPane);
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void initialize() {

    }

    private void LoadDashboardScene() throws MalformedURLException, IOException {
        stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Routes.viewsRoute("DashboardView.fxml"));
        root = loader.load();
        Scene scene = new Scene(root, 1200, 800);

        DashboardController dashboardController = loader.getController();
        dashboardController.displayNickName(LoginField.getText());
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

    private void LoadRegisteryScene() throws MalformedURLException, IOException {
        stage = (Stage) RegisteryButton.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("RegisterView.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

}
