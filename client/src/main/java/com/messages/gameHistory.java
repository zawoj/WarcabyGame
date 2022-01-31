package com.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class gameHistory implements Serializable {
    public List<String> logins = new ArrayList<>();
    public List<Integer> moveX = new ArrayList<>();
    public List<Integer> moveY = new ArrayList<>();
    public List<Integer> pawnX = new ArrayList<>();
    public List<Integer> pawnY = new ArrayList<>();
    private long id;

    public gameHistory(List<String> logins, List<Integer> moveX, List<Integer> moveY, List<Integer> pawnX,
            List<Integer> pawnY, long id) {
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
