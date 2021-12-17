package com.server;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.util.LinkedList;

public class ServerCore {
    private static volatile ServerCore instance;
    private TerminalController terminalController;
    private ServerSocket serverSocket;
    private final LinkedList<UserCommunicationThread> userConnections;
    boolean isRunning;

    private ServerCore(){
        userConnections = new LinkedList<>();
        isRunning = false;
    }

    public static ServerCore getInstance() {
        if(instance == null){
            synchronized (ServerCore.class){
                if(instance == null){
                    instance = new ServerCore();
                }
            }
        }
        return instance;
    }

    public void setController(TerminalController controller){
        this.terminalController=controller;
    }

    public TerminalController getController(){
        return terminalController;
    }

    public void command(String command){
        String[] splitCommand = command.split(" ");
        if(splitCommand.length == 0) return;
        switch (splitCommand[0]){
            case "echo" -> terminalController.append(command.substring(4));
            case "start" ->{
                if(splitCommand.length<2){
                    terminalController.append("wrong number of arguments");
                }else{
                    try {
                        startServer(Integer.parseInt(splitCommand[1]));
                    }catch(NumberFormatException e){
                        terminalController.append("wrong port number");
                    }
                }
            }
            case "close" -> close();
            default -> terminalController.append("unknown command: "+splitCommand[0]);
        }
    }
    private void startServer(int portNumber){
        try{
            if(isRunning) return;
            isRunning = true;
            serverSocket = new ServerSocket(portNumber);
            terminalController.append("started server at port "+portNumber);
            ConnectionListener conLis = new ConnectionListener(serverSocket);
            conLis.start();
        } catch (IOException exception) {
            terminalController.append(portNumber + " isn't a valid port number");
        }
    }
    private void close() {
        try {
            serverSocket.close();
            for(UserCommunicationThread UCT: userConnections){
                UCT.close();
            }
            terminalController.append("server closed");
        }catch (Exception e){
            terminalController.append("failed to close server");
        }
    }
    public LinkedList<UserCommunicationThread> getUsers(){
        return userConnections;
    }
}

