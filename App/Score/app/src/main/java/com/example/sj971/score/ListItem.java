package com.example.sj971.score;

import java.util.List;

/**
 * Created by sj971 on 2018-05-30.
 */

public class ListItem {
    String number;
    String name;

    public ListItem(){

    }

    public ListItem(String number, String name) {
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

