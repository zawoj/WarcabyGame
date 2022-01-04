package com.client;

import com.client.controllers.*;
import com.messages.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

/**
 * class being the core of the client.
 * this class is a singleton
 */

public class ClientCore {
    private static volatile ClientCore instance;
    StartViewController startViewController;
    LoginIntoLauncherController loginIntoLauncherController;
    RegisteryController registeryController;
    DashboardController dashboardController;
    LobbyController lobbyController;
    ConnectionListener conlis;
    String Login;
    int avatar;
    LobbyInfoMessage lobbyInfo;
    public Stage programStage;

    // Singletion
    public static ClientCore getInstance() {
        if (instance == null) {
            synchronized (ClientCore.class) {
                if (instance == null) {
                    instance = new ClientCore();
                }
            }
        }
        return instance;
    }

    public void setStartViewController(StartViewController startViewController) {
        this.startViewController = startViewController;
    }

    public void setLoginIntoLauncherController(LoginIntoLauncherController loginIntoLauncherController) {
        this.loginIntoLauncherController = loginIntoLauncherController;
    }

    public void setRegisteryController(RegisteryController registeryController) {
        this.registeryController = registeryController;
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public LobbyController getLobbyController() {
        return lobbyController;
    }

    public void setLobbyController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    public StartViewController getStartViewController() {
        return startViewController;
    }

    public LoginIntoLauncherController getLoginIntoLauncherController() {
        return loginIntoLauncherController;
    }

    public RegisteryController getRegisteryController() {
        return registeryController;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public void reqServerConnection(String ip, String port) throws Exception {
        Socket client = new Socket(ip, Integer.parseInt(port));
        conlis = new ConnectionListener(client);
        conlis.start();
    }

    public void reqLogin(String login, String password) throws Exception {
        LoginMessage lm = new LoginMessage();
        lm.setMessageType("login");
        lm.setLogin(login);
        lm.setPassword(password);
        conlis.getOut().writeObject(lm);
    }

    public void reqCreateNewAccount(String newLogin, String newPassword, int chosenAvatar) throws Exception {
        RegisterMessage rm = new RegisterMessage();
        rm.setMessageType("register");
        rm.setLogin(newLogin);
        rm.setPassword(newPassword);
        rm.setAvatar(chosenAvatar);
        conlis.getOut().writeObject(rm);
    }

    public void close() {
        if (conlis != null)
            conlis.close();
    }

    public void sendLobbyListRequest() throws Exception {
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("get lobby info");
        conlis.getOut().writeObject(mh);
    }

    public void createLobby() throws Exception {
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("Create Lobby");
        conlis.getOut().writeObject(mh);
    }

    public void joinLobby(String hostName) throws Exception {
        joinLobbyMessage mh = new joinLobbyMessage();
        mh.setMessageType("join lobby");
        mh.setHostName(hostName);
        conlis.getOut().writeObject(mh);
    }

    public void exitLobby() throws IOException {
        joinLobbyMessage mh = new joinLobbyMessage();
        mh.setMessageType("exit lobby");
        mh.setHostName(lobbyInfo.getPlayernames().get(0));
        conlis.getOut().writeObject(mh);
    }

    public LobbyInfoMessage getLobbyInfo() {
        return lobbyInfo;
    }

    public void setLobbyInfo(LobbyInfoMessage lobbyInfo) {
        this.lobbyInfo = lobbyInfo;
    }

}
