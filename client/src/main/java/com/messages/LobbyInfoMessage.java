package com.messages;

import java.util.LinkedList;

public class LobbyInfoMessage extends MessageHolder{
    LinkedList<String> playerinfo;
    public LobbyInfoMessage(){
        playerinfo = new LinkedList<>();
    }

    public LinkedList<String> getPlayerinfo() {
        return playerinfo;
    }

}
