package com.client.views;

import com.client.helpers.Routes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SceneController {
    @FXML
    private Label lbl1;
    private Label lbl2;

    @FXML
    private Button btn1;
    private Button btn2;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        if (event.getSource() == btn1) {
            stage = (Stage) btn1.getScene().getWindow();
            root = FXMLLoader.load(Routes.viewsRoute("loggedIn.fxml"));
            System.out.println("Logged In !");
        } else {
            stage = (Stage) btn2.getScene().getWindow();
            root = FXMLLoader.load(Routes.viewsRoute("registeryView.fxml"));
            System.out.println("Registery !");

        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
