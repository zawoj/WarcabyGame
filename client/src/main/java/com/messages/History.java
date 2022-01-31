package com.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class History implements Serializable {
    public ArrayList<String> logins = new ArrayList<>();
    public ArrayList<Integer> moveX = new ArrayList<>();
    public ArrayList<Integer> moveY = new ArrayList<>();
    public ArrayList<Integer> pawnX = new ArrayList<>();
    public ArrayList<Integer> pawnY = new ArrayList<>();
    private long id;

    public History(ArrayList<String> logins, ArrayList<Integer> moveX, ArrayList<Integer> moveY, ArrayList<Integer> pawnX,
                   ArrayList<Integer> pawnY, long id) {
        this.logins = logins;
        this.moveX = moveX;
        this.moveY = moveY;
        this.pawnX = pawnX;
        this.pawnY = pawnY;

    }

    public List<String> getLogins() {
        return this.logins;
    }

    public long getId() {
        return this.id;
    }

    public List<Integer> getMoveX() {
        return this.moveX;
    }

    public List<Integer> getMoveY() {
        return this.moveY;
    }

    public List<Integer> getPawnX() {
        return this.pawnX;
    }

    public List<Integer> getpawnY() {
        return this.pawnY;
    }

}
