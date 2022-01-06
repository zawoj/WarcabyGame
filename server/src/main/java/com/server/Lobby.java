package com.server;

import com.messages.LobbyInfoMessage;
import com.messages.MessageHolder;

import java.util.LinkedList;

/**
 * lobby
 */
public class Lobby {
    private String host, name;
    private int numberOfPlayers;
    private final LinkedList<UserCommunicationThread> players;
    Game game;

    /**
     * creates a new lobby
     */
    public Lobby() {
        numberOfPlayers = 0;
        players = new LinkedList<>();
    }

    /**
     * adds a player to lobby
     * @param newPlayer player to add
     */
    public void addPlayer(UserCommunicationThread newPlayer) {
        if (numberOfPlayers == 6)
            return;
        if (numberOfPlayers == 0) {
            host = newPlayer.userData.getLogin();
            name = host + "'s game";
        }
        numberOfPlayers++;
        players.add(newPlayer);
        newPlayer.setLobby(this);
        sendLobbyInfo();
    }

    /**
     * removes a player from lobby
     * @param playerToRemove player to remove
     */
    public void removePlayer(UserCommunicationThread playerToRemove) {
        if (!players.remove(playerToRemove))
            return;
        numberOfPlayers--;
        if (numberOfPlayers == 0) {
            ServerCore.getInstance().getLobbys().remove(this);
        } else {
            if (playerToRemove.userData.getLogin().equals(host)) {
                host = players.get(0).userData.getLogin();
            }
        }

        sendLobbyInfo();
    }

    /**
     * starts a game
     */
    public void start() {
        try {
            game = new Game(this, numberOfPlayers);
            ServerCore.getInstance().getLobbys().remove(this);
        } catch (Exception ignored) {
        }
    }

    private LobbyInfoMessage getLobbyInfo() {
        LobbyInfoMessage info = new LobbyInfoMessage();
        info.setMessageType("LobbyInfo");
        for (UserCommunicationThread uct : players) {
            info.getPlayernames().add(uct.userData.getLogin());
            info.getPlayerimages().add(uct.userData.getAvatarNbr());
        }
        info.setGameName(name);
        return info;
    }

    /**
     * sends lobby info to all players in it
     */
    public void sendLobbyInfo() {
        deliverMessages(getLobbyInfo());
    }

    /**
     * delivers message to all players in lobby
     * @param mh message to send
     */
    public void deliverMessages(MessageHolder mh) {
        for (UserCommunicationThread uct : players) {
            try {
                uct.out.writeObject(mh);
                ServerCore.getInstance().getController().appendOutput(mh.getMessageType());
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * return hosts login
     * @return hosts login
     */
    public String getHost() {
        return host;
    }

    /**
     * returns lobbys name
     * @return  lobbys name
     */
    public String getName() {
        return name;
    }

    /**
     * sets lobbys name
     * @param name new name
     */
    public void setName(String name) {
        if (!name.equals("")) {
            this.name = name;
        } else {
            this.name = host + "'s game";
        }
    }

    /**
     * returns number of players int the lobby
     * @return number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * returns players list
     * @return players list
     */
    public LinkedList<UserCommunicationThread> getPlayers() {
        return players;
    }
}
