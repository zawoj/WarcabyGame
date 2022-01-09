package com.client.controllerTest;

import java.io.IOException;
import java.net.MalformedURLException;

import com.client.helpers.Routes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.DebugUtils.informedErrorMessage;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;

@ExtendWith(ApplicationExtension.class)
public class LoginIntoLauncherTest extends ApplicationTest {
    LoginIntoLauncherTest instance;

    @BeforeEach
    public void init() {
        instance = new LoginIntoLauncherTest();
    }

    @Start
    void onStart(Stage stage) throws MalformedURLException, IOException {
        Scene scene = new Scene(FXMLLoader.load(Routes.viewsRoute("LoginIntoLauncher.fxml")));
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void button_are_not_null() {
        verifyThat("#LoginButton", isNotNull(), informedErrorMessage(this));
        verifyThat("#RegisteryButton", isNotNull(), informedErrorMessage(this));
    }

    @Test
    void checkUserAction() {
        clickOn("#LoginField").write("My Login");
        clickOn("#PasswordField").write("12345");
        clickOn("#LoginButton");

    }
}
