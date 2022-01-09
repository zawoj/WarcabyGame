package com.lab1;

import com.Database.UserInformationPackage;
import com.messages.dummyLobbyClass;
import com.server.Lobby;
import com.server.ServerCore;
import com.server.UserCommunicationThread;
import org.junit.jupiter.api.Test;
import com.controllers.TerminalController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class ServerCoreTest {
    @Test
    public void instanceTest() {
        ServerCore s1 = ServerCore.getInstance();
        ServerCore s2 = ServerCore.getInstance();
        assertEquals(s1, s2);
        assertNotNull(s1);
    }

    private static class dummyControler extends TerminalController {
        public String append, appendin, appendout;

        @Override
        public void append(String text) {
            append = text;
        }
    }

    @Test
    public void commandTest() {
        ServerCore.getInstance().setController(new dummyControler());
        ServerCore.getInstance().command("echo test1234");
        assertEquals(" test1234", ((dummyControler) ServerCore.getInstance().getController()).append);
    }

    @Test
    public void lobbyTest() {
        Lobby test = new Lobby();
        UserCommunicationThread u = new UserCommunicationThread(new Socket());
        u.userData = new UserInformationPackage("testLogin", "testPassword", 1);
        test.addPlayer(u);
        ServerCore.getInstance().getLobbys().add(test);
        Lobby test1 = ServerCore.getInstance().getLobbybyHost("testLogin");
        assertEquals(test, test1);
        LinkedList<dummyLobbyClass> d = ServerCore.getInstance().getLobbysInfo();
        assertEquals(d.get(0).hostName, "testLogin");

    }

    private static class dummySocket extends ServerSocket {
        public boolean amIactive = true;

        public dummySocket() throws IOException {
        }

        @Override
        public void close() throws IOException {
            amIactive = false;
        }
    }

    @Test
    public void closeTest() throws IOException {
        dummyControler dc = new dummyControler();
        ServerCore.getInstance().setController(dc);
        ServerCore.getInstance().ServerCoreSetup();
        dummySocket ds = new dummySocket();
        ServerCore.getInstance().serverSocket = ds;
        ServerCore.getInstance().close(true);
        assertEquals(dc.append, "server closed");
        assertNotNull(ServerCore.getInstance().getDataBaseManager());
        assertNotNull(ServerCore.getInstance().getUsers());
        assertFalse(ds.amIactive);
    }

    @Test
    public void startTest() {
        dummyControler dc = new dummyControler();
        ServerCore.getInstance().setController(dc);
        ServerCore.getInstance().ServerCoreSetup();
        ServerCore.getInstance().command("start 4444");
        assertEquals(dc.append, "started server at port 4444");
    }
}
