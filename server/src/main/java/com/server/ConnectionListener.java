package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * class that listens for new connections
 */
public class ConnectionListener extends Thread {
    ServerSocket server;

    ConnectionListener(ServerSocket serverSocket) {
        server = serverSocket;
    }

    /**
     * waits for new connections
     */
    @Override
    public void run() {
        while (true) {
            try {
                Socket client = server.accept();
                UserCommunicationThread UCF = new UserCommunicationThread(client);
                ServerCore.getInstance().getUsers().add(UCF);
                UCF.start();
            } catch (IOException exception) {
                ServerCore.getInstance().getController().append("Server accept failed");
                break;
            }
        }
    }
}
