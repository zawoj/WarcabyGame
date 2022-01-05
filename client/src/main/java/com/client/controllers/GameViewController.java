package com.client.controllers;

import com.client.game.ChineseCheckersBoard;
import com.client.game.ChineseCheckersBoardAdapter;
import com.client.game.ChineseCheckersBoardBuilder;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class GameViewController {
    @FXML
    public Pane gameBoard;
    static Group group = new Group();

    @FXML
    public void initialize() {

        System.out.println("HEJO");
        gameBoard.getChildren().add(gameBoardLoader());
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
