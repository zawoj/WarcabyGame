package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.client.ClientCore;
import com.client.game.Replay;
import com.client.helpers.Routes;
import com.messages.History;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class gamesHistory {
    @FXML
    public Text paginationPosition;
    @FXML
    Button backToDashboard, paginationButtonNext, paginationButtonPrev;
    @FXML
    VBox GamesCardsPane;

    private Integer paginationIndex = 0;
    private static Stage stage;
    private static Parent root;

    @FXML
    public void initialize() throws IOException {
       ClientCore.getInstance().gamesHistoryController = this;
       ClientCore.getInstance().getHistory();
    }

    @FXML
    public void loadNext() throws IOException {
        GamesCardsPane.getChildren().clear();
        paginationIndex++;
        gamesHistoryList();

    }

    @FXML
    public void loadPrev() throws IOException {
        GamesCardsPane.getChildren().clear();
        paginationIndex--;
        gamesHistoryList();
    }

    @FXML
    public void backToDashboardAction() throws MalformedURLException, IOException {
        stage = (Stage) backToDashboard.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("DashboardView.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();

    }

    public void gamesHistoryList() {
        System.out.println("gameHistoryList");
        // Send link list off lobbys init dasboard
        initGamesHistory();
        // Control when pagination button schuld be active
        if (ClientCore.getInstance().gameHistory.size() < 5) {
            paginationButtonNext.setDisable(true);
            paginationButtonPrev.setDisable(true);
        } else if (paginationIndex == ((int) Math.ceil(ClientCore.getInstance().gameHistory.size() / 5))) {
            paginationButtonNext.setDisable(true);
            paginationButtonPrev.setDisable(false);
        } else if (paginationIndex == 0) {
            paginationButtonPrev.setDisable(true);
            paginationButtonNext.setDisable(false);
        } else {
            paginationButtonNext.setDisable(false);
            paginationButtonPrev.setDisable(false);
        }
    }

    public void initGamesHistory() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                GamesCardsPane.getChildren().clear();
                for (int i = 0; i < 5; i++) {
                    if (i + (paginationIndex * 5) < ClientCore.getInstance().gameHistory.size()) {
                        GamesCardsPane.getChildren().add(i,
                                gameHistoryCardCreator(ClientCore.getInstance().gameHistory.get(i + (paginationIndex * 5)).getLogins(),
                                        ClientCore.getInstance().gameHistory.get(i + (paginationIndex * 5)).getMoveX(),
                                        ClientCore.getInstance().gameHistory.get(i + (paginationIndex * 5)).getMoveY(),
                                        ClientCore.getInstance().gameHistory.get(i + (paginationIndex * 5)).getPawnX(),
                                        ClientCore.getInstance().gameHistory.get(i + (paginationIndex * 5)).getpawnY()));
                    }
                }
            }
        });
    }

    public Pane gameHistoryCardCreator(List<String> gameName, List<Integer> moveX, List<Integer> moveY,
            List<Integer> pawnX,
            List<Integer> pawnY) {

        HBox gameCardHBox = new HBox();
        gameCardHBox.setId("gameCardHBox");
        System.out.println(moveY);
        System.out.println(moveX);
        System.out.println(pawnX);
        System.out.println(pawnY);

        Button playButton = new Button();

        playButton.setGraphic(new ImageView(new Image(Routes.imageRoute("Play.png"))));
        playButton.setOnAction(event -> {
            new Replay(gameName, moveX, moveY, pawnX, pawnY);
        });

        gameCardHBox.getChildren().addAll(new Text(gameName.toString()), playButton);

        gameCardHBox.setSpacing(40.0);
        gameCardHBox.setAlignment(Pos.CENTER);

        return gameCardHBox;
    }

}
