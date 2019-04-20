package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class MemberLevel implements Serializable {
        private int uid;
        private  String level;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
