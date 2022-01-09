package com.lab1;

import com.Database.UserInformationPackage;
import com.controllers.TerminalController;
import com.messages.MessageHolder;
import com.messages.MoveMessage;
import com.server.Game;
import com.server.Lobby;
import com.server.ServerCore;
import com.server.UserCommunicationThread;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GameTests {

    private class DummyUCT extends UserCommunicationThread{

        /**
         * creates new user thread
         *
         * @param clientSocket users socket
         */
        public DummyUCT(Socket clientSocket) {
            super(clientSocket);
        }

        @Override
        public void setInOut() throws IOException {
            this.out = new ObjectOutputStream(OutputStream.nullOutputStream());
            this.in = mock(ObjectInputStream.class);
        }
    }
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
    private static class dummyLobby extends Lobby{
        public MessageHolder message;
        public dummyLobby(){
            super();
        }
        @Override
        public void deliverMessages(MessageHolder mh) {
            message = mh;
        }
    }
    @Test
    public void gameTest() throws Exception {
        dummyControler dc = new dummyControler();
        ServerCore.getInstance().setController(dc);
        dummyLobby lobby = new dummyLobby();
        DummyUCT p1 = new DummyUCT(new Socket());
        DummyUCT p2 = new DummyUCT(new Socket());
        p1.userData = new UserInformationPackage("Login1", "pass1", 1);
        p2.userData = new UserInformationPackage("Login2", "pass2", 2);
        p1.setInOut();
        p2.setInOut();
        lobby.addPlayer(p1);
        p1.setLobby(lobby);
        lobby.addPlayer(p2);
        p2.setLobby(lobby);
        MessageHolder mh = new MessageHolder();
        mh.setMessageType("StartGame");
        p1.inputObjectHandling(mh);
        mh.setMessageType("skip turn");
        p1.inputObjectHandling(mh);
        mh.setMessageType("ready");
        p1.inputObjectHandling(mh);
        p2.inputObjectHandling(mh);
        Assertions.assertEquals(lobby.message.getMessageType(), "turn");
        lobby.message.setMessageType("Zero");
        Assertions.assertNotNull(lobby.game);
        MoveMessage moveMessage = new MoveMessage();
        moveMessage.setAll(1,1,2,2);
        moveMessage.setMessageType("move");
        p1.inputObjectHandling(moveMessage);
        Assertions.assertEquals("invalid move",dc.appendout );
        lobby.game.move(9,3,10,4);
        Assertions.assertEquals(lobby.message.getMessageType(), "turn");
        lobby.game.endGame();
        Assertions.assertEquals(lobby.message.getMessageType(), "game ended");

    }
}
