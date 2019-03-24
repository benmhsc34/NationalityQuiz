package com.internationalknowledgequiz.benja.international.Utils;

public class ListStatsItems {
    private String flag;
    private String country;
    private String stats;

    public ListStatsItems(String flag, String country, String stats) {
        this.flag = flag;
        this.country = country;
        this.stats = stats;
    }

    public String getFlag() {
        return flag;
    }

    public String getCountry() {
        return country;
    }

    public String getStats() {
        return stats;
    }


}
