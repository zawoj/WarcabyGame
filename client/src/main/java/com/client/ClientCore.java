package com.client;

/**
 * class being the core of the client.
 * this class is a singleton
 */

public class ClientCore {
    private static volatile ClientCore instance;

    public static ClientCore getInstance() {
        if (instance == null) {
            synchronized (ClientCore.class) {
                if (instance == null) {
                    instance = new ClientCore();
                }
            }
        }
        return instance;
    }

    public void reqServerConnection(String ip, String port) throws Exception {

        // TODO send really req to server now is just for testing client
        if (ip.equals("100.00") && port.equals("8080")) {
            System.out.println("connection established");
            ;
        } else {
            throw new Exception("cannot connect");
        }
    }
}
