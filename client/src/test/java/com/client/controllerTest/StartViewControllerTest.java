package com.client.controllerTest;

import java.io.IOException;
import java.net.MalformedURLException;

import com.client.helpers.Routes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.base.WindowMatchers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
public class StartViewControllerTest extends ApplicationTest {
    @Start
    void onStart(Stage stage) throws MalformedURLException, IOException {
        stage.setScene(new Scene(FXMLLoader.load(Routes.viewsRoute("StartView.fxml"))));
        stage.show();
    }


    @Test
    void checkExistConnectServerButton() {
        verifyThat("#connectServerButton", hasText("Connect"));
    }

    @Test
    void checkExistErrorButton() {
        verifyThat("#errorButton", hasText(""));
    }

    @Test
    void checkUserAction() {
        clickOn("#IP").write("localhost");
        clickOn("#PORT").write("12345");
        clickOn("#connectServerButton");

    }

}
