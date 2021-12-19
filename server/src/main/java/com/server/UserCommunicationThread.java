package com.server;

import com.server.messages.MessageHolder;

import java.io.*;
import java.net.Socket;

/**
 * Class responsible for connection with one client
 */
public class UserCommunicationThread extends Thread{

    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;
    MessageHolder message;


    UserCommunicationThread(Socket clientSocket){
        this.clientSocket=clientSocket;
    }

    /**
     * Function reading input from client
     */
    @Override
    public void run() {
        try{
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException exception) {
            ServerCore.getInstance().getController().append("failed to connect client" + clientSocket.getInetAddress().getHostAddress()
            );
        }
        do{
            try{
                message = (MessageHolder) in.readObject();
                InputObjectHandling(message);
            } catch (IOException | ClassNotFoundException exception) {
                ServerCore.getInstance().getController().append("Error reading input:" + exception.getLocalizedMessage());
                break;
            }
        }while(message != null);
    }

    public void close() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    /**
     * Function Processing input from client
     * @param message input received from client
     */
    public void InputObjectHandling(MessageHolder message){
        ServerCore.getInstance().getController().append(message.getMessageType());
    }
}
