package com.internationalknowledge.benja.international.Utils;

public class ListItem {

    private int flag;
    private String name;
    private int yens;

    public ListItem(int flag, String name, int yens) {
        this.flag = flag;
        this.name = name;
        this.yens = yens;
    }

    public int getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public int getYens() {
        return yens;
    }
}
