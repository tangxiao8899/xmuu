package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class MemberLevel implements Serializable {
        private int uid;
        private int level;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
