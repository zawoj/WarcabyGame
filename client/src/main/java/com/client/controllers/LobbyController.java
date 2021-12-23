package com.client.controllers;

import com.client.ClientCore;
import com.client.Lobby;

import com.client.helpers.Routes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
        displayAvatar(ClientCore.getInstance().getAvatar());
        ClientCore.getInstance().setLobbyController(this);
        gameName.setText("Game Name");
    }

    public void refreshLobbyData(){
        for(String s :ClientCore.getInstance().getLobbyInfo().getPlayerinfo()){
            System.out.println(s);
        }
    }

    public void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }
    public void displayAvatar(Integer avatarNumber) {
        System.out.println(avatarNumber);
        System.out.println(Routes.imageRoute("avatar" + avatarNumber + ".png"));
        Image avatarImagePreview = new Image(Routes.imageRoute("avatars\\avatar" + avatarNumber + ".png"));
        System.out.println(avatarImagePreview);
        avatarImage.setImage(avatarImagePreview);
    }

}