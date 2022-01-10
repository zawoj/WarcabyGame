package com.client.controllers;

import java.io.IOException;
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

/**
 * Class responsible for controlling the layout of the login screen and the
 * functionality.
 */
public class StartViewController implements Initializable {

    @FXML
    public Button connectServerButton, errorButton;
    @FXML
    public TextField IP, PORT;

    @FXML
    private Pane ErrorPane;

    /**
     * Method for initialize controller and send it to server
     * 
     * @param url            users url
     * @param resourceBundle users resourceBundle
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ClientCore.getInstance().setStartViewController(this);
    }

    /**
     * Method for initialize try to connect to server
     * 
     * @param event users event
     */
    @FXML
    private void StartViewControllerButtons(ActionEvent event) {
        try {
            ClientCore.getInstance().reqServerConnection(IP.getText(), PORT.getText());
            LoadNewScene();
        } catch (Exception e) {
            showError();
        }
    }

    /**
     * Method to close the error pane
     * 
     * @param e users actionEvent
     */
    @FXML
    private void errorButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(ErrorPane);
        transition.setToX(0);
        transition.play();
    }

    /**
     * Method to show the error pane when any error occurs
     */
    public void showError() {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(ErrorPane);
        transition.setToX(-285);
        transition.play();
    }

    /**
     * Method which load the Login and Registery view
     * 
     * @throws IOException throws if an error occurs
     */
    public void LoadNewScene() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) connectServerButton.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("loginIntoLauncher.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));

        stage.setScene(scene);
        stage.show();

    }

    /**
     * Getter for the IP
     * 
     * @return String IP which put users
     */
    public String getIP() {
        return this.IP.getText();
    }

}
