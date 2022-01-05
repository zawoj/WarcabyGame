package com.client.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisteryController implements Initializable {

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
    private static final ObservableList<String> avatarId = FXCollections.observableArrayList("Avatar 1", "Avatar 2",
            "Avatar 3", "Avatar 4", "Avatar 5", "Avatar 6", "Avatar 7", "Avatar 8", "Avatar 9", "Avatar 10",
            "Avatar 11", "Avatar 12", "Avatar 13", "Avatar 14", "Avatar 15", "Avatar 16", "Avatar 17", "Avatar 18",
            "Avatar 19", "Avatar 20", "Avatar 21", "Avatar 22", "Avatar 23", "Avatar 24");

    @FXML
    private void CreateAccountButtonController(ActionEvent e) {
        try {
            if (e.getSource() == buttonCreateAccount) {

                if (passwordValidation(newPassword.getText())) {
                    if (newPassword.getText().equals(checkNewPassword.getText())) {
                        ClientCore.getInstance().reqCreateNewAccount(newLogin.getText(), newPassword.getText(),
                                Integer.parseInt(avatarChoiceBox.getValue().split(" ")[1]));

                        newLogin.setText("");
                        newPassword.setText("");
                        checkNewPassword.setText("");

                        avatarChoiceBox.setValue("Choose avatar");
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
            errorNotification();
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
    private void backButtonController(ActionEvent e) throws IOException {
        LoadLoingLauncher();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        avatarChoiceBox.setItems(avatarId);
        avatarChoiceBox.setValue("Choice avatar");
        ClientCore.getInstance().setRegisteryController(this);
    }

    @FXML
    private void changeAvatarPreview() throws FileNotFoundException {
        Image avatarImagePreview = new Image(Routes.imageRoute(
                "avatars\\" + avatarChoiceBox.getValue().replaceAll(" ", "") +
                        ".png"));
        avatarImage.setImage(avatarImagePreview);

    }

    public void accountCreatedSuccesfullyNotification() {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(accountCreatedSuccesfully);
        transition.setToX(-300);
        transition.play();
    }

    public void errorNotification() {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(errorRegisteryNewAccount);
        transition.setToX(-300);
        transition.play();
    }

    private void LoadLoingLauncher() throws IOException {
        stage = (Stage) buttonCreateAccount.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("LoginIntoLauncher.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

    private boolean passwordValidation(String password) {
        return password.length() > 4;
    }

}
