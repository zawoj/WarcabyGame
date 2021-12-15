package com.server;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TerminalController {
    @FXML
    TextArea TerminalText;
    @FXML
    TextField TerminalField;

    public TerminalController(){
        ServerCore.getInstance().setController(this);
    }
    public void keypressed(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER) {
            TerminalText.setText(TerminalText.getText()+"\n>"+TerminalField.getText());
            ServerCore.getInstance().command(TerminalField.getText());
            TerminalField.setText("");
        }
    }
    public void append(String text){
        TerminalText.setText(TerminalText.getText()+"\n"+text);
    }
}

