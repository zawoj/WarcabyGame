package com.client.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.MalformedURLException;

import com.client.controllers.RegisteryController;
import com.client.helpers.Routes;
import com.client.helpers.exceptions.StringLengthException;
import com.client.helpers.exceptions.StringSameValidation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

@ExtendWith(ApplicationExtension.class)
public class RegisteryControllerTest extends ApplicationTest {
    RegisteryController instance;

    @BeforeEach
    public void init() {
        instance = new RegisteryController();
    }

    @Start
    void onStart(Stage stage) throws MalformedURLException, IOException {
        Scene scene = new Scene(FXMLLoader.load(Routes.viewsRoute("registerView.fxml")));
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
    }

    @Test
    @DisplayName("Checking: password length validation")
    void testPasswordLength() throws StringLengthException {
        assertEquals(true, instance.passwordValidation("12345"));
    }

    @Test
    @DisplayName("Checking: Throw exception password length validation")
    void testThrowExceptionPasswordLength() throws StringLengthException {
        Throwable exception = assertThrows(StringLengthException.class, () -> instance.passwordValidation("123"));
        assertEquals("Password must be at least 4 characters", exception.getMessage());
    }

    @Test
    @DisplayName("Checking: password and checkPassword are same")
    void testPasswordCheckPassword() throws StringSameValidation {
        assertEquals(true, instance.passwordCheckValidation("12345", "12345"));
    }

    @Test
    @DisplayName("Checking: Throw exception password length validation")
    void testThrowExceptionPasswordCheckPassword() throws StringSameValidation {
        Throwable exception = assertThrows(StringSameValidation.class,
                () -> instance.passwordCheckValidation("12345", "123"));
        assertEquals("Password are not same", exception.getMessage());
    }

    @Test
    void checkUserAction() {
        clickOn("#newLogin").write("New Login");
        clickOn("#newPassword").write("12345");
        clickOn("#checkNewPassword").write("12345");
        clickOn("#avatarChoiceBox").clickOn("Avatar 4");
        clickOn("#buttonCreateAccount");
        verifyThat("#accountCreatedSuccesfully", isVisible());

    }

}
