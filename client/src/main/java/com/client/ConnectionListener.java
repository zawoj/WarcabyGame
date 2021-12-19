package com.client;
import com.messages.MessageHolder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionListener extends Thread{
    ObjectInputStream in;
    ObjectOutputStream out;
    MessageHolder currentMessage;

    public ConnectionListener(Socket clientSocket) throws Exception{
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }
    @Override
    public void run() {
        while(true) {
            try {
                currentMessage = (MessageHolder) in.readObject();
            } catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
                break;
            }
        }
    }
}
