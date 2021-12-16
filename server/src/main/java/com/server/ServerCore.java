package com.server;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;

public class ServerCore {
    private static volatile ServerCore instance;
    private TerminalController terminalController;
    ServerSocket serverSocket;

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
    public void startServer(int portNumber){
        try{
            serverSocket = new ServerSocket(portNumber);
            terminalController.append("started server at port "+portNumber);
        } catch (IOException exception) {
            terminalController.append(portNumber + " isn't a valid port number");
        }
    }
    public void close() {
        try {
            serverSocket.close();
            terminalController.append("server closed");
        }catch (Exception e){
            terminalController.append("failed to close server");
        }
    }
}

