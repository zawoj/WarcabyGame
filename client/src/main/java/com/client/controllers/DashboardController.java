package com.client.controllers;

import java.io.IOException;
import java.net.MalformedURLException;

import com.client.ClientCore;
import com.client.Lobby;
import com.client.helpers.Routes;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class DashboardController {
    @FXML
    Text NickName;
    @FXML
    private ImageView avatarImage;
    @FXML
    Button createGameButton, logoutButtin;

    @FXML
    public void initialize() {
        displayNickName(ClientCore.getInstance().getLogin());
        displayAvatar(ClientCore.getInstance().getAvatar());
        ClientCore.getInstance().setDashboardController(this);
    }

    @FXML
    void createLooby() {
        try {
            ClientCore.getInstance().createLobby();
        }catch(Exception ignored){

        }
    }

    @FXML
    public void logout() throws IOException {
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
        System.out.println(avatarNumber);
        System.out.println(Routes.imageRoute("avatar" + avatarNumber + ".png"));
        Image avatarImagePreview = new Image(Routes.imageRoute("avatars\\avatar" + avatarNumber + ".png"));
        System.out.println(avatarImagePreview);
        avatarImage.setImage(avatarImagePreview);
    }

    public void LoadLobby() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(ClientCore.getInstance().getLobbyController()==null) {
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
                ClientCore.getInstance().getLobbyController().refreshData();
            }
        });
    }

}
