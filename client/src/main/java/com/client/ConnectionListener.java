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
                messageHandler(currentMessage);
            } catch (Exception exception) {
                break;
            }
        }
    }
    public void close(){
        try {
            out.close();
            in.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void messageHandler(MessageHolder message){
        switch(message.getMessageType()){
            case "Registered" : ClientCore.getInstance().getRegisteryController().accountCreatedSuccesfullyNotification();
            break;
            case "Register failed" : ClientCore.getInstance().getRegisteryController().errorNotification();
        }
    }

    public ObjectOutputStream getOut() {
        return out;
    }
}
