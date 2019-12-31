package com.maxst.mediaapp;

public class Images {
    private String name;
    private int thumbnail;

    public Images(String name, int thumbnail) {
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThubmnail() {
        return thumbnail;
    }

    public void setThubmnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
