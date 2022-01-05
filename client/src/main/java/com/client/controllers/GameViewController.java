package com.client.controllers;

import com.client.game.ChineseCheckersBoard;
import com.client.game.ChineseCheckersBoardAdapter;
import com.client.game.ChineseCheckersBoardBuilder;

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
    static Group group = new Group();

    @FXML
    public void initialize() {
        gameBoard.getChildren().add(gameBoardLoader());
        gameBoard.setAlignment(Pos.CENTER);
    }

    @FXML
    public void skipRound() {
        System.out.println("Skip round");
    }

    public Group gameBoardLoader() {
        try {
            ChineseCheckersBoard board = new ChineseCheckersBoardBuilder().setSize(5).setNumberOfPlayers(6).build();
            board.printInTerminal();
            ChineseCheckersBoardAdapter BoardAdapter = new ChineseCheckersBoardAdapter(board);

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
}
