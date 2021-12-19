package com.client;

import com.client.controllers.StartViewController;

import java.net.Socket;

/**
 * class being the core of the client.
 * this class is a singleton
 */

public class ClientCore {
    private static volatile ClientCore instance;


    public static ClientCore getInstance(){
        if(instance == null){
            synchronized (ClientCore.class){
                if(instance == null){
                    instance = new ClientCore();
                }
            }
        }
        return instance;
    }

    public void reqServerConnection(String ip, String port) throws Exception{
        Socket client = new Socket(ip,Integer.parseInt(port));
        ConnectionListener cl = new ConnectionListener(client);
        cl.start();
    }
}
