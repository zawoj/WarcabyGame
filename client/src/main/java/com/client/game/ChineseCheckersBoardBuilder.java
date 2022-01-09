package com.client.game;

/**
 * -1 pole nielegalne
 * 0 pole puste
 * 1-6 pole z pionkiem gracza
 *
 * [x][y]
 * x - wysokosc
 * y - szerokosc
 * wiem ze dziwnie ale tak mi na razie wygodniej w kodzie
 */
public class ChineseCheckersBoardBuilder {
    int size;
    int numberOfPlayers;
    int height;
    int width;

    public ChineseCheckersBoardBuilder setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        return this;
    }

    public ChineseCheckersBoardBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    public ChineseCheckersBoard build() throws Exception {
        if (size < 1 || numberOfPlayers < 0 || numberOfPlayers == 1 || numberOfPlayers == 5 || numberOfPlayers > 6)
            throw new Exception("wrong build parameters");
        ChineseCheckersBoard checkersBoard = new ChineseCheckersBoard();
        height = size + 3 * (size - 1); // dla size = 5 wynosi 17
        width = 2 * (size + 2 * (size - 1)) - 1; // dla size = 5 wynosi 25
        int[][] board = new int[height][width];
        // czyszczenie tabeli (wszystkie pola na -1)
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = -1;
            }
        }
        // tworzenie planszy (zaznaczanie kształtu na 0)
        setLegal(board);
        setPlayers(board);
        checkersBoard.setBoard(board, size);
        return checkersBoard;
    }

    private void setLegal(int[][] board) {
        int goodPoint = width / 2;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                board[i - 1][goodPoint + 2 * j] = 0;
                board[height - i][goodPoint + 2 * j] = 0;
            }
            goodPoint = goodPoint - 1;
        }
        goodPoint = 0;
        for (int i = size; i < 2 * size; i++) {
            for (int j = 0; j < width - 2 * (i - size); j += 2) {
                board[i - 1][goodPoint + j] = 0;
                board[height - i][goodPoint + j] = 0;
            }
            goodPoint = goodPoint + 1;
        }
    }

    private void setPlayers(int[][] board) {
        switch (numberOfPlayers) {
            case 2 -> set2Players(board);
            case 3 -> set3Players(board);
            case 4 -> set4Players(board);
            case 6 -> set6Players(board);
        }
    }

    private void set2Players(int[][] board) {
        int goodPoint = width / 2;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                board[i - 1][goodPoint + 2 * j] = 4;
                board[height - i][goodPoint + 2 * j] = 1;
            }
            goodPoint = goodPoint - 1;
        }
    }

    private void set3Players(int[][] board) {
        int goodPoint = width / 2;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                board[height - i][goodPoint + 2 * j] = 1;
            }
            goodPoint = goodPoint - 1;
        }
        goodPoint = 0;
        for (int i = size; i < 2 * size - 1; i++) {
            for (int j = 0; j < size - (i - size) - 1; j++) {
                board[i - 1][goodPoint + 2 * j] = 3;
                board[i - 1][width - goodPoint - 2 * j - 1] = 5;
            }
            goodPoint = goodPoint + 1;
        }
    }

    private void set4Players(int[][] board) {
        int goodPoint = 0;
        for (int i = size; i < 2 * size - 1; i++) {
            for (int j = 0; j < size - (i - size) - 1; j++) {
                board[i - 1][goodPoint + 2 * j] = 3;
                board[i - 1][width - goodPoint - 2 * j - 1] = 5;
                board[height - i][goodPoint + 2 * j] = 2;
                board[height - i][width - goodPoint - 2 * j - 1] = 6;
            }
            goodPoint = goodPoint + 1;
        }
    }

    private void set6Players(int[][] board) {
        int goodPoint = width / 2;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < i; j++) {
                board[i - 1][goodPoint + 2 * j] = 4;
                board[height - i][goodPoint + 2 * j] = 1;
            }
            goodPoint = goodPoint - 1;
        }
        goodPoint = 0;
        for (int i = size; i < 2 * size - 1; i++) {
            for (int j = 0; j < size - (i - size) - 1; j++) {
                board[i - 1][goodPoint + 2 * j] = 3;
                board[i - 1][width - goodPoint - 2 * j - 1] = 5;
                board[height - i][goodPoint + 2 * j] = 2;
                board[height - i][width - goodPoint - 2 * j - 1] = 6;
            }
            goodPoint = goodPoint + 1;
        }
    }
}
