package com.pd.server.main.dto;

import java.util.List;

public class NameAndDataRes {

    private String[] categories;

    private List<NameAndData> series;

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public List<NameAndData> getSeries() {
        return series;
    }

    public void setSeries(List<NameAndData> series) {
        this.series = series;
    }
}
