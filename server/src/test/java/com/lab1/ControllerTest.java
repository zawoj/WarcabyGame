package com.lab1;

import com.controllers.TerminalController;
import com.helpers.Routes;
import com.server.App;
import com.server.ServerCore;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.net.MalformedURLException;

@ExtendWith(ApplicationExtension.class)
public class ControllerTest extends ApplicationTest{
    @Start
    void onStart(Stage stage) throws MalformedURLException, IOException {
        Scene scene = new Scene(FXMLLoader.load(Routes.viewsRoute("Terminal.fxml")));
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void appendTest(){
        TerminalController t = ServerCore.getInstance().getController();
        t.append("TEST");
        Assertions.assertEquals("\nTEST", t.TerminalText.getText());
        t.TerminalText.setText("");
        t.appendInput("TESTIN");
        Assertions.assertEquals("\n<- TESTIN", t.InOutTextArea.getText());
        t.InOutTextArea.setText("");
        t.appendOutput("TESTOUT");
        Assertions.assertEquals("\n-> TESTOUT", t.InOutTextArea.getText());
        clickOn("#TerminalField").write("echo 123").press(KeyCode.ENTER);
        Assertions.assertEquals("", t.TerminalField.getText());
    }
}
