package com.server;

import com.board.ChineseCheckersBoard;
import com.board.ChineseCheckersBoardBuilder;
import com.messages.*;

import java.io.IOException;
import java.util.Random;

/**
 * class that handles one game
 */
public class Game {
    Lobby lobby;
    int playerCount, readyPlayers;
    ChineseCheckersBoard board;
    int[] playerNumbers;
    int currentPlayer;

    /**
     * creates the game
     * @param lobby games lobby
     * @param playerCount number of players
     * @throws Exception throws if size or number of players if wrongly set
     */
    public Game(Lobby lobby, int playerCount) throws Exception {
        this.lobby = lobby;
        this.playerCount = playerCount;
        readyPlayers = 0;
        board = new ChineseCheckersBoardBuilder().setSize(5).setNumberOfPlayers(playerCount).build();
        switch (playerCount) {
            case 2 -> playerNumbers = new int[] { 1, 4 };
            case 3 -> playerNumbers = new int[] { 1, 3, 5 };
            case 4 -> playerNumbers = new int[] { 2, 3, 5, 6 };
            case 6 -> playerNumbers = new int[] { 1, 2, 3, 4, 5, 6 };
        }
        currentPlayer = new Random().nextInt(playerCount);
        for (int i = 0; i < playerCount; i++) {
            gameBeginningMessage gbm = new gameBeginningMessage();
            gbm.setMessageType("gameBeginning");
            gbm.setPlayerCount(playerCount);
            gbm.setPlayerNumber(playerNumbers[i]);
            lobby.getPlayers().get(i).out.writeObject(gbm);
        }
    }

    /**
     * adds one to ready players and when all players are ready starts the game
     */
    public void ready(){
        readyPlayers++;
        if(readyPlayers == playerCount) turn();
    }

    /**
     * informs current player that its his move
     */
    public void turn() {
        joinLobbyMessage jlm = new joinLobbyMessage();
        jlm.setMessageType("turn");
        jlm.setHostName(lobby.getPlayers().get(currentPlayer).userData.getLogin());
        lobby.deliverMessages(jlm);
    }

    /**
     * goes to next player
     */
    public void skipMove() {
        currentPlayer = (currentPlayer + 1) % playerCount;
        turn();
    }

    /**
     * performs move on board
     * @param pawnX pawns x
     * @param pawnY pawns y
     * @param moveX moves x
     * @param moveY moves y
     */
    public void move(int pawnX, int pawnY, int moveX, int moveY) {
        try {
            board.move(pawnX, pawnY, moveX, moveY);
            deliverMove(pawnX, pawnY, moveX, moveY);
            int win = board.checkIfGameEnded(); // sprawdzałem działa
            skipMove();
        } catch (Exception e) {
            MessageHolder mh = new MessageHolder();
            mh.setMessageType("invalid move");
            messageCurrentPlayer(mh);
        }

    }

    /**
     * ends game
     */
    public void endGame() {
        System.out.println("Game Ended");
    }

    /**
     * sends a message to current player
     * @param message message to send
     */
    public void messageCurrentPlayer(MessageHolder message) {
        try {
            lobby.getPlayers().get(currentPlayer).out.writeObject(message);
            ServerCore.getInstance().getController().appendOutput(message.getMessageType());
        } catch (IOException ex) {
            endGame();
        }
    }

    /**
     * informs all players about the move
     * @param pawnX pawns x
     * @param pawnY pawns y
     * @param moveX moves x
     * @param moveY moves y
     */
    public void deliverMove(int pawnX, int pawnY, int moveX, int moveY) {
        MoveMessage mm = new MoveMessage();
        mm.setMessageType("move");
        mm.setAll(pawnX, pawnY, moveX, moveY);
        lobby.deliverMessages(mm);
    }
}
