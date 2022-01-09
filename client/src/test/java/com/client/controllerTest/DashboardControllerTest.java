package com.client.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import com.client.controllers.DashboardController;
import com.client.helpers.Routes;
import com.client.helpers.exceptions.StringLengthException;
import com.client.helpers.exceptions.StringSameValidation;
import com.messages.dummyLobbyClass;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.DebugUtils.informedErrorMessage;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;

@ExtendWith(ApplicationExtension.class)
public class DashboardControllerTest extends ApplicationTest {
    public Text NickName, paginationPosition;
    Button createGameButton, logoutButtin, paginationButtonNext, paginationButtonPrev;
    VBox GamesCardsPane;
    Button refreshButton;
    ImageView refreshIcon;

    @Start
    void onStart(Stage stage) throws MalformedURLException, IOException {
        Scene scene = new Scene(FXMLLoader.load(Routes.viewsRoute("DashboardView.fxml")));
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        createGameButton = new Button("createGameButton");
        logoutButtin = new Button("logoutButtin");
        paginationButtonNext = new Button("paginationButtonNext");
        paginationButtonPrev = new Button("paginationButtonPrev");

        NickName = new Text("NickName");
        paginationPosition = new Text("paginationPosition");
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void button_are_not_null() {
        verifyThat("#createGameButton", isNotNull(), informedErrorMessage(this));
        verifyThat("#logoutButtin", isNotNull(), informedErrorMessage(this));
        verifyThat("#paginationButtonNext", isNotNull(), informedErrorMessage(this));
        verifyThat("#paginationButtonPrev", isNotNull(), informedErrorMessage(this));

    }

    @Test
    public void textTest() throws Exception {
        verifyThat("#NickName", isNotNull(), informedErrorMessage(this));
        verifyThat("#paginationPosition", isNotNull(), informedErrorMessage(this));
    }

    @Test
    public void viewsTest() throws Exception {
        verifyThat("#avatarImage", isNotNull(), informedErrorMessage(this));
        verifyThat("#GamesCardsPane", isNotNull(), informedErrorMessage(this));
    }

}
