package com.client;

import com.client.controllers.GameViewController;
import com.client.helpers.Routes;
import com.messages.*;

import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Class for read information from a server and intepreated information
 */
public class ConnectionListener extends Thread {
    public ObjectInputStream in;
    public ObjectOutputStream out;
    public MessageHolder currentMessage;
    public boolean creatingGame = false;

    public ConnectionListener(Socket clientSocket) throws Exception {
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    /**
     * Constructor for tests
     */
    public ConnectionListener() {
    }

    /**
     * Start connection listener
     */
    @Override
    public void run() {
        while (true) {
            try {
                currentMessage = (MessageHolder) in.readObject();
                System.out.println(currentMessage.getMessageType());
                messageHandler(currentMessage);
            } catch (Exception exception) {
                break;
            }
        }
    }

    /**
     * Close connection listener
     */
    public void close() {
        try {
            out.close();
            in.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Method calling appropriate methods depending on the message received from the
     * server
     * 
     * @param message message from server
     * @throws MalformedURLException
     * @throws FileNotFoundException
     */
    public void messageHandler(MessageHolder message) throws MalformedURLException {
        switch (message.getMessageType()) {
            case "Registered" -> {
                ClientCore.getInstance().getRegisteryController()
                        .accountCreatedSuccesfullyNotification();
                ClientCore.getInstance().getRegisteryController().setAllCorrect(true);
            }
            case "Register failed" -> {
                ClientCore.getInstance().getRegisteryController().errorNotification();
                ClientCore.getInstance().getRegisteryController().setAllCorrect(false);
            }
            case "Logged in" -> {
                RegisterMessage rm = (RegisterMessage) message;
                ClientCore.getInstance().setLogin(rm.getLogin());
                ClientCore.getInstance().setAvatar(rm.getAvatar());
                ClientCore.getInstance().getLoginIntoLauncherController().LoadDashboardScene();
            }
            case "Login fail" -> ClientCore.getInstance().getLoginIntoLauncherController().ErrorNotification();
            case "LobbyInfo" -> {
                LobbyInfoMessage lm = (LobbyInfoMessage) message;
                ClientCore.getInstance().setLobbyInfo(lm);
                ClientCore.getInstance().getDashboardController().LoadLobby();
                GameViewController x = ClientCore.getInstance().getGameController();
                if (x != null)
                    x.musik.stop();
            }
            case "lobby list info" -> {
                LobbyListMessage llm = (LobbyListMessage) message;
                LinkedList<dummyLobbyClass> lobbys = llm.getLobbys();
                ClientCore.getInstance().getDashboardController().changeLobbyList(lobbys);
                ClientCore.getInstance().getDashboardController().initDashboardGames();
            }
            case "gameBeginning" -> {
                creatingGame = true;
                gameBeginningMessage gbm = (gameBeginningMessage) message;
                try {
                    ClientCore.getInstance().getLobbyController().loadGameScene(gbm.getPlayerCount(),
                            gbm.getPlayerNumber());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case "invalid move" -> {
                ClientCore.getInstance().myTurn = true;
            }
            case "turn" -> {
                joinLobbyMessage jlm = (joinLobbyMessage) message;
                if (Objects.equals(jlm.getHostName(), ClientCore.getInstance().getLogin())) {
                    ClientCore.getInstance().myTurn = true;

                }
                ClientCore.getInstance().currentPlayer = jlm.getHostName();
                ClientCore.getInstance().getGameController().setTurnArrow();

            }
            case "move" -> {
                MoveMessage mm = (MoveMessage) message;
                ClientCore.getInstance().getGameController().getMouseMoveHandler().executeMove(mm.getPawnX(),
                        mm.getPawnY(), mm.getMoveX(), mm.getMoveY());
            }
            case "game history" -> {
                System.out.println("game history");
                GameHistoryMessage ghm = (GameHistoryMessage) message;
                ClientCore.getInstance().gameHistory = ghm.getList();
                ClientCore.getInstance().gamesHistoryController.gamesHistoryList();
            }
        }
    }

    /**
     * @return ObjectOutputStream
     */
    public ObjectOutputStream getOut() {
        return out;
    }
}
