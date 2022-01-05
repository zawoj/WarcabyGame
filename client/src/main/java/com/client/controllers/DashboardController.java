package com.client.controllers;

import java.io.IOException;
import java.util.LinkedList;

import com.client.ClientCore;
import com.client.helpers.Routes;
// import com.jfoenix.controls.cells.editors.TextFieldEditorBase;

import com.messages.dummyLobbyClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    Text NickName, paginationPosition;
    @FXML
    private ImageView avatarImage;
    @FXML
    Button createGameButton, logoutButtin, paginationButtonNext, paginationButtonPrev;
    @FXML
    VBox GamesCardsPane;
    @FXML
    Button refreshButton;
    @FXML
    ImageView refreshIcon;
    // Dummy list
    public LinkedList<dummyLobbyClass> lobbyLinkedList = new LinkedList<>();

    // Varible for help which card games we schuld load
    private Integer paginationIndex = 0;

    @FXML
    public void initialize() throws IOException {
        displayNickName(ClientCore.getInstance().getLogin());
        displayAvatar(ClientCore.getInstance().getAvatar());
        ClientCore.getInstance().setDashboardController(this);
        try {
            ClientCore.getInstance().sendLobbyListRequest();
            lobbyList();
        } catch (Exception ignored) {
        }

    }

    @FXML
    public void createLooby() {
        try {
            ClientCore.getInstance().createLobby();
            LoadLobby();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void refreshAction() {
        // TODO nice animation
        try {
            ClientCore.getInstance().sendLobbyListRequest();
            lobbyList();
        } catch (Exception ignored) {
        }
    }

    @FXML
    public void logout() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) logoutButtin.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("loginIntoLauncher.fxml"));
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loadNext() throws IOException {
        GamesCardsPane.getChildren().clear();
        paginationIndex++;
        lobbyList();

    }

    @FXML
    public void loadPrev() throws IOException {
        GamesCardsPane.getChildren().clear();
        paginationIndex--;
        lobbyList();
    }

    public void lobbyList() {
        // Send link list off lobbys init dasboard
        initDashboardGames();
        // Control when pagination button schuld be active
        if (lobbyLinkedList.size() < 5) {
            paginationButtonNext.setDisable(true);
            paginationButtonPrev.setDisable(true);
        } else if (paginationIndex == ((int) Math.ceil(lobbyLinkedList.size() / 5))) {
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

    // Load game list from linkList and create them
    public void initDashboardGames() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                GamesCardsPane.getChildren().clear();
                for (int i = 0; i < 5; i++) {
                    if (i + (paginationIndex * 5) < lobbyLinkedList.size()) {
                        GamesCardsPane.getChildren().add(i,
                                gameCardCreator(lobbyLinkedList.get(i + (paginationIndex * 5)).getName(),
                                        lobbyLinkedList.get(i + (paginationIndex * 5)).getPlayersInLobby(),
                                        lobbyLinkedList.get(i + (paginationIndex * 5)).hostName()));
                    }
                }
            }
        });
    }

    public void changeLobbyList(final LinkedList<dummyLobbyClass> newList) {
        lobbyLinkedList.clear();
        lobbyLinkedList.addAll(newList);
    }

    public void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

    public void displayAvatar(Integer avatarNumber) {
        Image avatarImagePreview = new Image(Routes.imageRoute("avatars\\avatar" + avatarNumber + ".png"));
        avatarImage.setImage(avatarImagePreview);
    }

    // Create new card gamer
    // TODO better styles
    public Pane gameCardCreator(String gameName, Integer playersInLobby, String hostName) {

        HBox gameCardHBox = new HBox();
        gameCardHBox.setId("gameCardHBox");

        Button playButton = new Button();
        playButton.setId(hostName);
        playButton.setGraphic(new ImageView(new Image(Routes.imageRoute("Play.png"))));
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ClientCore.getInstance().joinLobby(((Node) event.getSource()).getId());
                } catch (Exception ignored) {
                }
            }
        });

        if (playersInLobby == 6) {
            playButton.setDisable(true);
        }

        gameCardHBox.getChildren().addAll(new Text(gameName), new Text(playersInLobby + "/6"), new Text(hostName),
                playButton);

        gameCardHBox.setSpacing(40.0);
        gameCardHBox.setAlignment(Pos.CENTER);

        return gameCardHBox;
    }

    public void LoadLobby() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent root = null;
                try {
                    root = FXMLLoader.load(Routes.viewsRoute("Lobby.fxml"));
                    Scene scene = new Scene(root, 1200, 800);
                    scene.getStylesheets().add(Routes.styleRoute("app.css"));
                    ClientCore.getInstance().programStage.setScene(scene);
                    ClientCore.getInstance().programStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ClientCore.getInstance().getLobbyController().refreshLobbyData();
            }
        });

    }

}
