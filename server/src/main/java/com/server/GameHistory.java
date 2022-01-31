package com.server;

import com.messages.History;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GameHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ElementCollection
    private List<String> logins = new ArrayList<>();
    @ElementCollection
    private List<Integer> moveX = new ArrayList<>();
    @ElementCollection
    private List<Integer> moveY = new ArrayList<>();
    @ElementCollection
    private List<Integer> pawnX = new ArrayList<>();
    @ElementCollection
    private List<Integer> pawnY = new ArrayList<>();

    public void setLogins(ArrayList<String> logins) {
        this.logins = logins;
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

    public List<Integer> getPawnY() {
        return this.pawnY;
    }

    public History pack(){
        return new History(new ArrayList<>(logins), new ArrayList<>(moveX), new ArrayList<>(moveY),
                new ArrayList<>(pawnX), new ArrayList<>(pawnY), id);
    }

}
