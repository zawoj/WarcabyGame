package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.client.ClientCore;
import com.client.game.Replay;
import com.client.helpers.Routes;
import com.messages.gameHistory;

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

    public LinkedList<gameHistory> historyLinkedList = new LinkedList<>();
    private Integer paginationIndex = 0;
    private static Stage stage;
    private static Parent root;

    @FXML
    public void initialize() throws IOException {
        gamesHistoryList();
        List<String> logins = new ArrayList<>();
        logins.add("Zawoj");
        logins.add("Vipo");
        List<Integer> moveX = new ArrayList<>();
        moveX.add(10);
        moveX.add(8);
        moveX.add(7);
        moveX.add(9);
        moveX.add(8);
        moveX.add(10);
        moveX.add(12);
        moveX.add(11);
        moveX.add(9);
        moveX.add(10);
        moveX.add(9);
        List<Integer> moveY = new ArrayList<>();
        moveY.add(4);
        moveY.add(12);
        moveY.add(5);
        moveY.add(11);
        moveY.add(4);
        moveY.add(10);
        moveY.add(6);
        moveY.add(9);
        moveY.add(7);
        moveY.add(8);
        moveY.add(9);
        List<Integer> pawnX = new ArrayList<>();
        pawnX.add(9);
        pawnX.add(9);
        pawnX.add(10);
        pawnX.add(10);
        pawnX.add(11);
        pawnX.add(11);
        pawnX.add(9);
        pawnX.add(10);
        pawnX.add(8);
        pawnX.add(9);
        pawnX.add(7);
        List<Integer> pawnY = new ArrayList<>();
        pawnY.add(3);
        pawnY.add(13);
        pawnY.add(4);
        pawnY.add(12);
        pawnY.add(3);
        pawnY.add(11);
        pawnY.add(5);
        pawnY.add(10);
        pawnY.add(6);
        pawnY.add(9);
        pawnY.add(7);
        long id = 1;
        historyLinkedList.add(new gameHistory(logins, moveX, moveY, pawnX, pawnY, id));

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
        // Send link list off lobbys init dasboard
        initGamesHistory();
        // Control when pagination button schuld be active
        if (historyLinkedList.size() < 5) {
            paginationButtonNext.setDisable(true);
            paginationButtonPrev.setDisable(true);
        } else if (paginationIndex == ((int) Math.ceil(historyLinkedList.size() / 5))) {
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
                    if (i + (paginationIndex * 5) < historyLinkedList.size()) {
                        GamesCardsPane.getChildren().add(i,
                                gameHistoryCardCreator(historyLinkedList.get(i + (paginationIndex * 5)).getLogins(),
                                        historyLinkedList.get(i + (paginationIndex * 5)).getMoveX(),
                                        historyLinkedList.get(i + (paginationIndex * 5)).getMoveY(),
                                        historyLinkedList.get(i + (paginationIndex * 5)).getPawnX(),
                                        historyLinkedList.get(i + (paginationIndex * 5)).getpawnY()));
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
