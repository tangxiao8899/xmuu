package com.carryit.base.besttmwuu.entity;

public class imsEweiShopDiypage {
    private Integer id;

    private Integer uniacid;

    private Boolean type;

    private String name;

    private Integer createtime;

    private Integer lastedittime;

    private String keyword;

    private Integer diymenu;

    private Integer merch;

    private Integer diyadv;

    private String data;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUniacid() {
        return uniacid;
    }

    public void setUniacid(Integer uniacid) {
        this.uniacid = uniacid;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Integer getLastedittime() {
        return lastedittime;
    }

    public void setLastedittime(Integer lastedittime) {
        this.lastedittime = lastedittime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public Integer getDiymenu() {
        return diymenu;
    }

    public void setDiymenu(Integer diymenu) {
        this.diymenu = diymenu;
    }

    public Integer getMerch() {
        return merch;
    }

    public void setMerch(Integer merch) {
        this.merch = merch;
    }

    public Integer getDiyadv() {
        return diyadv;
    }

    public void setDiyadv(Integer diyadv) {
        this.diyadv = diyadv;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }
}