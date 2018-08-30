package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class Level implements Serializable {

    private String level;
    private String levelname;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }
}
