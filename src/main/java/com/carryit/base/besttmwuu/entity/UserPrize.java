package com.carryit.base.besttmwuu.entity;

public class UserPrize {

    private Integer id;
    private String uid;
    private Integer pid;
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "UserPrize{" +
                "id=" + id +
                ", uid=" + uid +
                ", pid=" + pid +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
