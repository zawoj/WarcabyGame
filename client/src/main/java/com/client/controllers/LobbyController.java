package com.client.controllers;

import com.client.ClientCore;
import com.client.Lobby;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LobbyController {
    @FXML
    Text NickName;
    @FXML
    private ImageView avatarImage;
    @FXML
    TextField gameName;
    @FXML
    Button saveEditButton;

    private Boolean saveMode = false;

    @FXML
    public void initialize() {
        displayNickName(ClientCore.getInstance().getLogin());
        Lobby newLobby = new Lobby(ClientCore.getInstance().getLogin());
        gameName.setPromptText("Host's game");
        gameName.setDisable(true);

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

    public void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

}