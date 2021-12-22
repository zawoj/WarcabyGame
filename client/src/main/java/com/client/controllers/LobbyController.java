package com.client.controllers;

import com.client.ClientCore;
import com.client.Lobby;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class LobbyController {
    @FXML
    Text NickName;
    @FXML
    private ImageView avatarImage;

    @FXML
    public void initialize() {
        displayNickName(ClientCore.getInstance().getLogin());
        Lobby newLobby = new Lobby(ClientCore.getInstance().getLogin());
        ClientCore.getInstance().setLobbyController(this);
    }

    public void refreshData(){
        for(String s :ClientCore.getInstance().getLobbyInfo().getPlayerinfo()){
            System.out.println(s);
        }
    }

    public void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

}