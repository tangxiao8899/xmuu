package com.carryit.base.besttmwuu.entity;

public class imsEweiShopSnsCategory {
    private Integer id;

    private Integer uniacid;

    private String name;

    private String thumb;

    private Byte displayorder;

    private Boolean enabled;

    private String advimg;

    private String advurl;

    private Byte isrecommand;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public Byte getDisplayorder() {
        return displayorder;
    }

    public void setDisplayorder(Byte displayorder) {
        this.displayorder = displayorder;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getAdvimg() {
        return advimg;
    }

    public void setAdvimg(String advimg) {
        this.advimg = advimg == null ? null : advimg.trim();
    }

    public String getAdvurl() {
        return advurl;
    }

    public void setAdvurl(String advurl) {
        this.advurl = advurl == null ? null : advurl.trim();
    }

    public Byte getIsrecommand() {
        return isrecommand;
    }

    public void setIsrecommand(Byte isrecommand) {
        this.isrecommand = isrecommand;
    }
}