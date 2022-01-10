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
    GameViewController gameController;
    ConnectionListener conlis;
    String Login;
    int avatar;
    LobbyInfoMessage lobbyInfo;
    public String currentPlayer;
    public Stage programStage;
    public boolean myTurn = false;

    /**
     * Singletion
     * 
     * @return ClientCore instance of ClientCore
     */
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

    /**
     * Setter of the start view controller
     * 
     * @param startViewController start view controller
     */
    public void setStartViewController(StartViewController startViewController) {
        this.startViewController = startViewController;
    }

    /**
     * Getter for start view controller
     * 
     * @return StartViewController start view controller
     */
    public StartViewController getStartViewController() {
        return startViewController;
    }

    /**
     * Setter of the game controller
     * 
     * @param gameController game controller
     */
    public void setGameController(GameViewController gameController) {
        this.gameController = gameController;
    }

    /**
     * Getter for game controller
     * 
     * @return GameViewController game controller
     */
    public GameViewController getGameController() {
        return gameController;
    }

    /**
     * Setter of the login into launcher controller
     * 
     * @param loginIntoLauncherController login into launcher controller
     */
    public void setLoginIntoLauncherController(LoginIntoLauncherController loginIntoLauncherController) {
        this.loginIntoLauncherController = loginIntoLauncherController;
    }

    /**
     * Getter for ger login into launcher controller
     * 
     * @return LoginIntoLauncherController login into launcher controller
     */
    public LoginIntoLauncherController getLoginIntoLauncherController() {
        return loginIntoLauncherController;
    }

    /**
     * Setter of the registeryController
     * 
     * @param registeryController registeryController
     */
    public void setRegisteryController(RegisteryController registeryController) {
        this.registeryController = registeryController;
    }

    /**
     * Getter for registeryController
     * 
     * @return RegisteryController registeryController
     */
    public RegisteryController getRegisteryController() {
        return registeryController;
    }

    /**
     * Setter of the dashboardController
     * 
     * @param dashboardController dashboardController
     */
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    /**
     * Getter for dashboardController
     * 
     * @return DashboardController dashboardController
     */
    public DashboardController getDashboardController() {
        return dashboardController;
    }

    /**
     * Setter for LobbyController
     * 
     * @param lobbyController
     */
    public void setLobbyController(LobbyController lobbyController) {
        this.lobbyController = lobbyController;
    }

    /**
     * Getter of the lobbyControllerr
     * 
     * @return LobbyController LobbyController
     */
    public LobbyController getLobbyController() {
        return lobbyController;
    }

    /**
     * Getter of the conlis
     * 
     * @return ConnectionListener
     */
    public ConnectionListener getConLis() {
        return conlis;
    }

    /**
     * Setter for the login
     * 
     * @param login login
     */
    public void setLogin(String login) {
        Login = login;
    }

    /**
     * Getter of the login
     * 
     * @return String login
     */
    public String getLogin() {
        return Login;
    }

    /**
     * Setter for the avatar
     * 
     * @param avatar avatar id
     */
    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    /**
     * Getter of the avatar
     * 
     * @return int avatar id
     */
    public int getAvatar() {
        return avatar;
    }

    /**
     * @param ip
     * @param port
     * @throws Exception
     */
    public void reqServerConnection(String ip, String port) throws Exception {
        Socket client = new Socket(ip, Integer.parseInt(port));
        conlis = new ConnectionListener(client);
        conlis.start();
    }

    /**
     * @param login
     * @param password
     * @throws Exception
     */
    public void reqLogin(String login, String password) throws Exception {
        LoginMessage lm = new LoginMessage();
        lm.setMessageType("login");
        lm.setLogin(login);
        lm.setPassword(password);
        conlis.getOut().writeObject(lm);
    }

    /**
     * @param newLogin
     * @param newPassword
     * @param chosenAvatar
     * @throws Exception
     */
    public void reqCreateNewAccount(String newLogin, String newPassword, int chosenAvatar) throws Exception {
        RegisterMessage rm = new RegisterMessage();
        rm.setMessageType("register");
        rm.setLogin(newLogin);
        rm.setPassword(newPassword);
        rm.setAvatar(chosenAvatar);
        conlis.getOut().writeObject(rm);
    }

    public void close() {
        try {
            exitLobby();
        } catch (Exception ignored) {
        }
        if (conlis != null)
            conlis.close();
    }

    /**
     * @throws Exception
     */
    public void sendLobbyListRequest() throws Exception {
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("get lobby info");
        conlis.getOut().writeObject(mh);
    }

    /**
     * @throws Exception
     */
    public void createLobby() throws Exception {
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("Create Lobby");
        conlis.getOut().writeObject(mh);
    }

    /**
     * @param hostName
     * @throws Exception
     */
    public void joinLobby(String hostName) throws Exception {
        joinLobbyMessage mh = new joinLobbyMessage();
        mh.setMessageType("join lobby");
        mh.setHostName(hostName);
        conlis.getOut().writeObject(mh);
    }

    /**
     * @throws IOException
     */
    public void exitLobby() throws IOException {
        joinLobbyMessage mh = new joinLobbyMessage();
        mh.setMessageType("exit lobby");
        mh.setHostName(lobbyInfo.getPlayernames().get(0));
        conlis.getOut().writeObject(mh);
    }

    /**
     * @param lobbyInfo
     */
    public void setLobbyInfo(LobbyInfoMessage lobbyInfo) {
        this.lobbyInfo = lobbyInfo;
    }

    /**
     * @return LobbyInfoMessage
     */
    public LobbyInfoMessage getLobbyInfo() {
        return lobbyInfo;
    }

    /**
     * @param name
     */
    public void changeLobbyName(String name) {
        joinLobbyMessage message = new joinLobbyMessage();
        message.setMessageType("change name");
        message.setHostName(name);
        try {
            conlis.getOut().writeObject(message);
        } catch (Exception ignored) {
        }
    }

    /**
     * @throws IOException
     */
    public void startGame() throws IOException {
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("StartGame");
        conlis.getOut().writeObject(mh);
    }

    /**
     * @param pawnX
     * @param pawnY
     * @param moveX
     * @param moveY
     * @throws IOException
     */
    public void sendMove(int pawnX, int pawnY, int moveX, int moveY) throws IOException {
        MoveMessage mm = new MoveMessage();
        mm.setMessageType("move");
        mm.setAll(pawnX, pawnY, moveX, moveY);
        conlis.getOut().writeObject(mm);
    }

    /**
     * @throws IOException
     */
    public void skipRound() throws IOException {
        if (myTurn) {
            MessageHolder mh = new MessageHolder();
            mh.setMessageType("skip turn");
            conlis.getOut().writeObject(mh);
            myTurn = false;
        }
    }

    public void ready() {
        try {
            MessageHolder mh = new MessageHolder();
            mh.setMessageType("ready");
            conlis.getOut().writeObject(mh);
        } catch (IOException ignored) {
        }
    }
}
