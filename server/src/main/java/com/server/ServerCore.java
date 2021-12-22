package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;

import com.controllers.TerminalController;

/**
 * class being the core of the server.
 * this class is a singleton
 */
public class ServerCore {
    private static volatile ServerCore instance;
    private TerminalController terminalController;
    private ServerSocket serverSocket;
    private final LinkedList<UserCommunicationThread> userConnections;
    boolean isRunning;

    DataBaseManager dataBaseManager;

    private ServerCore() {

        userConnections = new LinkedList<>();
        isRunning = false;
    }

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
     * -returns the window controller used by the class
     * 
     * @return terminal controller
     */
    public TerminalController getController() {
        return terminalController;
    }

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
            case "close" -> close();
            default -> terminalController.append("unknown command: " + splitCommand[0]);
        }
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
    public void close() {
        try {
            dataBaseManager.saveDB();
            serverSocket.close();

            for (UserCommunicationThread UCT : userConnections) {

                UCT.close();
            }
            serverSocket.close();
            terminalController.append("server closed");
        } catch (Exception e) {
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
}
