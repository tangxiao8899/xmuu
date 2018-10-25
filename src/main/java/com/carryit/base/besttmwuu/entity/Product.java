package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

/**
 * 产品表
 */
public class Product implements Serializable {

    private int id; //主键ID
    private String level; //商品等级 0：UU圈主  1：高级VIP 6:副圈主 7：管理员 9：梦想VIP
    private String levelName;// 商品名称
    private long price; // 价格

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
