package com.carryit.base.besttmwuu.entity;

public class ImsEweiShopSnsPostWithBLOBs extends imsEweiShopSnsPost {
    private String content;

    private String images;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }
}