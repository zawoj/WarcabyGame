package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.animation.TranslateTransition;
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

public class StartViewController implements Initializable {

    @FXML
    private Button connectServerButton, errorButton;
    @FXML
    private TextField IP, PORT;

    @FXML
    private Pane ErrorPane;

    @FXML
    private void StartViewControllerButtons(ActionEvent event) throws Exception {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(ErrorPane);
        try {
            ClientCore.getInstance().reqServerConnection(IP.getText(), PORT.getText());
            LoadNewScene();
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public void LoadNewScene() throws MalformedURLException, IOException {
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

}
