package com.messages;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryMessage extends MessageHolder{
    private List<History> list = new ArrayList<>();

    public List<History> getList() {
        return list;
    }

    public void setList(List<History> list) {
        this.list = list;
    }
}
