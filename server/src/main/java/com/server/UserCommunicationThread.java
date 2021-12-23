package com.server;

import com.Database.UserInformationPackage;
import com.messages.LoginMessage;
import com.messages.MessageHolder;
import com.messages.RegisterMessage;

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

    UserCommunicationThread(Socket clientSocket) {
        this.clientSocket = clientSocket;

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
            case "register" -> {
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
            case "login" -> {
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
            case "get lobby info" ->{

            }
        }
    }
}
