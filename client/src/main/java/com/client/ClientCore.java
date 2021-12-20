package com.client;

/**
 * class being the core of the client.
 * this class is a singleton
 */

public class ClientCore {
    private static volatile ClientCore instance;

    // Singletion
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

        } else {
            throw new Exception("cannot connect");
        }
    }

    public void reqLogin(String login, String password) throws Exception {
        // TODO send really req to server now is just for testing client
        if (login.equals("Zawoj") && password.equals("12345")) {
            System.out.println("Loged successfully");
        } else {
            throw new Exception("Can't Log in");
        }
    }

    public void reqCreateNewAccount(String newLogin, String newPassword, String choosedAvatar) throws Exception {
        // TODO send really req to server now is just for testing client
        if (newLogin.equals("Zawoj")) {
            throw new Exception("This login already exist");
        } else {
            System.out.println("Create Account Sucessfully");
        }
    }
}
