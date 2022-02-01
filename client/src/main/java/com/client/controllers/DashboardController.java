package com.client.controllers;

import java.io.IOException;
import java.util.LinkedList;

import com.client.ClientCore;
import com.client.helpers.Routes;

import com.messages.dummyLobbyClass;
import javafx.application.Platform;
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

/**
 * Class responsible for controlling the layout of the dasboard and the
 * functionality.
 */
public class DashboardController {
    @FXML
    public Text NickName, paginationPosition;
    @FXML
    private ImageView avatarImage;
    @FXML
    Button createGameButton, logoutButtin, paginationButtonNext, paginationButtonPrev, gameHistory;
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

    /**
     * This method initialize DashboardController by settings nickName and avatar.
     * Also send to server instane of this controller.
     * 
     * @throws IOException if send looby list request fails
     */
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

    /**
     * Method which invokes method which is responsible for loading the lobby;
     */
    @FXML
    public void createLooby() {
        try {
            ClientCore.getInstance().createLobby();
            LoadLobby();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method which send request to server for get lobbyList again
     */
    @FXML
    public void refreshAction() {
        try {
            ClientCore.getInstance().sendLobbyListRequest();
            lobbyList();
        } catch (Exception ignored) {
        }
    }

    /**
     * Method which logout you form your dasboard (account). And show login launcher
     * 
     * @throws IOException if can't find loginIntoLauncher.fxml
     */
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
    public void gameHistoryAction() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) logoutButtin.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("GamesHistory.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method which load next five loobies
     * 
     * @throws IOException
     */
    @FXML
    public void loadNext() throws IOException {
        GamesCardsPane.getChildren().clear();
        paginationIndex++;
        lobbyList();

    }

    /**
     * Method which load previoues five loobies
     * 
     * @throws IOException
     */
    @FXML
    public void loadPrev() throws IOException {
        GamesCardsPane.getChildren().clear();
        paginationIndex--;
        lobbyList();
    }

    /**
     * Method which load all loobies and init dasboard gamesCardsPanes
     * And sett pagination options
     */
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

    /**
     * Method which load game list from linkList and create them
     */
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

    /**
     * Method for creating a new game card and adding it to the list of cards
     * 
     * @param newList new created list
     */
    public void changeLobbyList(final LinkedList<dummyLobbyClass> newList) {
        lobbyLinkedList.clear();
        lobbyLinkedList.addAll(newList);
    }

    /**
     * Method which set players login in dasboard
     * 
     * @param nickName users nickName
     */
    public void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

    /**
     * Method which load user avatar and set him on dasboard
     * 
     * @param avatarNumber users avatar
     */
    public void displayAvatar(Integer avatarNumber) {
        Image avatarImagePreview = new Image(Routes.imageRoute("avatars\\avatar" + avatarNumber + ".png"));
        avatarImage.setImage(avatarImagePreview);
    }

    /**
     * Method which create a new game card and set it on dasboard lobbies list
     * 
     * @param gameName       the name of game which is visible on game card
     * @param playersInLobby information about players in the lobby
     * @param hostName       show who is hosting the game
     * @return gameCardHBox new game cards list
     */
    // Create new card gamer
    public Pane gameCardCreator(String gameName, Integer playersInLobby, String hostName) {

        HBox gameCardHBox = new HBox();
        gameCardHBox.setId("gameCardHBox");

        Button playButton = new Button();
        playButton.setId(hostName);
        playButton.setGraphic(new ImageView(new Image(Routes.imageRoute("Play.png"))));
        playButton.setOnAction(event -> {
            try {
                ClientCore.getInstance().joinLobby(((Node) event.getSource()).getId());
            } catch (Exception ignored) {
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

    /**
     * Method which load new lobby scene and send information about it to the server
     */
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
