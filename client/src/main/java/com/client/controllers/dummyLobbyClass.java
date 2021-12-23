package com.client.controllers;

public class dummyLobbyClass {
    public String gameName, hostName, somethnikToGame;
    public Integer playersInLobby;

    dummyLobbyClass(String gameName, Integer playersInLobby, String hostName, String somethnikToGame) {
        this.gameName = gameName;
        this.playersInLobby = playersInLobby;
        this.hostName = hostName;
        this.somethnikToGame = somethnikToGame;
    }

    public String getName() {
        return this.gameName;
    }

    public String hostName() {
        return this.hostName;
    }

    public String somethnikToGame() {
        return this.somethnikToGame;
    }

    public Integer getPlayersInLobby() {
        return this.playersInLobby;
    }
}
