package com.client.controllers;

import java.io.IOException;
import java.util.Objects;

import com.client.ClientCore;
import com.client.helpers.Routes;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class LobbyController {
    @FXML
    Text NickName, HostName, Player1Nick, Player2Nick, Player3Nick, Player4Nick, Player5Nick;
    @FXML
    private ImageView avatarImage, HostAvatar, Player1Avatar, Player2Avatar, Player3Avatar, Player4Avatar,
            Player5Avatar;
    @FXML
    TextField gameName;
    @FXML
    Button saveEditButton, goOut, startGame;

    private Boolean saveMode = false;
    private static Stage stage;
    private static Parent root;

    @FXML
    public void initialize() {
        ClientCore.getInstance().setLobbyController(this);
        displayNickName(ClientCore.getInstance().getLogin());
        displayAvatar(ClientCore.getInstance().getAvatar());
        gameName.setPromptText(ClientCore.getInstance().getLobbyInfo().getGameName());
        checkPrivilages();
    }

    @FXML
    public void saveEdit() {
        saveMode = !saveMode;
        if (isHost(ClientCore.getInstance().getLogin())) {
            if (saveMode) {
                // After edit
                saveEditButton.setText("Save");
                gameName.setDisable(false);
                gameName.requestFocus();
            } else if (isHost(ClientCore.getInstance().getLogin())) {
                // After Save
                saveEditButton.setText("Edit");
                gameName.setDisable(true);
                ClientCore.getInstance().changeLobbyName(gameName.getText());
            }
        }
    }

    @FXML
    private void goOut() throws IOException {
        ClientCore.getInstance().exitLobby();
        stage = (Stage) goOut.getScene().getWindow();
        root = FXMLLoader.load(Routes.viewsRoute("DashboardView.fxml"));
        Scene scene = new Scene(root, 1200, 800);
        scene.getStylesheets().add(Routes.styleRoute("app.css"));
        stage.setScene(scene);
        stage.show();
        ClientCore.getInstance().setLobbyController(this);
        gameName.setText("Game Name");
    }

    @FXML
    public void startGame() throws IOException {
        ClientCore.getInstance().startGame();

    }

    public void refreshLobbyData() {
        setUsers();
        checkPrivilages();
    }

    // Set for useers theirs privileges
    private void checkPrivilages() {
        if (isHost(ClientCore.getInstance().getLogin())
                && ClientCore.getInstance().getLobbyInfo().getPlayernames().size() > 1) {
            saveEditButton.setDisable(false);
            startGame.setDisable(false);
        } else if (isHost(ClientCore.getInstance().getLogin())
                && ClientCore.getInstance().getLobbyInfo().getPlayernames().size() == 1) {
            saveEditButton.setDisable(false);
            startGame.setDisable(true);
        } else {
            saveEditButton.setDisable(true);
            startGame.setDisable(true);
            gameName.setDisable(true);
        }
    }

    // Helper function to check that you are host
    private Boolean isHost(String playerName) {
        return Objects.equals(playerName, ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0));
    }

    // Set avatars and nicknames
    private void setUsers() {
        // Set Nicks
        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 1)
            HostName.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(0).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 2)
            Player1Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(1).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 3)
            Player2Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(2).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 4)
            Player3Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(3).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 5)
            Player4Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(4).toString());

        if (ClientCore.getInstance().getLobbyInfo().getPlayernames().size() >= 6)
            Player5Nick.setText(ClientCore.getInstance().getLobbyInfo().getPlayernames().get(5).toString());

        // Set Avatars
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 1)
            HostAvatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(0) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 2)
            Player1Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(1) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 3)
            Player2Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(2) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 4)
            Player3Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(3) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 5)
            Player4Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(4) + ".png")));
        if (ClientCore.getInstance().getLobbyInfo().getPlayerimages().size() >= 6)
            Player5Avatar.setImage(new Image(Routes.imageRoute(
                    "avatars\\avatar" + ClientCore.getInstance().getLobbyInfo().getPlayerimages().get(5) + ".png")));
    }

    public void loadGameScene(int playerCount, int playerNumber) throws Exception {
        final int count = playerCount;
        final int number = playerNumber;
        Platform.runLater(() -> {
            try {
                stage = (Stage) startGame.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(Routes.viewsRoute("GameView.fxml"));
                root = loader.load();
                Scene scene = new Scene(root, 1200, 800);
                scene.getStylesheets().add(Routes.styleRoute("app.css"));
                stage.setScene(scene);
                stage.show();
                ClientCore.getInstance().getGameController().startGameView(count, number);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    private void displayNickName(String nickName) {
        NickName.setTextAlignment(TextAlignment.CENTER);
        NickName.setText(nickName);
    }

    public void displayAvatar(Integer avatarNumber) {
        Image avatarImagePreview = new Image(Routes.imageRoute("avatars\\avatar" + avatarNumber + ".png"));
        avatarImage.setImage(avatarImagePreview);
    }

}