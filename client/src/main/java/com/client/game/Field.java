package com.client.game;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Field extends Circle {
    private int height;
    private int width;

    public Field(double centerX, double centerY, double radius, Paint fill, int height, int width) {
        super(centerX, centerY, radius, fill);
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
