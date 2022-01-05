package com.client.game;

import com.client.ClientCore;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMoveHandler implements EventHandler<MouseEvent> {
    boolean isPawnChoosen = false;
    int pawnX, pawnY, playerNumber;
    ChineseCheckersBoardAdapter ccba;


    public MouseMoveHandler(ChineseCheckersBoardAdapter ccba) {
        System.out.println("mmh created");
        this.ccba = ccba;
    }


    @Override
    public void handle(MouseEvent mouseEvent) {
        System.out.println("siema " + ClientCore.getInstance().myTurn);
        System.out.println("numer - " + playerNumber);
        Field field = (Field) mouseEvent.getSource();
        System.out.println("klikam - " + ccba.checkersBoard.getBoard()[field.getHeight()][field.getWidth()]);
        if(!ClientCore.getInstance().myTurn) return;
        try {
            if (isPawnChoosen) {
                move(pawnX, pawnY, field.getWidth(), field.getHeight());
                isPawnChoosen = false;
            } else {
                pawnX = field.getWidth();
                pawnY = field.getHeight();
                if(ccba.checkersBoard.getBoard()[pawnY][pawnX] != playerNumber ) return;
                isPawnChoosen = true;
            }
        } catch (Exception e) {
            isPawnChoosen = false;
        }
    }

    private void move(int pawnX, int pawnY, int moveX, int moveY) throws Exception {
        ChineseCheckersBoard Logic = ccba.checkersBoard.setValidMoves(pawnY, pawnX);
        if (Logic.getBoard()[moveY][moveX] == 1) {
            ClientCore.getInstance().sendMove(pawnX, pawnY, moveX, moveY);
            ClientCore.getInstance().myTurn = false;
        } else {
            throw new Exception("invalid move");
        }
    }

    public void executeMove(int pawnX, int pawnY, int moveX, int moveY) {
        try {
            ccba.move(pawnX, pawnY, moveX, moveY);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setPlayerNumber(int playerNumber){
        this.playerNumber = playerNumber;
    }
}
