package com.client.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisteryController {

    @FXML
    private Button btnBack, buttonCreateAccount;
    @FXML
    private TextField newLogin, newPassword, checkNewPassword;
    @FXML
    Pane errorRegisteryNewAccount, errorPanePassword, accountCreatedSuccesfully, errorPanePasswordValidation;
    @FXML
    private ComboBox<String> avatarChoiceBox;
    @FXML
    private ImageView avatarImage;

    private static Stage stage;
    private static Parent root;
    private static ObservableList<String> avatarId = FXCollections.observableArrayList("Avatar 1", "Avatar 2",
            "Avatar 3", "Avatar 4", "Avatar 5", "Avatar 6", "Avatar 7", "Avatar 8", "Avatar 9", "Avatar 10",
            "Avatar 11", "Avatar 12", "Avatar 13", "Avatar 14", "Avatar 15", "Avatar 16", "Avatar 17", "Avatar 18",
            "Avatar 19", "Avatar 20", "Avatar 21", "Avatar 22", "Avatar 23", "Avatar 24");

    @FXML
    private void CreateAccountButtonController(ActionEvent e) throws Exception {
        try {
            if (e.getSource() == buttonCreateAccount) {

                if (passwordValidation(newPassword.getText())) {
                    if (newPassword.getText().equals(checkNewPassword.getText())) {
                        ClientCore.getInstance().reqCreateNewAccount(newLogin.getText(), newPassword.getText(),
                                (String) avatarChoiceBox.getValue());

                        TranslateTransition transition = new TranslateTransition();
                        transition.setNode(accountCreatedSuccesfully);
                        transition.setToX(-300);
                        transition.play();

                        newLogin.setText("");
                        newPassword.setText("");
                        checkNewPassword.setText("");

                        avatarChoiceBox.setValue("Choice avatar");
                    } else {
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

    @FXML
    private void initialize() {
        avatarChoiceBox.setItems(avatarId);
        avatarChoiceBox.setValue("Choice avatar");
    }

    @FXML
    private void changeAvatarPreview() throws FileNotFoundException {
        Image avatarImagePreview = new Image(Routes.imageRoute(
                "avatars\\" + ((String) avatarChoiceBox.getValue()).replaceAll(" ", "") +
                        ".png"));
        avatarImage.setImage(avatarImagePreview);

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
