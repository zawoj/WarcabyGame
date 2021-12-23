package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

import com.client.ClientCore;
import com.client.helpers.Routes;
// import com.jfoenix.controls.cells.editors.TextFieldEditorBase;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    public LinkedList lobbyLinkedList = new LinkedList<dummyLobbyClass>();

    @FXML
    public void initialize() throws MalformedURLException, IOException {
        displayNickName(ClientCore.getInstance().getLogin());
        displayAvatar(ClientCore.getInstance().getAvatar());

        // Dummy create list
        lobbyLinkedList.add(new dummyLobbyClass("Game #1", 3, "Zawoj", "Link1"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #2", 1, "Vipo", "Link2"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #3", 5, "Andrzej", "Link3"));
        initDashboardGames(lobbyLinkedList);

    }

    @FXML
    public void createLooby() {
        System.out.println("Create Lobby");
        try {
            ClientCore.getInstance().createLobby();
            LoadLobby();
        } catch (Exception ignored) {

        }
    }

    @FXML
    public void refreshAction() {
        // TODO nice animation
        System.out.println("Refresh !");
    }

    @FXML
    public void logout() throws MalformedURLException, IOException {
        Stage stage;
        Parent root;

        stage = (Stage) logoutButtin.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("loginIntoLauncher.fxml"));
        System.out.println("Connect Server");
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));

        stage.setScene(scene);
        stage.show();
    }

    public void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

    public void displayAvatar(Integer avatarNumber) {
        Image avatarImagePreview = new Image(Routes.imageRoute("avatars\\avatar" + avatarNumber + ".png"));
        avatarImage.setImage(avatarImagePreview);
    }

    public void LoadLobby() {
        System.out.println("Load Lobby");

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (ClientCore.getInstance().getLobbyController() == null) {
                    Stage stage = (Stage) createGameButton.getScene().getWindow();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(Routes.viewsRoute("Lobby.fxml"));
                        Scene scene = new Scene(root, 1200, 800);
                        scene.getStylesheets().add(Routes.styleRoute("app.css"));
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                // ClientCore.getInstance().getLobbyController().refreshData();
            }
        });

    }

    // Laoad game list from linkList and create them
    public void initDashboardGames(LinkedList lobbyList) throws MalformedURLException, IOException {
        for (int i = 0; i < lobbyList.size(); i++) {
            GamesCardsPane.getChildren().add(i, gameCardCreator(((dummyLobbyClass) lobbyList.get(i)).getName(),
                    ((dummyLobbyClass) lobbyList.get(i)).getPlayersInLobby(),
                    ((dummyLobbyClass) lobbyList.get(i)).hostName(),
                    ((dummyLobbyClass) lobbyList.get(i)).somethnikToGame()));
        }

    }

    @FXML
    public void loadNext() {
        System.out.println("Load Next");

    }

    @FXML
    public void loadPrev() {
        System.out.println("Load Prev");
    }

    // Create new card gamer
    // Lepsze style będą. Wszystko będzie lepiej wyrównane
    public Pane gameCardCreator(String gameName, Integer playersInLobby, String hostName, String somethnikToGame) {

        HBox gameCardHBox = new HBox();
        gameCardHBox.setId("gameCardHBox");

        Button playButton = new Button();
        playButton.setGraphic(new ImageView(new Image(Routes.imageRoute("Play.png"))));
        // This will be propably better but for now i dont have any idea
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
        });

        gameCardHBox.getChildren().addAll(new Text(gameName), new Text(playersInLobby + "/6"), new Text(hostName),
                playButton);

        gameCardHBox.setSpacing(40.0);
        gameCardHBox.setAlignment(Pos.CENTER);

        return gameCardHBox;
    }

}
