package com.server;

import com.messages.LobbyInfoMessage;
import com.messages.MessageHolder;

import java.util.LinkedList;

public class Lobby {
    private String host, name;
    private int numberOfPlayers;
    private LinkedList<UserCommunicationThread> players;

    public Lobby(){
        numberOfPlayers = 0;
        players = new LinkedList<>();
    }

    public void addPlayer(UserCommunicationThread newPlayer){
        if(numberOfPlayers == 6) return;
        if(numberOfPlayers == 0){
            host = newPlayer.userData.getLogin();
            name = host + "'s game";
        }
        numberOfPlayers++;
        players.add(newPlayer);
    }

    public void removePlayer(UserCommunicationThread playerToRemove){
        if(!players.remove(playerToRemove)){
            return;
        }else{
            numberOfPlayers--;
            if(numberOfPlayers==0){
                ServerCore.getInstance().getLobbys().remove(this);
            }
        }
    }

    public void start(){

    }

    private LobbyInfoMessage getLobbyInfo(){
        LobbyInfoMessage info = new LobbyInfoMessage();
        info.setMessageType("LobbyInfo");
        for(UserCommunicationThread uct : players){
            info.getPlayerinfo().add(uct.userData.getLogin());
        }
        return info;
    }
    public void sendLobbyInfo(){
        deliverMessages(getLobbyInfo());
    }

    public void deliverMessages(MessageHolder mh){
        try {
            for (UserCommunicationThread uct : players) {
                uct.out.writeObject(mh);
                ServerCore.getInstance().getController().appendOutput(mh.getMessageType());
            }
        }catch(Exception ignored){}
    }
    public String getHost() {
        return host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPlayers(){
        return numberOfPlayers;
    }
}
