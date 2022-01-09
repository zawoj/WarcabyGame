package com.messages;

public class MoveMessage extends MessageHolder{
    int pawnX, pawnY, moveX, moveY;

    public void setAll(int pawnX, int pawnY, int moveX, int moveY){
        this.pawnX = pawnX;
        this.pawnY = pawnY;
        this.moveX = moveX;
        this.moveY = moveY;
    }

    public int getPawnX() {
        return pawnX;
    }

    public void setPawnX(int pawnX) {
        this.pawnX = pawnX;
    }

    public int getPawnY() {
        return pawnY;
    }

    public void setPawnY(int pawnY) {
        this.pawnY = pawnY;
    }

    public int getMoveX() {
        return moveX;
    }

    public void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    public int getMoveY() {
        return moveY;
    }

    public void setMoveY(int moveY) {
        this.moveY = moveY;
    }
}
