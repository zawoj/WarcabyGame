package com.client.game;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polyline;
import javafx.scene.transform.Rotate;

/**
 * Class which create single field of game board
 */
public class Field extends Polyline {
    private int height;
    private int width;

    /**
     * Constructor of single field
     * 
     * @param centerX field x position
     * @param centerY field y position
     * @param radius  field radius
     * @param fill    fill field color
     * @param height  height of field
     * @param width   width of field
     */
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
        this.setStroke(Color.BLACK);
        Rotate rotation = new Rotate(30, centerX, centerY);
        this.getTransforms().add(rotation);
        this.height = height;
        this.width = width;

    }

    /**
     * Getter of the height of the field
     * 
     * @return int height of the field
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter of the width of the field
     * 
     * @return int width of the field
     */
    public int getWidth() {
        return width;
    }

}
