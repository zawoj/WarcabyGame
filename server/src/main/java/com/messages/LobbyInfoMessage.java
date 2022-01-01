package com.messages;

import java.util.LinkedList;

public class LobbyInfoMessage extends MessageHolder{
    LinkedList<String> playernames;
    LinkedList<Integer> playerimages;
    String GameName;
    public LobbyInfoMessage(){
        playernames = new LinkedList<>();
        playerimages = new LinkedList<>();
    }

    public LinkedList<String> getPlayernames() {
        return playernames;
    }
    public LinkedList<Integer> getPlayerimages(){return playerimages;}
    public String getGameName() {
        return GameName;
    }

    public void setGameName(String gameName) {
        GameName = gameName;
    }
}
