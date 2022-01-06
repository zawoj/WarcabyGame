package com.client.controllers;

import java.util.LinkedList;

import com.client.ClientCore;
import com.client.game.ChineseCheckersBoard;
import com.client.game.ChineseCheckersBoardAdapter;
import com.client.game.ChineseCheckersBoardBuilder;

import com.client.game.MouseMoveHandler;
import javafx.scene.paint.Color;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameViewController {

    @FXML
    public StackPane gameBoard;
    @FXML
    public Button skipRound;
    @FXML
    Text Player1, Player2, Player3, Player4, Player5, Player6;
    @FXML
    public Rectangle Color1, Color2, Color3, Color4, Color5, Color6;
    @FXML
    public Pane arrow;

    LinkedList<String> playersNicknames;
    int playerCount;
    int playerNumber;
    static Group group = new Group();
    public int[] playerNumbers;

    private MouseMoveHandler mmh;

    @FXML
    public void initialize() {
        ClientCore.getInstance().setGameController(this);
        // setTurnArrow();

    }

    public void startGameView(int playerCount, int playerNumber) {
        this.playerCount = playerCount;
        this.playerNumber = playerNumber;
        gameBoard.getChildren().add(gameBoardLoader());
        gameBoard.setAlignment(Pos.CENTER);
        mmh.setPlayerNumber(playerNumber);
        setUsers();

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

    private void setUsers() {
        switch (playerCount) {
            case 2 -> playerNumbers = new int[] { 1, 4 };
            case 3 -> playerNumbers = new int[] { 1, 3, 5 };
            case 4 -> playerNumbers = new int[] { 2, 3, 5, 6 };
            case 6 -> playerNumbers = new int[] { 1, 2, 3, 4, 5, 6 };
        }

        if (playerNumbers.length == 2) {
            Player1.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0));
            Player2.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(1));
            Color1.setFill(setColor(playerNumbers[0]));
            Color2.setFill(setColor(playerNumbers[1]));
        } else if (playerNumbers.length == 3) {
            Player1.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0));
            Player2.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(1));
            Player3.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(2));
            Color1.setFill(setColor(playerNumbers[0]));
            Color2.setFill(setColor(playerNumbers[1]));
            Color3.setFill(setColor(playerNumbers[2]));

        } else if (playerNumbers.length == 4) {
            Player1.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0));
            Player2.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(1));
            Player3.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(2));
            Player4.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(3));
            Color1.setFill(setColor(playerNumbers[0]));
            Color2.setFill(setColor(playerNumbers[1]));
            Color3.setFill(setColor(playerNumbers[2]));
            Color4.setFill(setColor(playerNumbers[3]));
        } else if (playerNumbers.length == 6) {
            Player1.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0));
            Player2.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(1));
            Player3.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(2));
            Player4.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(3));
            Player5.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(4));
            Player6.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(5));
            Color1.setFill(setColor(playerNumbers[0]));
            Color2.setFill(setColor(playerNumbers[1]));
            Color3.setFill(setColor(playerNumbers[2]));
            Color4.setFill(setColor(playerNumbers[3]));
            Color5.setFill(setColor(playerNumbers[4]));
            Color6.setFill(setColor(playerNumbers[5]));
        }
    }

    private Paint setColor(int playerNumber) {
        Paint returnCOLOR;
        switch (playerNumber) {
            case 1 -> returnCOLOR = Color.BLUE;
            case 2 -> returnCOLOR = Color.GREEN;
            case 3 -> returnCOLOR = Color.RED;
            case 4 -> returnCOLOR = Color.BLACK;
            case 5 -> returnCOLOR = Color.YELLOW;
            case 6 -> returnCOLOR = Color.PURPLE;
            default -> returnCOLOR = Color.TRANSPARENT;
        }

        return returnCOLOR;
    }

    public void setTurnArrow() {
        String currntTurPlayer = ClientCore.getInstance().currentPlayer;
        System.out.println("Set arrow on player " + currntTurPlayer);
        System.out.println(ClientCore.getInstance().getLobbyInfo().getPlayernames().indexOf(currntTurPlayer));
        System.out.println(arrow);

        // TODO repair setToY bsc isn'y good
        switch (ClientCore.getInstance().getLobbyInfo().getPlayernames().indexOf(currntTurPlayer)) {
            case 0: {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(arrow);
                transition.setToY(25.0);
                transition.play();
                break;
            }
            case 1: {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(arrow);
                transition.setToY(50.0);
                transition.play();
                break;
            }
            case 2: {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(arrow);
                transition.setToY(100.0);
                transition.play();
                break;
            }
            case 3: {
                TranslateTransition transition = new TranslateTransition();
                transition.setToY(126.0);
                transition.play();
                break;
            }
            case 4: {
                TranslateTransition transition = new TranslateTransition();
                transition.setToY(154.0);
                transition.play();
                break;
            }
            case 5: {
                TranslateTransition transition = new TranslateTransition();
                transition.setToY(180.0);
                transition.play();
                break;
            }
        }

    }

}
