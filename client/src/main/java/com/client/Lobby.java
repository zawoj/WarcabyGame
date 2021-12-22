package com.client;

public class Lobby {
    public String[] players = new String[6];

    public Lobby(String hostName) {
        this.players[0] = hostName;
    }

}
