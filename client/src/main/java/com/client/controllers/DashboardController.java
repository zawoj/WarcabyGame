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
import javafx.scene.Node;
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

    // Varible for help which card games we schuld load
    private Integer paginationIndex = 0;

    @FXML
    public void initialize() throws MalformedURLException, IOException {
        displayNickName(ClientCore.getInstance().getLogin());
        displayAvatar(ClientCore.getInstance().getAvatar());

        // Dummy create list
        lobbyLinkedList.clear();
        lobbyLinkedList.add(new dummyLobbyClass("Game #1", 3, "Zawoj", "Link1"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #2", 1, "Vipo", "Link2"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #3", 5, "Andrzej", "Link3"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #4", 5, "Andrzej", "Link4"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #5", 2, "Andrzej", "Link5"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #6", 1, "Andrzej", "Link6"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #7", 4, "Andrzej", "Link7"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #8", 5, "Andrzej", "Link8"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #9", 6, "Andrzej", "Link9"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #10", 2, "Andrzej", "Link10"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #11", 4, "Andrzej", "Link11"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #12", 3, "Andrzej", "Link12"));
        lobbyLinkedList.add(new dummyLobbyClass("Game #13", 3, "Andrzej", "Link13"));
        // Send link list off lobbys init dasboard
        initDashboardGames(lobbyLinkedList);

        // Control when pagination button schuld be active
        if (paginationIndex == ((int) Math.ceil(lobbyLinkedList.size() / 5))) {
            paginationButtonNext.setDisable(true);
        } else if (paginationIndex == 0) {
            paginationButtonPrev.setDisable(true);
        } else {
            paginationButtonNext.setDisable(false);
            paginationButtonPrev.setDisable(false);
        }

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

    // Load game list from linkList and create them
    public void initDashboardGames(LinkedList lobbyList) throws MalformedURLException, IOException {
        for (int i = 0; i < 5; i++) {
            if (i + (paginationIndex * 5) < lobbyList.size()) {
                GamesCardsPane.getChildren().add(i,
                        gameCardCreator(((dummyLobbyClass) lobbyList.get(i + (paginationIndex * 5))).getName(),
                                ((dummyLobbyClass) lobbyList.get(i + (paginationIndex * 5))).getPlayersInLobby(),
                                ((dummyLobbyClass) lobbyList.get(i + (paginationIndex * 5))).hostName(),
                                ((dummyLobbyClass) lobbyList.get(i + (paginationIndex * 5))).somethnikToGame()));
            }
        }

    }

    @FXML
    public void loadNext() throws MalformedURLException, IOException {
        GamesCardsPane.getChildren().clear();
        paginationIndex++;
        initialize();

    }

    @FXML
    public void loadPrev() throws MalformedURLException, IOException {
        GamesCardsPane.getChildren().clear();
        paginationIndex--;
        initialize();
    }

    // Creator of game's card TODO make better styles
    public Pane gameCardCreator(String gameName, Integer playersInLobby, String hostName, String somethnikToGame) {

        HBox gameCardHBox = new HBox();
        gameCardHBox.setId("gameCardHBox");

        Button playButton = new Button();
        playButton.setId(somethnikToGame);
        playButton.setGraphic(new ImageView(new Image(Routes.imageRoute("Play.png"))));
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(((Node) event.getSource()).getId());
            }
        });

        gameCardHBox.getChildren().addAll(new Text(gameName), new Text(playersInLobby + "/6"), new Text(hostName),
                playButton);

        gameCardHBox.setSpacing(40.0);
        gameCardHBox.setAlignment(Pos.CENTER);

        return gameCardHBox;
    }

}
