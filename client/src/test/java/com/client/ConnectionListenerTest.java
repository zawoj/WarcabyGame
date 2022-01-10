package com.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;

import com.client.controllers.LoginIntoLauncherController;
import com.client.controllers.RegisteryController;
import com.messages.MessageHolder;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ConnectionListenerTest {

    @Test
    public void testConnectionListenerRegisteredFailed() throws Exception {
        RegisteryController controller = Mockito.mock(RegisteryController.class);
        ClientCore.getInstance().setRegisteryController(controller);
        ConnectionListener testConnectionListnere = new ConnectionListener();
        MessageHolder msgHolder = new MessageHolder();

        msgHolder.setMessageType("Register failed");
        testConnectionListnere.messageHandler(msgHolder);
        Mockito.verify(controller).errorNotification();

        ObjectInputStream in = Mockito.mock(ObjectInputStream.class);
        ObjectOutputStream out = Mockito.mock(ObjectOutputStream.class);

        testConnectionListnere.in = in;
        testConnectionListnere.out = out;

        testConnectionListnere.close();
        Mockito.verify(in).close();
        Mockito.verify(out).close();

    }

    @Test
    public void testConnectionListenerRegistered() throws Exception {
        RegisteryController controller = Mockito.mock(RegisteryController.class);
        ClientCore.getInstance().setRegisteryController(controller);
        ConnectionListener testConnectionListnere = new ConnectionListener();
        MessageHolder msgHolder = new MessageHolder();

        msgHolder.setMessageType("Registered");
        testConnectionListnere.messageHandler(msgHolder);
        Mockito.verify(controller).accountCreatedSuccesfullyNotification();

        ObjectInputStream in = Mockito.mock(ObjectInputStream.class);
        ObjectOutputStream out = Mockito.mock(ObjectOutputStream.class);

        testConnectionListnere.in = in;
        testConnectionListnere.out = out;

        testConnectionListnere.close();
        Mockito.verify(in).close();
        Mockito.verify(out).close();

    }

    @Test
    public void testConnectionListenerLoginFaild() throws Exception {
        LoginIntoLauncherController controller = Mockito.mock(LoginIntoLauncherController.class);
        ClientCore.getInstance().setLoginIntoLauncherController(controller);
        ConnectionListener testConnectionListnere = new ConnectionListener();
        MessageHolder msgHolder = new MessageHolder();

        msgHolder.setMessageType("Login fail");
        testConnectionListnere.messageHandler(msgHolder);
        Mockito.verify(controller).ErrorNotification();

        ObjectInputStream in = Mockito.mock(ObjectInputStream.class);
        ObjectOutputStream out = Mockito.mock(ObjectOutputStream.class);

        testConnectionListnere.in = in;
        testConnectionListnere.out = out;

        testConnectionListnere.close();
        Mockito.verify(in).close();
        Mockito.verify(out).close();

    }

    @Test
    public void getOutTest() throws Exception {
        ObjectOutputStream out = Mockito.mock(ObjectOutputStream.class);
        final ConnectionListener testConnectionListnere = new ConnectionListener();
        final Field field = testConnectionListnere.getClass().getDeclaredField("out");
        field.setAccessible(true);
        field.set(testConnectionListnere, out);
        final ObjectOutputStream result = testConnectionListnere.getOut();
        assertEquals(out, result, "Get poperly return startViewController");
    }

}
