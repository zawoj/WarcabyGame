package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class StartViewController implements Initializable {

    @FXML
    private Button connectServerButton = new Button("Connect",
            new ImageView(new Image(Routes.imageRoute("Arrow.png"))));
    @FXML
    private TextField IP, PORT;

    private static String ipString, portString;

    @FXML
    private void StartViewControllerButtons(ActionEvent event) throws Exception {
        ipString = IP.getText();
        portString = PORT.getText();

        if (event.getSource() == connectServerButton) {
            if (ClientCore.reqServerConnection()) {
                LoadNewScene();
            } else {
                System.out.println("Oh i cant connect");
            }
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

    public static String getIP() {
        return ipString;
    }

    public static String getPORT() {
        return portString;
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
