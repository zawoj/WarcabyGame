package com.client.game;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseMoveHandler implements EventHandler<MouseEvent> {
    boolean isPawnChoosen = false;
    int pawnX, pawnY;
    ChineseCheckersBoardAdapter ccba;

    public MouseMoveHandler(ChineseCheckersBoardAdapter ccba) {
        this.ccba = ccba;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        Field field = (Field) mouseEvent.getSource();
        try {
            if (isPawnChoosen) {
                ccba.move(pawnX, pawnY, field.getWidth(), field.getHeight());
                isPawnChoosen = false;
            } else {
                pawnX = field.getWidth();
                pawnY = field.getHeight();
                isPawnChoosen = true;
            }
        } catch (Exception e) {
            isPawnChoosen = false;
        }
    }
}
