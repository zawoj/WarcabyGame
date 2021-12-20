package com.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

import com.server.ServerCore;

/**
 * class controlling the terminal window
 */
public class TerminalController implements Initializable {
    @FXML
    TextArea TerminalText, InOutTextArea;
    @FXML
    TextField TerminalField;

    /**
     * function that checks if the typed key is ENTER, if it is it passes the typed
     * function to ServerCore
     * 
     * @param e keyEvent
     */
    public void keypressed(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            TerminalText.setText(TerminalText.getText() + "\n>" + TerminalField.getText());
            ServerCore.getInstance().command(TerminalField.getText());
            TerminalField.setText("");
        }
    }

    /**
     * adds string to terminal
     * 
     * @param text text to add
     */
    public void append(String text) {
        TerminalText.setText(TerminalText.getText() + "\n" + text);
    }

    /**
     * initializer which passes this controller to ServerCore and setups ServerCore
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServerCore.getInstance().setController(this);
        ServerCore.getInstance().ServerCoreSetup();
    }

    public void appendInput(String text) {
        InOutTextArea.setText(InOutTextArea.getText() + "\n<- " + text);
    }

    public void appendOutput(String text) {
        InOutTextArea.setText(InOutTextArea.getText() + "\n-> " + text);
    }
}
