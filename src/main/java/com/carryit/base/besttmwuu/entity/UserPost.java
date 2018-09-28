package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;

public class UserPost implements Serializable {
    private Integer id;

    private Integer uniacid;

    private Integer bid;

    private Integer pid;

    private Integer rpid;

    private String avatar;//头像

    private String content;//动态文字

    private String image;//动态图片

    private String nickname;//评论人昵称

    private String title;//内容title

    private Integer createtime;//创建时间

    private Integer replytime;//回复时间

    private Integer credit;

    private Integer views;//意见

    private Byte deleted;

    private Integer deletedtime;//删除时间

    private Integer checktime;//检查时间

    private Integer fabulous;//点赞

    public Integer getFabulous() {
        return fabulous;
    }

    public void setFabulous(Integer fabulous) {
        this.fabulous = fabulous;
    }

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

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRpid() {
        return rpid;
    }

    public void setRpid(Integer rpid) {
        this.rpid = rpid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }

    public Integer getReplytime() {
        return replytime;
    }

    public void setReplytime(Integer replytime) {
        this.replytime = replytime;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    public Integer getDeletedtime() {
        return deletedtime;
    }

    public void setDeletedtime(Integer deletedtime) {
        this.deletedtime = deletedtime;
    }

    public Integer getChecktime() {
        return checktime;
    }

    public void setChecktime(Integer checktime) {
        this.checktime = checktime;
    }
}
