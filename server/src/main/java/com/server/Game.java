package com.server;

import com.board.ChineseCheckersBoard;
import com.board.ChineseCheckersBoardBuilder;
import com.messages.MessageHolder;
import com.messages.MoveMessage;
import com.messages.gameBeginningMessage;

import java.io.IOException;
import java.util.Random;

public class Game {
    Lobby lobby;
    int playerCount;
    ChineseCheckersBoard board;
    int[] playerNumbers;
    int currentPlayer;
    public Game(Lobby lobby, int playerCount) throws Exception {
        this.lobby = lobby;
        this.playerCount=playerCount;
        board = new ChineseCheckersBoardBuilder().setSize(5).setNumberOfPlayers(playerCount).build();
        switch (playerCount){
            case 2 -> playerNumbers = new int[]{1, 4};
            case 3 -> playerNumbers = new int[]{1, 3, 5};
            case 4 -> playerNumbers = new int[]{2, 3, 5, 6};
            case 6 -> playerNumbers = new int[]{1,2,3,4,5,6};
        }
        currentPlayer = new Random().nextInt(playerCount);
        for(int i = 0; i< playerCount; i++){
            gameBeginningMessage gbm = new gameBeginningMessage();
            gbm.setMessageType("gameBeginning");
            gbm.setPlayerCount(playerCount);
            gbm.setPlayerNumber(playerNumbers[i]);
            lobby.getPlayers().get(i).out.writeObject(gbm);
        }
        turn();
    }

    public void turn() {
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("your turn");
        messageCurrentPlayer(mh);
    }
    public void skipMove(){
        currentPlayer = (currentPlayer+1)%playerCount;
        turn();
    }


    public void move(int pawnX, int pawnY, int moveX, int moveY){
        try{
            board.move(pawnX, pawnY, moveX, moveY);
            deliverMove(pawnX, pawnY, moveX, moveY);
            //check if game ended
            skipMove();
        } catch (Exception e) {
            MessageHolder mh = new MessageHolder();
            mh.setMessageType("invalid move");
            messageCurrentPlayer(mh);
        }

    }

    public void endGame(){
        System.out.println("Game Ended");
    }

    public void messageCurrentPlayer(MessageHolder message){
        try {
            lobby.getPlayers().get(currentPlayer).out.writeObject(message);
            ServerCore.getInstance().getController().appendOutput(message.getMessageType());
        }catch (IOException ex){
            endGame();
        }
    }
    public void deliverMove(int pawnX, int pawnY, int moveX, int moveY){
        MoveMessage mm = new MoveMessage();
        mm.setMessageType("move");
        mm.setAll(pawnX, pawnY, moveX, moveY);
        lobby.deliverMessages(mm);
    }
}
