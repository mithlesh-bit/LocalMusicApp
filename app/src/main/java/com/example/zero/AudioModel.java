package com.example.zero;

import java.io.Serializable;

public class AudioModel implements Serializable {
    String path;
    String title;
    String duaration;

    public AudioModel(String path, String title, String duaration) {
        this.path = path;
        this.title = title;
        this.duaration = duaration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuaration() {
        return duaration;
    }

    public void setDuaration(String duaration) {
        this.duaration = duaration;
    }
}
