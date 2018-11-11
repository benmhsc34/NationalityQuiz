package com.internationalknowledge.benja.international.Utils;

public class ListStatsItems {
    private  int flag;
    private String country;
    private  String stats;

    public ListStatsItems(int flag, String country, String stats) {
        this.flag = flag;
        this.country = country;
        this.stats = stats;
    }

    public  int getFlag() {
        return flag;
    }

    public  String getCountry() {
        return country;
    }

    public  String getStats() {
        return stats;
    }


}
