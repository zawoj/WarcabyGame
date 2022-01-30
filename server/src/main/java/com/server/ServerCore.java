package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.controllers.TerminalController;
import com.messages.dummyLobbyClass;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * class being the core of the server.
 * this class is a singleton
 */
public class ServerCore {
    private static volatile ServerCore instance;
    private TerminalController terminalController;
    public ServerSocket serverSocket;
    private final LinkedList<UserCommunicationThread> userConnections;
    private final LinkedList<Lobby> serverLobbys;
    public boolean isRunning;
    private DataBaseManager dataBaseManager;
    @Autowired
    public GameHistoryRepository gameHistoryRepository;

    /**
     * creates new server core
     */
    private ServerCore() {
        serverLobbys = new LinkedList<>();
        userConnections = new LinkedList<>();
        isRunning = false;
    }

    /**
     * sets up database manager
     */
    public void ServerCoreSetup() {
        dataBaseManager = new DataBaseManager();
    }

    /**
     * returns the instance of this class
     * 
     * @return instance
     */
    public static ServerCore getInstance() {
        if (instance == null) {
            synchronized (ServerCore.class) {
                if (instance == null) {
                    instance = new ServerCore();
                }
            }
        }
        return instance;
    }

    /**
     * sets the window controller for class to use
     * 
     * @param controller terminal controller
     */
    public void setController(TerminalController controller) {
        this.terminalController = controller;
    }

    /**
     * returns the window controller used by the class
     * 
     * @return terminal controller
     */
    public TerminalController getController() {
        return terminalController;
    }

    /**
     * returns database manager
     * 
     * @return database manager
     */
    public DataBaseManager getDataBaseManager() {
        return dataBaseManager;
    }

    /**
     * function responsible for handling commands typed in terminal
     * 
     * @param command typed command
     */

    public void command(String command) {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length == 0)
            return;
        switch (splitCommand[0]) {
            case "echo" -> terminalController.append(command.substring(4));
            case "start" -> {
                if (splitCommand.length < 2) {
                    terminalController.append("wrong number of arguments");
                } else {
                    try {
                        startServer(Integer.parseInt(splitCommand[1]));
                    } catch (NumberFormatException e) {
                        terminalController.append("wrong port number");
                    }
                }
            }
            case "close" -> close(true);
            case "gamecount" -> terminalController.append("number: "+gameHistoryRepository.count());
            case "listgames" -> listGames();
            case "showgame" -> {
                if(splitCommand.length<2){
                    terminalController.append("please pass game id");
                }else{
                    showGame(splitCommand[1]);
                }
            }
            default -> terminalController.append("unknown command: " + splitCommand[0]);
        }
    }
    private void showGame(String arg){
            try{
                long id = Long.parseLong(arg);
                Optional<GameHistory> game = gameHistoryRepository.findById(id);
                if(game.isEmpty()){
                    terminalController.append("could not find a game with given id");
                    return;
                }
                new Replay(game.get().getLogins(),game.get().getMoveX(),game.get().getMoveY(),game.get().getPawnX(),game.get().getPawnY());
//                StringBuilder bob = new StringBuilder();
//                for(String s: game.get().getLogins()){
//                    bob.append(s).append(" | ");
//                }
//                terminalController.append("game between:"+bob);
//                for(int i = 0; i<game.get().getMoveX().size(); i++){
//                    terminalController.append(game.get().getPawnX().get(i) + "," +game.get().getPawnY().get(i) + " -> " + game.get().getMoveX().get(i) + "," +game.get().getMoveY().get(i));
//                }
            }catch(NumberFormatException ex){
                terminalController.append("wrong id");
            }
    }

    private void listGames(){
        List<GameHistory> games = gameHistoryRepository.findAll();
        for(int i = 0; i<gameHistoryRepository.count(); i++){
            StringBuilder bob = new StringBuilder();
            for(String s: games.get(i).getLogins()){
                bob.append(s).append(" | ");
            }
            terminalController.append("id: "+games.get(i).getId()+" between: " + bob);
        }
        terminalController.append("end of games");
    }

    /**
     * function starting server
     * 
     * @param portNumber port on witch the server starts
     */
    private void startServer(int portNumber) {
        try {
            if (isRunning)
                return;
            isRunning = true;
            serverSocket = new ServerSocket(portNumber);
            terminalController.append("started server at port " + portNumber);
            ConnectionListener conLis = new ConnectionListener(serverSocket);

            conLis.start();
        } catch (IOException exception) {
            terminalController.append(portNumber + " isn't a valid port number");
        }
    }

    /**
     * function closing the server
     */
    public void close(boolean write) {
        try {
            dataBaseManager.saveDB();
            if(serverSocket == null){
                isRunning = false;
                return;
            }
            for (UserCommunicationThread UCT : userConnections) {

                UCT.close();
            }
            serverSocket.close();
            if (write)
                terminalController.append("server closed");
            isRunning = false;
        } catch (Exception e) {
            e.printStackTrace();
            if (write)
                terminalController.append("failed to close server");
        }
    }

    /**
     * returns the list of connections with users
     * 
     * @return list of user connections
     */
    public LinkedList<UserCommunicationThread> getUsers() {
        return userConnections;
    }

    /**
     * returns the list of lobbys
     * 
     * @return list of lobbys
     */
    public LinkedList<Lobby> getLobbys() {
        return serverLobbys;
    }

    public Lobby getLobbybyHost(String host) {
        for (Lobby lobby : serverLobbys) {
            if (Objects.equals(lobby.getHost(), host)) {
                return lobby;
            }
        }
        return null;
    }

    /**
     * returns info about lobbys
     * 
     * @return
     */
    public LinkedList<dummyLobbyClass> getLobbysInfo() {
        LinkedList<dummyLobbyClass> info = new LinkedList<>();
        for (int i = 0; i < serverLobbys.size(); i++) {
            Lobby lobby = serverLobbys.get(i);
            info.add(i, new dummyLobbyClass(lobby.getName(), lobby.getNumberOfPlayers(), lobby.getHost()));
        }
        return info;
    }
    @Transactional
    public void saveGame(GameHistory gameHistory){
        System.out.println("Saving Game...");
        gameHistoryRepository.save(gameHistory);
    }
}
