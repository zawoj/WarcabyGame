package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Objects;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LobbyController {
    @FXML
    Text NickName, HostName, Player1Nick, Player2Nick, Player3Nick, Player4Nick, Player5Nick;
    @FXML
    private ImageView avatarImage, HostAvatar, Player1Avatar, Player2Avatar, Player3Avatar, Player4Avatar,
            Player5Avatar;
    @FXML
    TextField gameName;
    @FXML
    Button saveEditButton, goOut, startGame;

    private Boolean saveMode = false;
    private static Stage stage;
    private static Parent root;

    @FXML
    public void initialize() {
        ClientCore.getInstance().setLobbyController(this);
        displayNickName(ClientCore.getInstance().getLogin());
        displayAvatar(ClientCore.getInstance().getAvatar());
        gameName.setPromptText(ClientCore.getInstance().getLobbyInfo().getGameName());
        gameName.setDisable(true);

    }

    @FXML
    public void saveEdit() {
        saveMode = !saveMode;
        if (saveMode == true) {
            // After edit
            saveEditButton.setText("Save");
            gameName.setDisable(false);
            gameName.requestFocus();
        } else {
            // After Save
            saveEditButton.setText("Edit");
            gameName.setDisable(true);
        }
    }

    @FXML
    private void goOut() throws IOException {
        ClientCore.getInstance().exitLobby();
        stage = (Stage) goOut.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("DashboardView.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
        ClientCore.getInstance().setLobbyController(this);
        gameName.setText("Game Name");
    }

    public void refreshLobbyData() {
        setUsers();
        isHost(ClientCore.getInstance().getLogin());

        // Check that game can by started
        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() > 1) {
            startGame.setDisable(false);
        } else {
            startGame.setDisable(true);
        }
    }

    @FXML
    public void startGame() {
        System.out.println("Game Start");
    }

    private void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

    public void displayAvatar(Integer avatarNumber) {
        Image avatarImagePreview = new Image(Routes.imageRoute("avatars\\avatar" + avatarNumber + ".png"));
        avatarImage.setImage(avatarImagePreview);
    }

    private void isHost(String playerName) {

        System.out.println("Host: " + ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0) + " My name: "
                + playerName);

        if (!Objects.equals(playerName, ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0))) {
            gameName.setDisable(true);
            saveEditButton.setDisable(true);
            startGame.setDisable(true);
        }
    }

    private void setUsers() {
        // Set Nicks
        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 1)
            HostName.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 2)
            Player1Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(1).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 3)
            Player2Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(2).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 4)
            Player3Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(3).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 5)
            Player4Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(4).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 6)
            Player5Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(5).toString());

        // Set Avatars
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 1)
            HostAvatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(0) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 2)
            Player1Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(1) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 3)
            Player2Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(2) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 4)
            Player3Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(3) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 5)
            Player4Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(4) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 6)
            Player5Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(5) + ".png")));
    }

    public void loadGameScene() throws IOException {
        stage = (Stage) goOut.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("GameView.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

}