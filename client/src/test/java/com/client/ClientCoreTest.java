package com.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Field;

import com.client.controllers.DashboardController;
import com.client.controllers.GameViewController;
import com.client.controllers.LobbyController;
import com.client.controllers.LoginIntoLauncherController;
import com.client.controllers.RegisteryController;
import com.client.controllers.StartViewController;

import org.junit.jupiter.api.Test;

public class ClientCoreTest {

    @Test
    public void instanceTest() {
        ClientCore c1 = ClientCore.getInstance();
        ClientCore c2 = ClientCore.getInstance();
        assertEquals(c1, c2);
        assertNotNull(c1);
    }

    // StartViewController - tests for setter and getter methods
    @Test
    public void setStartViewControllerTest() throws NoSuchFieldException, IllegalAccessException {
        StartViewController startViewController = new StartViewController();
        final ClientCore client = new ClientCore();
        client.setStartViewController(startViewController);
        final Field field = client.getClass().getDeclaredField("startViewController");
        field.setAccessible(true);
        assertEquals(startViewController, field.get(client), "Set poperly startViewController");
    }

    @Test
    public void getStartViewControllerTest() throws NoSuchFieldException, IllegalAccessException {
        StartViewController startViewController = new StartViewController();
        final ClientCore client = new ClientCore();
        final Field field = client.getClass().getDeclaredField("startViewController");
        field.setAccessible(true);
        field.set(client, startViewController);
        final StartViewController result = client.getStartViewController();
        assertEquals(startViewController, result, "Get poperly return startViewController");
    }

    // GameController - tests for setter and getter methods
    @Test
    public void setGameControllerTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        GameViewController gameViewController = new GameViewController();
        final ClientCore client = new ClientCore();
        client.setGameController(gameViewController);
        final Field field = client.getClass().getDeclaredField("gameController");
        field.setAccessible(true);
        assertEquals(gameViewController, field.get(client), "Set poperly gameViewController");
    }

    @Test
    public void getGameControllerTest() throws NoSuchFieldException, IllegalAccessException {
        GameViewController gameViewController = new GameViewController();
        final ClientCore client = new ClientCore();
        final Field field = client.getClass().getDeclaredField("gameController");
        field.setAccessible(true);
        field.set(client, gameViewController);
        final GameViewController result = client.getGameController();
        assertEquals(gameViewController, result, "Get poperly return gameViewController");
    }

    // LoginIntoLauncherController - tests for setter and getter methods
    @Test
    public void setLoginIntoLauncherController()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        LoginIntoLauncherController loginIntoLauncherController = new LoginIntoLauncherController();
        final ClientCore client = new ClientCore();
        client.setLoginIntoLauncherController(loginIntoLauncherController);
        final Field field = client.getClass().getDeclaredField("loginIntoLauncherController");
        field.setAccessible(true);
        assertEquals(loginIntoLauncherController, field.get(client), "Set poperly loginIntoLauncherController");
    }

    @Test
    public void getLoginIntoLauncherController() throws NoSuchFieldException, IllegalAccessException {
        LoginIntoLauncherController loginIntoLauncherController = new LoginIntoLauncherController();
        final ClientCore client = new ClientCore();
        final Field field = client.getClass().getDeclaredField("loginIntoLauncherController");
        field.setAccessible(true);
        field.set(client, loginIntoLauncherController);
        final LoginIntoLauncherController result = client.getLoginIntoLauncherController();
        assertEquals(loginIntoLauncherController, result, "Get poperly return loginIntoLauncherController");
    }

    // RegisterController - test for setter and getter methods
    @Test
    public void setRegisteryControllerTest() throws NoSuchFieldException, IllegalAccessException {
        RegisteryController controller = new RegisteryController();
        final ClientCore client = new ClientCore();
        client.setRegisteryController(controller);
        final Field field = client.getClass().getDeclaredField("registeryController");
        field.setAccessible(true);
        assertEquals(controller, field.get(client), "Set poperly RegisteryController");

    }

    @Test
    public void getRegisteryControllerTest() throws NoSuchFieldException, IllegalAccessException {
        RegisteryController controller = new RegisteryController();
        final ClientCore client = new ClientCore();
        final Field field = client.getClass().getDeclaredField("registeryController");
        field.setAccessible(true);
        field.set(client, controller);
        final RegisteryController result = client.getRegisteryController();
        assertEquals(controller, result, "Get poperly return RegisteryController");
    }

    // DashboardController - tests for setter and getter methods
    @Test
    public void setDashboardControllerTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        DashboardController controller = new DashboardController();
        final ClientCore client = new ClientCore();
        client.setDashboardController(controller);
        final Field field = client.getClass().getDeclaredField("dashboardController");
        field.setAccessible(true);
        assertEquals(controller, field.get(client), "Set property dashboardController ");
    }

    @Test
    public void getDashboardControllerTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        DashboardController controller = new DashboardController();
        final ClientCore client = new ClientCore();
        final Field field = client.getClass().getDeclaredField("dashboardController");
        field.setAccessible(true);
        field.set(client, controller);
        final DashboardController result = client.getDashboardController();
        assertEquals(controller, result, "Get property dashboardController ");
    }

    // LobbyController - tests for setter and getter methods
    @Test
    public void setLobbyControllerTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        LobbyController controller = new LobbyController();
        final ClientCore client = new ClientCore();
        client.setLobbyController(controller);
        final Field field = client.getClass().getDeclaredField("lobbyController");
        field.setAccessible(true);
        assertEquals(controller, field.get(client), "Set property lobbyController");
    }

    @Test
    public void getLobbyControllerTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        LobbyController controller = new LobbyController();
        final ClientCore client = new ClientCore();
        final Field field = client.getClass().getDeclaredField("lobbyController");
        field.setAccessible(true);
        field.set(client, controller);
        final LobbyController result = client.getLobbyController();
        assertEquals(controller, result, "Get property lobbyController");
    }

    // Login - tests for setter and getter methods
    @Test
    public void setLoginTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final ClientCore client = new ClientCore();
        client.setLogin("foo");
        final Field field = client.getClass().getDeclaredField("Login");
        field.setAccessible(true);
        assertEquals("foo", field.get(client));
    }

    @Test
    public void getLoginTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        final ClientCore client = new ClientCore();
        final Field field = client.getClass().getDeclaredField("Login");
        field.setAccessible(true);
        field.set(client, "testValue");
        final String result = client.getLogin();
        assertEquals("testValue", result);
    }

    // Avatar - tests for setter and getter methods
    @Test
    public void setAvatarTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        final ClientCore client = new ClientCore();
        client.setAvatar(5);
        final Field field = client.getClass().getDeclaredField("avatar");
        field.setAccessible(true);
        assertEquals(5, field.get(client));
    }

    @Test
    public void getAvatarTest()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        final ClientCore client = new ClientCore();
        final Field field = client.getClass().getDeclaredField("avatar");
        field.setAccessible(true);
        field.set(client, 5);
        final Integer result = client.getAvatar();
        assertEquals(5, result);
    }

}
