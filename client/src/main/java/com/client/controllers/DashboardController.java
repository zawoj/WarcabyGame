package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import com.client.ClientCore;
import com.client.helpers.Routes;
import com.jfoenix.controls.cells.editors.TextFieldEditorBase;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    Text NickName;
    @FXML
    private ImageView avatarImage;
    @FXML
    Button createGameButton, logoutButtin;
    @FXML
    Pane lobbyNamePane;
    @FXML
    TextField createNameLobby;

    @FXML
    public void initialize() {
        displayNickName(ClientCore.getInstance().getLogin());
        displayAvatar(ClientCore.getInstance().getAvatar());
    }

    @FXML
    void createLooby() throws MalformedURLException, IOException {
        LoadLobby();
    }

    @FXML
    public void logout() throws MalformedURLException, IOException {
        Stage stage;
        Parent root;

        stage = (Stage) logoutButtin.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("loginIntoLauncher.fxml"));
        System.out.println("Connect Server");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void setLobbyName() {
        TranslateTransition transition = new TranslateTransition();
        transition.setNode(lobbyNamePane);
        transition.setToY(-650);
        transition.play();

    }

    public void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

    public void displayAvatar(Integer avatarNumber) {
        Image avatarImagePreview = new Image(Routes.imageRoute("avatars\\avatar" + avatarNumber + ".png"));
        avatarImage.setImage(avatarImagePreview);
    }

    public void LoadLobby() throws MalformedURLException, IOException {

        Stage stage = (Stage) createGameButton.getScene().getWindow();
        Parent root = FXMLLoader.load(Routes.viewsRoute("Lobby.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

}
