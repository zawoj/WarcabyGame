package com.client.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class GameViewController {
    @FXML
    public Pane gameBoard, PlayersList;

    @FXML
    public void initialize() {
        System.out.println(com.board.AppWindow.getBoard());
        com.board.AppWindow.launchApp();
        gameBoard.getChildren().add(com.board.AppWindow.getBoard());
        System.out.println("HEJO");
    }
}
