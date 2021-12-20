package com.client;

import com.client.controllers.LoginIntoLauncherController;
import com.client.controllers.RegisteryController;
import com.client.controllers.StartViewController;
import com.messages.RegisterMessage;

import java.net.Socket;

/**
 * class being the core of the client.
 * this class is a singleton
 */

public class ClientCore {
    private static volatile ClientCore instance;
    StartViewController startViewController;
    LoginIntoLauncherController loginIntoLauncherController;
    RegisteryController registeryController;
    ConnectionListener conlis;

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

    public void setStartViewController(StartViewController startViewController) {
        this.startViewController = startViewController;
    }

    public void setLoginIntoLauncherController(LoginIntoLauncherController loginIntoLauncherController) {
        this.loginIntoLauncherController = loginIntoLauncherController;
    }

    public void setRegisteryController(RegisteryController registeryController) {
        this.registeryController = registeryController;
    }

    public StartViewController getStartViewController() {
        return startViewController;
    }

    public LoginIntoLauncherController getLoginIntoLauncherController() {
        return loginIntoLauncherController;
    }

    public RegisteryController getRegisteryController() {
        return registeryController;
    }

    public void reqServerConnection(String ip, String port) throws Exception {
        Socket client = new Socket(ip, Integer.parseInt(port));
        conlis = new ConnectionListener(client);
        conlis.start();
    }

    public void reqLogin(String login, String password) throws Exception {
        // TODO send really req to server now is just for testing client
        if (login.equals("Zawoj") && password.equals("12345")) {
            System.out.println("Loged successfully");
        } else {
            throw new Exception("Can't Log in");
        }
    }

    public void reqCreateNewAccount(String newLogin, String newPassword, int chosenAvatar) throws Exception {
        RegisterMessage rm = new RegisterMessage();
        rm.setMessageType("register");
        rm.setLogin(newLogin);
        rm.setPassword(newPassword);
        rm.setAvatar(chosenAvatar);
        conlis.getOut().writeObject(rm);
    }
    public void close(){
        if(conlis!=null) conlis.close();
    }
}
