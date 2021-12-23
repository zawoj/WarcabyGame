package com.messages;

import java.io.Serializable;

public class dummyLobbyClass implements Serializable {
    public String gameName, hostName;
    public Integer playersInLobby;

    public dummyLobbyClass(String gameName, Integer playersInLobby, String hostName) {
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

