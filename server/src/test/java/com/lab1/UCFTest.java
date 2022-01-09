package com.lab1;
import com.Database.UserDataBase;
import com.controllers.TerminalController;
import com.messages.LoginMessage;
import com.messages.MessageHolder;
import com.messages.RegisterMessage;
import com.messages.joinLobbyMessage;
import com.server.DataBaseManager;
import com.server.ServerCore;
import com.server.UserCommunicationThread;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


public class UCFTest {
    private static class dummyControler extends TerminalController {
        public String append, appendin, appendout;
        @Override
        public void append(String text) {
            append = text;
        }

        @Override
        public void appendInput(String text) {
            appendin = text;
        }

        @Override
        public void appendOutput(String text) {
            appendout = text;
        }
    }
    @Test
    public void test() throws IOException {
        Socket cl = Mockito.mock(Socket.class);
        Mockito.when(cl.getOutputStream()).thenReturn(OutputStream.nullOutputStream());
        Mockito.when(cl.getInputStream()).thenReturn(null);
        UserCommunicationThread ucf = new UserCommunicationThread(cl);
        try{
            ucf.setInOut();
        }catch(Exception ignored){}
        assertNotNull(ucf.out);
        dummyControler dc = new dummyControler();
        ServerCore.getInstance().setController(dc);
        RegisterMessage m = new RegisterMessage();
        m.setMessageType("register");
        m.setLogin("tl");
        m.setPassword("tp");
        m.setAvatar(1);
        ServerCore.getInstance().ServerCoreSetup();
        ucf.inputObjectHandling(m);
        assertTrue(ServerCore.getInstance().getDataBaseManager().checkIfUserInDatabase("tl"));
        LoginMessage lm = new LoginMessage();
        lm.setMessageType("login");
        lm.setPassword("tp");
        lm.setLogin("tl");
        ucf.inputObjectHandling(lm);
        assertEquals(ucf.userData.getLogin(), "tl");
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("get lobby info");
        ucf.inputObjectHandling(mh);
        assertEquals("lobby list info", dc.appendout);
        mh.setMessageType("Create Lobby");
        ucf.inputObjectHandling(mh);
        assertNotNull(ServerCore.getInstance().getLobbybyHost("tl"));
        joinLobbyMessage jlm = new joinLobbyMessage();
        jlm.setMessageType("change name");
        jlm.setHostName("TESTNAME");
        ucf.inputObjectHandling(jlm);
        assertEquals("TESTNAME", ucf.myLobby.getName());
        assertNotNull(ucf.myLobby);
        mh.setMessageType("exit lobby");
        ucf.inputObjectHandling(mh);
        assertNull(ucf.myLobby);
        jlm.setMessageType("join lobby");
        jlm.setHostName("TEST");
        ucf.inputObjectHandling(jlm);
        assertNull(ucf.myLobby);
        ucf.in = Mockito.mock(ObjectInputStream.class);
        ucf.out = Mockito.mock(ObjectOutputStream.class);
        ucf.close();
        Mockito.verify(ucf.in).close();
        Mockito.verify(ucf.out).close();
        Mockito.verify(cl).close();
        InetAddress iam = Mockito.mock(InetAddress.class);
        Mockito.when(cl.getInetAddress()).thenReturn(iam);
        Mockito.when(iam.getHostAddress()).thenReturn("TEST");
        ucf.run();
        assertEquals("failed to connect clientTEST", dc.append);
    }
}
