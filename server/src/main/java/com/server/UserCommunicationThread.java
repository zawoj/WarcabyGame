package com.server;

import com.messages.MessageHolder;

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
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException exception) {
            ServerCore.getInstance().getController().append("failed to connect client" + clientSocket.getInetAddress().getHostAddress());
            return;
        }
        do{
            try{
                message = (MessageHolder) in.readObject();
                InputObjectHandling(message);
            } catch (IOException | ClassNotFoundException exception) {
                ServerCore.getInstance().getController().append("Error reading input:" + exception);
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
