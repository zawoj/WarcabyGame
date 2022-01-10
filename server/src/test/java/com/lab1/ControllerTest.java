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
import java.util.concurrent.CountDownLatch;

@ExtendWith(ApplicationExtension.class)
public class ControllerTest extends ApplicationTest{




    // If this test caused a failure please see AppTest for more info



    @Start
    void onStart(Stage stage) throws MalformedURLException, IOException {
        Scene scene = new Scene(FXMLLoader.load(Routes.viewsRoute("Terminal.fxml")));
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void appendTest() throws InterruptedException {
        final TerminalController t = ServerCore.getInstance().getController();
        clickOn("#TerminalField").write("echo 123").press(KeyCode.ENTER);
        t.appendInput("TESTIN");
        t.appendOutput("TESTOUT");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Platform.runLater(countDownLatch::countDown);
        countDownLatch.await();
        Assertions.assertTrue(t.InOutTextArea.getText().contains("TESTIN"));
        Assertions.assertTrue(t.InOutTextArea.getText().contains("TESTOUT"));
    }
}
