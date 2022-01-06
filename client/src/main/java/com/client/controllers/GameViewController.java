package com.client.controllers;

import java.util.LinkedList;

import com.client.ClientCore;
import com.client.game.ChineseCheckersBoard;
import com.client.game.ChineseCheckersBoardAdapter;
import com.client.game.ChineseCheckersBoardBuilder;

import com.client.game.MouseMoveHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class GameViewController {

    @FXML
    public StackPane gameBoard;
    @FXML
    public Button skipRound;

    LinkedList<String> playersNicknames;
    int playerCount;
    int playerNumber;
    static Group group = new Group();
    private MouseMoveHandler mmh;

    @FXML
    public void initialize() {
        ClientCore.getInstance().setGameController(this);
    }

    public void startGameView(int playerCount, int playerNumber) {
        this.playerCount = playerCount;
        this.playerNumber = playerNumber;
        gameBoard.getChildren().add(gameBoardLoader());
        gameBoard.setAlignment(Pos.CENTER);
        mmh.setPlayerNumber(playerNumber);
    }

    @FXML
    public void skipRound() {
        try {
            ClientCore.getInstance().skipRound();
        } catch (Exception ignored) {
        }
    }

    public Group gameBoardLoader() {
        try {
            ChineseCheckersBoard board = new ChineseCheckersBoardBuilder().setSize(5).setNumberOfPlayers(playerCount)
                    .build();
            ChineseCheckersBoardAdapter BoardAdapter = new ChineseCheckersBoardAdapter(board);
            mmh = BoardAdapter.getMouseMoveHandler();

            com.client.game.Field[][] fields = BoardAdapter.getFields();

            for (com.client.game.Field[] a : fields) {
                for (com.client.game.Field c : a) {
                    if (c != null)
                        group.getChildren().add(c);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return group;
    }

    public MouseMoveHandler getMouseMoveHandler() {
        return mmh;
    }
}
