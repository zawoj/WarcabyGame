package com.board;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Class for detecting users mouse events
 */
public class MouseMoveHandler implements EventHandler<MouseEvent> {
    ChineseCheckersBoardAdapter ccba;

    public MouseMoveHandler(ChineseCheckersBoardAdapter ccba) {
        this.ccba = ccba;
    }

    /**
     * Method for checking what the user do
     * 
     * @param mouseEvent users mouseEvent
     */
    @Override
    public void handle(MouseEvent mouseEvent) {

    }

}
