package com.client.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

public class FieldTest {
    Field instance;

    @BeforeEach
    public void init() {
        instance = new Field(10.0, 10.0, 15.0, Color.WHITE, 10, 10);
    }

    @Test
    void testGetHeight() {
        assertEquals(10, instance.getHeight());
    }

    @Test
    void testGetWidth() {
        assertEquals(10, instance.getWidth());
    }

    @Test
    void testIsPolyline() {
        assertEquals(true, instance instanceof Polyline);
    }
}
