package com.example.benja.nationalityquiz.Utils;

public class ListItem {

    private int flag;
    private String name;
    private String stats;

    public ListItem(int flag, String name, String stats) {
        this.flag = flag;
        this.name = name;
        this.stats = stats;
    }

    public int getFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public String getStats() { return stats; }
}
