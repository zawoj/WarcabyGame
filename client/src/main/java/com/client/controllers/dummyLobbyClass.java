package com.client.controllers;

public class dummyLobbyClass {
    public String gameName, hostName;
    public Integer playersInLobby;

    dummyLobbyClass(String gameName, Integer playersInLobby, String hostName) {
        this.gameName = gameName;
        this.playersInLobby = playersInLobby;
        this.hostName = hostName;
    }

    public String getName() {
        return this.gameName;
    }

    public String hostName() {
        return this.hostName;
    }

    public Integer getPlayersInLobby() {
        return this.playersInLobby;
    }
}
