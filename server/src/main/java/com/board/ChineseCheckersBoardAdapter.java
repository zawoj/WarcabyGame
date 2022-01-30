package com.board;

import javafx.scene.paint.Color;

public class ChineseCheckersBoardAdapter {
    ChineseCheckersBoard checkersBoard;
    Field[][] fields;
    int radious = 15;
    MouseMoveHandler mmh;

    public MouseMoveHandler getMouseMoveHandler() {
        return mmh;
    }

    public ChineseCheckersBoardAdapter(ChineseCheckersBoard board) {
        checkersBoard = board;
        fields = new Field[board.getBoard().length][board.getBoard()[0].length];
        mmh = new MouseMoveHandler(this);
        setFields();
    }

    public void setFields() {
        int[][] board = checkersBoard.getBoard();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                switch (board[x][y]) {
                    case -1 -> fields[x][y] = null;
                    case 0 -> fields[x][y] = new Field(2 * radious * y + 15, 3 * radious * x + 15, 1.5 * radious,
                            Color.WHITE, x, y);
                    case 1 -> fields[x][y] = new Field(2 * radious * y + 15, 3 * radious * x + 15, 1.5 * radious,
                            Color.BLUE, x, y);
                    case 2 -> fields[x][y] = new Field(2 * radious * y + 15, 3 * radious * x + 15, 1.5 * radious,
                            Color.GREEN, x, y);
                    case 3 -> fields[x][y] = new Field(2 * radious * y + 15, 3 * radious * x + 15, 1.5 * radious,
                            Color.RED, x, y);
                    case 4 -> fields[x][y] = new Field(2 * radious * y + 15, 3 * radious * x + 15, 1.5 * radious,
                            Color.BLACK, x, y);
                    case 5 -> fields[x][y] = new Field(2 * radious * y + 15, 3 * radious * x + 15, 1.5 * radious,
                            Color.YELLOW, x, y);
                    case 6 -> fields[x][y] = new Field(2 * radious * y + 15, 3 * radious * x + 15, 1.5 * radious,
                            Color.PURPLE, x, y);
                }
                if (fields[x][y] == null)
                    continue;
                fields[x][y].setStroke(Color.BLACK);
                fields[x][y].setStrokeWidth(2);
                fields[x][y].setOnMouseMoved(mouseEvent -> {
                    Field field = (Field) mouseEvent.getSource();
                    ChineseCheckersBoard Logic = checkersBoard.setValidMoves(field.getHeight(), field.getWidth());
                    for (int i = 0; i < Logic.getBoard().length; i++) {
                        for (int j = 0; j < Logic.getBoard()[0].length; j++) {
                            if (Logic.getBoard()[i][j] == 1) {
                                fields[i][j].setStroke(Color.DARKBLUE);
                                fields[i][j].setStrokeWidth(5);
                            } else if (Logic.getBoard()[i][j] == 0) {
                                fields[i][j].setStroke(Color.BLACK);
                                fields[i][j].setStrokeWidth(2);

                            }
                        }
                    }
                });
                fields[x][y].setOnMouseClicked(mmh);
            }
        }
    }

    public Field[][] getFields() {
        return fields;
    }

    public void move(int pawnX, int pawnY, int moveX, int moveY) throws Exception {
        checkersBoard.move(pawnX, pawnY, moveX, moveY);
        int[][] board = checkersBoard.getBoard();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[0].length; y++) {
                switch (board[x][y]) {
                    case -1 -> fields[x][y] = null;
                    case 0 -> fields[x][y].setFill(Color.WHITE);
                    case 1 -> fields[x][y].setFill(Color.BLUE);
                    case 2 -> fields[x][y].setFill(Color.GREEN);
                    case 3 -> fields[x][y].setFill(Color.RED);
                    case 4 -> fields[x][y].setFill(Color.BLACK);
                    case 5 -> fields[x][y].setFill(Color.YELLOW);
                    case 6 -> fields[x][y].setFill(Color.PURPLE);
                }
            }
        }
    }
}
