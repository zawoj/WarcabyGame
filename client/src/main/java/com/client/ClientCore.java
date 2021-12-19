package com.client;

import com.client.controllers.StartViewController;

/**
 * class being the core of the client.
 * this class is a singleton
 */

public class ClientCore {
    static String IPconnection, PORTconnection;

    public static boolean reqServerConnection() {
        IPconnection = StartViewController.getIP();
        PORTconnection = StartViewController.getPORT();
        System.out.println(IPconnection);
        System.out.println(PORTconnection);

        // TODO send really req to server now is just for testing client
        if (IPconnection.equals("100.00") && PORTconnection.equals("8080")) {
            return true;
        } else {
            return false;
        }

    }
}
