package com.messages;

import java.util.LinkedList;

public class LobbyListMessage extends MessageHolder {
    private LinkedList<dummyLobbyClass> lobbys = new LinkedList<>();

    public LinkedList<dummyLobbyClass> getLobbys() {
        return lobbys;
    }

    public void setLobbys(LinkedList<dummyLobbyClass> lobbys) {
        this.lobbys = lobbys;
    }
}
