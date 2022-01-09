package com.lab1;

import com.Database.UserInformationPackage;
import com.server.Lobby;
import com.server.UserCommunicationThread;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import java.net.Socket;

public class LobbyTest {
    @Test
    public void lobbyAddRemNameTest(){
        Lobby lob = new Lobby();
        UserCommunicationThread u = new UserCommunicationThread(new Socket());
        u.userData = new UserInformationPackage("testLogin", "testPassword", 1);
        lob.addPlayer(u);
        UserCommunicationThread u2 = new UserCommunicationThread(new Socket());
        u2.userData = new UserInformationPackage("testLogin2", "testPassword2", 2);
        lob.addPlayer(u2);
        lob.removePlayer(u);
        Assertions.assertEquals(lob.getName(), "testLogin's game");
        lob.setName("TTT");
        Assertions.assertEquals(lob.getName(), "TTT");
        Assertions.assertEquals(lob.getHost(), u2.userData.getLogin());
    }
}
