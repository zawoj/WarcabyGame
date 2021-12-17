package com.server;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * class controlling the terminal window
 */
public class TerminalController {
    @FXML
    TextArea TerminalText;
    @FXML
    TextField TerminalField;

    /**
     * constructor which passes this controller to ServerCore
     */
    public TerminalController(){
        ServerCore.getInstance().setController(this);
    }

    /**
     * function that checks if the typed key is ENTER, if it is it passes the typed function to ServerCore
     * @param e keyEvent
     */
    public void keypressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER) {
            TerminalText.setText(TerminalText.getText()+"\n>"+TerminalField.getText());
            ServerCore.getInstance().command(TerminalField.getText());
            TerminalField.setText("");
        }
    }

    /**
     * adds string to terminal
     * @param text text to add
     */
    public void append(String text){
        TerminalText.setText(TerminalText.getText()+"\n"+text);
    }
}

