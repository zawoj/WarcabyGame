package com.client.controllers;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DashboardController {
    @FXML
    Text NickName;
    @FXML
    private ImageView avatarImage;

    @FXML
    public void initialize() {
        displayNickName(ClientCore.getInstance().getLogin());
        displayAvatar(ClientCore.getInstance().getAvatar());
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
