package com.client.helpersTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.MalformedURLException;
import java.net.URL;

import com.client.helpers.Routes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RoutesTest {

    @Test
    @DisplayName("Checking: Routes of Images")
    void testGetImageRoutes() {
        assertEquals("file:/G:/WarcabyGame/client/src/main/java/com/client/img/test.png",
                Routes.imageRoute("test.png"), "Cannot get image");

    }

    @Test
    @DisplayName("Checking: Throw exception of get routes image routes when string is null")
    void testThrowExceptionOfGetImageRoutes() {
        Throwable exception = assertThrows(NullPointerException.class, () -> Routes.imageRoute(null));
        assertEquals("Cannot find image when route is empty string", exception.getMessage());
    }

    @Test
    @DisplayName("Checking: Routes of Images")
    void testGetStyleRoutes() throws MalformedURLException {
        assertEquals("file:/G:/WarcabyGame/client/src/main/java/com/client/styles/test.css",
                Routes.styleRoute("test.css"), "Cannot get styles route");
    }

    @Test
    @DisplayName("Checking: Throw exception of get style routes when string is null")
    void testThrowExceptionOfGetStyleRoutes() {
        Throwable exception = assertThrows(NullPointerException.class, () -> Routes.styleRoute(null));
        assertEquals("Cannot find styleFileName with empty string", exception.getMessage());
    }

    @Test
    @DisplayName("Checking: Routes of Views")
    void testGetViewRoutes() throws MalformedURLException {
        assertEquals(new URL("file:/G:/WarcabyGame/client/src/main/java/com/client/views/test.fxml"),
                Routes.viewsRoute("test.fxml"), "Cannot get styles route");

    }

    @Test
    @DisplayName("Checking: Throw exception of get views routes when string is null")
    void testThrowExceptionOfGetViewRoutes() {
        Throwable exception = assertThrows(NullPointerException.class, () -> Routes.viewsRoute(null));
        assertEquals("Cannot find viewName with empty string", exception.getMessage());
    }

}
