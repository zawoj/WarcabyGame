package com.client.game;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

public class Field extends Polyline {
    private int height;
    private int width;

    public Field(double centerX, double centerY, double radius, Paint fill, int height, int width) {
        super(
                centerX, centerY,
                centerX + radius, centerY,
                centerX + radius * (3.0 / 2.0), centerY + radius * (Math.sqrt(3) / 2.0),
                centerX + radius, centerY + radius * Math.sqrt(3),
                centerX, centerY + radius * Math.sqrt(3),
                centerX - (radius / 2.0), centerY + radius * (Math.sqrt(3) / 2.0),
                centerX, centerY);
        this.setFill(fill);
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
