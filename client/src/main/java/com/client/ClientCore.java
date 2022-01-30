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
     * Request to server information about to which port and ip we want to connect
     * 
     * @param ip   ip to connect
     * @param port port to connect
     * @throws Exception if an error occurs
     */
    public void reqServerConnection(String ip, String port) throws Exception {
        Socket client = new Socket(ip, Integer.parseInt(port));
        conlis = new ConnectionListener(client);
        conlis.start();
    }

    /**
     * Request to server information about account which we want to login in
     * 
     * @param login    users login
     * @param password users password
     * @throws Exception throws if an error occurs
     */
    public void reqLogin(String login, String password) throws Exception {
        LoginMessage lm = new LoginMessage();
        lm.setMessageType("login");
        lm.setLogin(login);
        lm.setPassword(password);
        conlis.getOut().writeObject(lm);
    }

    /**
     * Request to server information about account which we want to create
     * 
     * @param newLogin     name of the new login
     * @param newPassword  users new password
     * @param chosenAvatar id of chosen avatar
     * @throws Exception throws if something error occurs
     */
    public void reqCreateNewAccount(String newLogin, String newPassword, int chosenAvatar) throws Exception {
        RegisterMessage rm = new RegisterMessage();
        rm.setMessageType("register");
        rm.setLogin(newLogin);
        rm.setPassword(newPassword);
        rm.setAvatar(chosenAvatar);
        conlis.getOut().writeObject(rm);
    }

    /**
     * Method which cloase connection with server
     */
    public void close() {
        try {
            exitLobby();
        } catch (Exception ignored) {
        }
        if (conlis != null)
            conlis.close();
    }

    /**
     * Request to server for get information about list of lobbys
     * 
     * @throws Exception throws if an error occurs
     */
    public void sendLobbyListRequest() throws Exception {
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("get lobby info");
        conlis.getOut().writeObject(mh);
    }

    /**
     * Send to server information about new lobby created
     * 
     * @throws Exception throws if an error occurs
     */
    public void createLobby() throws Exception {
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("Create Lobby");
        conlis.getOut().writeObject(mh);
    }

    /**
     * Method for connect to lobby
     * 
     * @param hostName this is not host name, this is information about which user
     *                 want to connect to lobby
     * @throws Exception throws if an error occurs
     */
    public void joinLobby(String hostName) throws Exception {
        joinLobbyMessage mh = new joinLobbyMessage();
        mh.setMessageType("join lobby");
        mh.setHostName(hostName);
        conlis.getOut().writeObject(mh);
    }

    /**
     * Method to exit from the lobby
     * 
     * @throws IOException throws if an error occurs
     */
    public void exitLobby() throws IOException {
        joinLobbyMessage mh = new joinLobbyMessage();
        mh.setMessageType("exit lobby");
        mh.setHostName(lobbyInfo.getPlayernames().get(0));
        conlis.getOut().writeObject(mh);
        myTurn = false;
    }

    /**
     * Setter for lobbyInfo
     * 
     * @param lobbyInfo lobbyInfo
     */
    public void setLobbyInfo(LobbyInfoMessage lobbyInfo) {
        this.lobbyInfo = lobbyInfo;
    }

    /**
     * Getter of the lobbyInfo
     * 
     * @return LobbyInfoMessage lobbyInfo
     */
    public LobbyInfoMessage getLobbyInfo() {
        return lobbyInfo;
    }

    /**
     * Method which change the lobby name and send it to the server
     * 
     * @param name new name of lobby
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
     * Method which send to server information about that game have started and then
     * (on server) send it to all users.
     * 
     * @throws IOException throws if an error occurs
     */
    public void startGame() throws IOException {
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("StartGame");
        conlis.getOut().writeObject(mh);
    }

    /**
     * Method send to server information about player's move
     * 
     * @param pawnX pawn position X
     * @param pawnY pawn position Y
     * @param moveX move position X
     * @param moveY move position Y
     * @throws IOException throws if an error occurs
     */
    public void sendMove(int pawnX, int pawnY, int moveX, int moveY) throws IOException {
        MoveMessage mm = new MoveMessage();
        mm.setMessageType("move");
        mm.setAll(pawnX, pawnY, moveX, moveY);
        conlis.getOut().writeObject(mm);
    }

    /**
     * Method send to server information about that player skip round
     * 
     * @throws IOException throws if an error occurs
     */
    public void skipRound() throws IOException {
        if (myTurn) {
            MessageHolder mh = new MessageHolder();
            mh.setMessageType("skip turn");
            conlis.getOut().writeObject(mh);
            myTurn = false;
        }
    }

    /**
     * Method send to server information about that player are ready to start
     * playing
     */
    public void ready() {
        try {
            MessageHolder mh = new MessageHolder();
            mh.setMessageType("ready");
            conlis.getOut().writeObject(mh);
        } catch (IOException ignored) {
        }
    }
}
