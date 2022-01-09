package com.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;

import com.client.controllers.StartViewController;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClientCoreTest {
    ClientCore clientCore;

    @Test
    public void instanceTest() {
        ClientCore c1 = ClientCore.getInstance();
        ClientCore c2 = ClientCore.getInstance();
        assertEquals(c1, c2);
        assertNotNull(c1);
    }

    @BeforeEach
    public void setUp() {
        clientCore = new ClientCore();
    }

    // StartViewController tests
    @Test
    public void setStartViewControllerTest() throws NoSuchFieldException, IllegalAccessException {

        clientCore.setStartViewController(new StartViewController());

        final Field field = clientCore.getClass().getDeclaredField("startViewController");
        field.setAccessible(true);
        assertEquals(true, field.get(clientCore) instanceof StartViewController);
    }

    @Test
    public void getStartViewControllerTest() throws NoSuchFieldException, IllegalAccessException {
        // given

        final Field field = clientCore.getClass().getDeclaredField("startViewController");
        field.setAccessible(true);
        field.set(clientCore, "magic_values");

        // when
        final StartViewController result = (StartViewController) clientCore.getStartViewController();

        // then
        assertEquals("field wasn't retrieved properly", result, "magic_values");
    }
}
