package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import com.client.ClientCore;
import com.client.Lobby;
import com.client.helpers.Routes;

import com.client.helpers.Routes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        Lobby newLobby = new Lobby(ClientCore.getInstance().getLogin());
        gameName.setPromptText(ClientCore.getInstance().getLobbyInfo().getGameName());
        gameName.setDisable(true);
        // isHost();
        setUsers();

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
        // for (String s : ClientCore.getInstance().getLobbyInfo().getPlayernames()) {
        // System.out.println(s);
        // }
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

    private void isHost() {
        if (true) {
            gameName.setDisable(true);
            saveEditButton.setDisable(true);
            startGame.setDisable(true);
        }
    }

    private void setUsers() {
        // Set Nicks
        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 1
                && ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0) != null)
            HostName.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 2
                && ClientCore.getInstance().getLobbyInfo().getPlayernames().get(1) != null)
            Player1Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(1).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 3
                && ClientCore.getInstance().getLobbyInfo().getPlayernames().get(2) != null)
            Player2Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(2).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 4
                && ClientCore.getInstance().getLobbyInfo().getPlayernames().get(3) != null)
            Player3Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(3).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 5
                && ClientCore.getInstance().getLobbyInfo().getPlayernames().get(4) != null)
            Player4Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(4).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 6
                && ClientCore.getInstance().getLobbyInfo().getPlayernames().get(5) != null)
            Player5Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(5).toString());

        // Set Avatars


        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 1
                && ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(0) != null)
            HostAvatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(0) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 2
                && ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(1) != null)
            Player1Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(1) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 3
                && ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(2) != null)
            Player2Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(2) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 4
                && ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(3) != null)
            Player3Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(3) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 5
                && ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(4) != null)
            Player4Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(4) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 6
                && ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(5) != null)
            Player5Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(5) + ".png")));
    }

}