package com.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginIntoLauncherController implements Initializable {

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
        try {
            if (event.getSource() == LoginButton) {
                ClientCore.getInstance().reqLogin(LoginField.getText(), PasswordField.getText());
            } else if (event.getSource() == RegisteryButton) {
                LoadRegisteryScene();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void errorButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(ErrorPane);
        transition.setToX(0);
        transition.play();
    }

    public void ErrorNotification() {
        TranslateTransition transition = new TranslateTransition();
        ErrorPane.setVisible(true);
        transition.setNode(ErrorPane);
        transition.setToX(-285);
        transition.play();
    }

    public void LoadDashboardScene() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    stage = (Stage) LoginButton.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(Routes.viewsRoute("DashboardView.fxml"));
                    root = loader.load();
                    Scene scene = new Scene(root, 1200, 800);
                    scene.getStylesheets().add(Routes.styleRoute("app.css"));
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private void LoadRegisteryScene() throws IOException {
        stage = (Stage) RegisteryButton.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("RegisterView.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClientCore.getInstance().setLoginIntoLauncherController(this);
    }
}
