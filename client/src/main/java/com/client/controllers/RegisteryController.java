package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisteryController {

    @FXML
    private Button btnBack, buttonCreateAccount;
    @FXML
    private TextField newLogin, newPassword, checkNewPassword;
    @FXML
    Pane errorRegisteryNewAccount, errorPanePassword, accountCreatedSuccesfully, errorPanePasswordValidation;

    private static Stage stage;
    private static Parent root;

    @FXML
    private void CreateAccountButtonController(ActionEvent e) throws Exception {
        // TODO add password validation
        try {
            if (e.getSource() == buttonCreateAccount) {

                if (passwordValidation(newPassword.getText())) {
                    if (newPassword.getText().equals(checkNewPassword.getText())) {
                        ClientCore.getInstance().reqCreateNewAccount(newLogin.getText(), newPassword.getText());
                        TranslateTransition transition = new TranslateTransition();
                        transition.setNode(accountCreatedSuccesfully);
                        transition.setToX(-300);
                        transition.play();
                        newLogin.setText("");
                        newPassword.setText("");
                        checkNewPassword.setText("");
                    } else {
                        System.out.println(newPassword.getText() + checkNewPassword.getText());
                        TranslateTransition transition = new TranslateTransition();
                        transition.setNode(errorPanePassword);
                        transition.setToX(-300);
                        transition.play();
                    }
                } else {
                    TranslateTransition transition = new TranslateTransition();
                    transition.setNode(errorPanePasswordValidation);
                    transition.setToX(-300);
                    transition.play();
                }

            }

            if (e.getSource() == btnBack) {
                LoadLoingLauncher();
            }

        } catch (Exception er) {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(errorRegisteryNewAccount);
            transition.setToX(-300);
            transition.play();
        }
    }

    @FXML
    private void errorPaneLoginExistButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(errorRegisteryNewAccount);
        transition.setToX(0);
        transition.play();
    }

    @FXML
    private void errorPanePasswordButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(errorPanePassword);
        transition.setToX(0);
        transition.play();
    }

    @FXML
    private void accountCreatedSuccesfullyButton(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(accountCreatedSuccesfully);
        transition.setToX(0);
        transition.play();
    }

    @FXML
    private void errorPasswordValidtionButtonController(ActionEvent e) {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(errorPanePasswordValidation);
        transition.setToX(0);
        transition.play();
    }

    @FXML
    private void backButtonController(ActionEvent e) throws MalformedURLException, IOException {
        LoadLoingLauncher();
    }

    private void LoadLoingLauncher() throws MalformedURLException, IOException {
        stage = (Stage) buttonCreateAccount.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("LoginIntoLauncher.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

    private boolean passwordValidation(String password) {
        if (password.length() > 4) {
            return true;
        } else {
            return false;
        }
    }

}
