package com.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

}
