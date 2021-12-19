package com.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DashboardController {
    @FXML
    Text NickName;

    public void displayNickName(String nickName) {
        NickName.setText(nickName);
    }

    @FXML
    public void initialize() {
        NickName.setTextAlignment(TextAlignment.CENTER);
    }

}
