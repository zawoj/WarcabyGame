package com.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.server.ServerCore;

/**
 * class controlling the terminal window
 */
public class TerminalController implements Initializable {
    @FXML
    public TextArea TerminalText, InOutTextArea;
    @FXML
    public TextField TerminalField;

    LinkedList<String> history = new LinkedList<>();
    int historyindex = -1;

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
            history.addFirst(TerminalField.getText());
            historyindex = -1;
            TerminalField.setText("");
        }else if(e.getCode()==KeyCode.UP){
            if(historyindex<history.size()-1){
                historyindex++;
                TerminalField.setText(history.get(historyindex));
            }

        }else if(e.getCode()==KeyCode.DOWN){
            if(historyindex>-1){
                historyindex--;
                if(historyindex==-1){
                    TerminalField.setText("");
                }else {
                    TerminalField.setText(history.get(historyindex));
                }
            }
        }
    }

    /**
     * adds string to terminal
     * 
     * @param text text to add
     */
    public void append(String text) {
        Platform.runLater(()->TerminalText.setText(TerminalText.getText() + "\n" + text));

    }

    /**
     * initializer which passes this controller to ServerCore and setups ServerCore
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServerCore.getInstance().setController(this);
        ServerCore.getInstance().ServerCoreSetup();
    }

    /**
     * adds string as input
     * @param text text to add
     */
    public void appendInput(String text) {
        Platform.runLater(()->InOutTextArea.setText(InOutTextArea.getText() + "\n<- " + text));
    }

    /**
     * adds string as output
     * @param text text to add
     */
    public void appendOutput(String text) {
        Platform.runLater(()->InOutTextArea.setText(InOutTextArea.getText() + "\n-> " + text));
    }
}
