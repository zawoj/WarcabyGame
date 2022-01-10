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

/**
 * Class responsible for controlling the layout of the login screen and the
 * functionality.
 */
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

    /**
     * Method for initialize controller and send it to server
     * 
     * @param url            users url
     * @param resourceBundle users resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ClientCore.getInstance().setLoginIntoLauncherController(this);
    }

    /**
     * Method which is responsible for loading the login screen or register screen
     * 
     * @param event what event
     * @throws Exception
     */
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

    /**
     * Method which is responsible for close error notification after click X on
     * error pane.
     * Also invokes animation
     * 
     * @param e keyEvent
     */
    @FXML
    private void errorButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(ErrorPane);
        transition.setToX(0);
        transition.play();
    }

    /**
     * Method which invokes error pane, and animation
     */
    public void ErrorNotification() {
        TranslateTransition transition = new TranslateTransition();
        ErrorPane.setVisible(true);
        transition.setNode(ErrorPane);
        transition.setToX(-285);
        transition.play();
    }

    /**
     * Method responsible for loade the dasboard view scene
     * 
     */
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

    /**
     * Method responsible for loading the register view scene
     * 
     * @throws IOException throws if an error occurs
     */
    private void LoadRegisteryScene() throws IOException {
        stage = (Stage) RegisteryButton.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("RegisterView.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

}
