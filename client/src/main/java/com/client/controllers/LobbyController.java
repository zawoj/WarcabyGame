package com.client.controllers;

import com.client.ClientCore;
import com.client.Lobby;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LobbyController {
    @FXML
    Text NickName;
    @FXML
    private ImageView avatarImage;
    @FXML
    Label gameName;

    @FXML
    public void initialize() {
        displayNickName(ClientCore.getInstance().getLogin());
        Lobby newLobby = new Lobby(ClientCore.getInstance().getLogin());
        gameName.setText("Game Name");

    }

    public void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

}