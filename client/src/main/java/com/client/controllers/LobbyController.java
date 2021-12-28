package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import com.client.ClientCore;
import com.client.Lobby;
import com.client.helpers.Routes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LobbyController {
    @FXML
    Text NickName;
    @FXML
    private ImageView avatarImage;
    @FXML
    TextField gameName;
    @FXML
    Button saveEditButton, goOut, startGame;

    private Boolean saveMode = false;
    private static Stage stage;
    private static Parent root;

    @FXML
    public void initialize() {
        displayNickName(ClientCore.getInstance().getLogin());
        Lobby newLobby = new Lobby(ClientCore.getInstance().getLogin());
        gameName.setPromptText("Host's game");
        gameName.setDisable(true);
        isHost();

    }

    @FXML
    public void saveEdit() {
        saveMode = !saveMode;
        System.out.println(saveMode);
        if (saveMode == true) {
            // After edit
            saveEditButton.setText("Save");
            gameName.setDisable(false);
        } else {
            // After Save
            saveEditButton.setText("Edit");
            gameName.setDisable(true);
            System.out.println(gameName.getText());
        }
    }

    @FXML
    private void goOut() throws MalformedURLException, IOException {
        stage = (Stage) goOut.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("DashboardView.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void startGame() {
        System.out.println("Game Start");
    }

    private void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

    private void isHost() {
        if (true) {
            gameName.setDisable(true);
            saveEditButton.setDisable(true);
            startGame.setDisable(true);
        }
    }

}