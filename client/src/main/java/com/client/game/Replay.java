package com.client.game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Replay {
    private List<String> logins;
    private List<Integer> moveX;
    private List<Integer> moveY;
    private List<Integer> pawnX;
    private List<Integer> pawnY;
    int currentMove = 0;
    ChineseCheckersBoard board;

    public Replay(List<String> logins, List<Integer> moveX, List<Integer> moveY, List<Integer> pawnX,
            List<Integer> pawnY) {
        this.logins = logins;
        this.moveX = moveX;
        this.moveY = moveY;
        this.pawnX = pawnX;
        this.pawnY = pawnY;
        try {
            board = new ChineseCheckersBoardBuilder().setNumberOfPlayers(logins.size()).setSize(5).build();
            start();
        } catch (Exception ignored) {
        }

    }

    private void start() {
        StringBuilder bob = new StringBuilder();
        for (String s : logins) {
            bob.append(s).append(" | ");
        }
        Stage stage = new Stage();
        Group group = new Group();
        Scene scene = new Scene(group, 800, 800);
        ChineseCheckersBoardAdapter adapter = new ChineseCheckersBoardAdapter(board);
        Field[][] fields = adapter.getFields();
        for (Field[] a : fields) {
            for (Field c : a) {
                if (c != null)
                    group.getChildren().add(c);
            }
        }
        stage.setScene(scene);
        stage.setTitle("Game between: " + bob);
        stage.show();
        scene.setOnKeyPressed((keyEvent -> {
            if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
                if (currentMove != pawnX.size() - 1) {
                    try {
                        adapter.move(pawnX.get(currentMove), pawnY.get(currentMove), moveX.get(currentMove),
                                moveY.get(currentMove));
                        currentMove++;
                    } catch (Exception ignored) {
                    }
                }
            } else if (keyEvent.getCode().equals(KeyCode.LEFT)) {
                if (currentMove != 0) {
                    try {
                        currentMove--;
                        adapter.move(moveX.get(currentMove), moveY.get(currentMove), pawnX.get(currentMove),
                                pawnY.get(currentMove));
                    } catch (Exception ignored) {
                    }
                }
            }
        }));
    }
}
