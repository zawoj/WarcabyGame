package com.server;

import com.Database.UserInformationPackage;
import com.messages.*;

import java.io.*;
import java.net.Socket;

/**
 * Class responsible for connection with one client
 */
public class UserCommunicationThread extends Thread {

    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;
    MessageHolder message;
    UserInformationPackage userData;
    Lobby myLobby = null;

    /**
     * creates new user thread
     * @param clientSocket users socket
     */
    UserCommunicationThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * sets player lobby
     * @param lobby
     */
    public void setLobby(Lobby lobby){
        myLobby = lobby;
    }

    /**
     * Function reading input from client
     */
    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException exception) {
            ServerCore.getInstance().getController()
                    .append("failed to connect client" + clientSocket.getInetAddress().getHostAddress());
            ServerCore.getInstance().getUsers().remove(this);
            return;
        }
        do {
            try {
                message = (MessageHolder) in.readObject();
                InputObjectHandling(message);
            } catch (IOException | ClassNotFoundException exception) {
                ServerCore.getInstance().getController().append("Error reading input:" + exception);
                ServerCore.getInstance().getUsers().remove(this);
                break;
            }
        } while (message != null);
    }

    /**
     * closes connection
     * @throws IOException
     */
    public void close() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    /**
     * Function Processing input from client
     * 
     * @param message input received from client
     */
    public void InputObjectHandling(MessageHolder message) throws IOException {
        ServerCore.getInstance().getController().appendInput(message.getMessageType());
        switch (message.getMessageType()) {
            case "register" -> register(message);
            case "login" -> login(message);
            case "get lobby info" -> getLobbyInfo();
            case "Create Lobby" -> createLobby();
            case "join lobby" -> joinLobby(message);
            case "exit lobby" -> exitLobby();
            case  "StartGame" -> startGame();
            case "move" -> move(message);
            case "skip turn" ->skipTurn();
            case "change name" -> changeLobbyName(message);
            case "ready" -> ready();
        }
    }
    private void register(MessageHolder message) throws IOException {
        RegisterMessage rm = (RegisterMessage) message;
        MessageHolder ms = new MessageHolder();
        if (ServerCore.getInstance().getDataBaseManager().checkIfUserInDatabase(rm.getLogin())) {
            ms.setMessageType("Register failed");
        } else {
            ServerCore.getInstance().getDataBaseManager().addUser(rm.getLogin(), rm.getPassword(),
                    rm.getAvatar());
            ms.setMessageType("Registered");
        }
        out.writeObject(ms);
        ServerCore.getInstance().getController().appendOutput(ms.getMessageType());
    }
    private void login(MessageHolder message) throws IOException{
        LoginMessage lm = (LoginMessage) message;
        RegisterMessage rm = new RegisterMessage();
        if (ServerCore.getInstance().getDataBaseManager().checkIfUserInDatabase(lm.getLogin())) {
            UserInformationPackage uip = ServerCore.getInstance().getDataBaseManager()
                    .getUserByLogin(lm.getLogin());
            if (uip.getPassword().equals(lm.getPassword())) {
                userData = uip;
                rm.setMessageType("Logged in");
                rm.setPassword(lm.getPassword());
                rm.setLogin(lm.getLogin());
                rm.setAvatar(uip.getAvatarNbr());
            } else {
                rm.setMessageType("Login fail");
            }
        } else {
            rm.setMessageType("Login fail");
        }
        out.writeObject(rm);
        ServerCore.getInstance().getController().appendOutput(rm.getMessageType());
    }
    private void getLobbyInfo() throws IOException{
        LobbyListMessage llm = new LobbyListMessage();
        llm.setMessageType("lobby list info");
        llm.setLobbys(ServerCore.getInstance().getLobbysInfo());
        out.writeObject(llm);
        ServerCore.getInstance().getController().appendOutput(llm.getMessageType());
    }
    private void createLobby(){
        Lobby lobby = new Lobby();
        lobby.addPlayer(this);
        ServerCore.getInstance().getLobbys().add(lobby);
    }
    private void joinLobby(MessageHolder message){
        joinLobbyMessage jlm = (joinLobbyMessage) message;
        Lobby lobby = ServerCore.getInstance().getLobbybyHost(jlm.getHostName());
        if(lobby != null) lobby.addPlayer(this);
    }
    private void exitLobby(){
        if(myLobby != null) myLobby.removePlayer(this);
        myLobby = null;
    }
    public void changeLobbyName(MessageHolder message){
        joinLobbyMessage jm = (joinLobbyMessage) message;
        if(myLobby != null) myLobby.setName(jm.getHostName());
    }
    private void startGame(){
        if(myLobby != null) myLobby.start();
    }
    private void move(MessageHolder message){
        MoveMessage mm = (MoveMessage) message;
        if(myLobby != null) myLobby.game.move(mm.getPawnX(), mm.getPawnY(), mm.getMoveX(), mm.getMoveY());
    }
    private void skipTurn(){
        myLobby.game.skipMove();
    }
    private void ready(){
        if(myLobby != null) myLobby.game.ready();
    }
}
