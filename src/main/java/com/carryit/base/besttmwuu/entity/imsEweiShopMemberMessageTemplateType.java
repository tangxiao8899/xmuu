package com.carryit.base.besttmwuu.entity;

public class imsEweiShopMemberMessageTemplateType {
    private Integer id;

    private String name;

    private String typecode;

    private String templatecode;

    private String templateid;

    private String templatename;

    private String content;

    private Boolean showtotaladd;

    private String typegroup;

    private String groupname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTypecode() {
        return typecode;
    }

    public void setTypecode(String typecode) {
        this.typecode = typecode == null ? null : typecode.trim();
    }

    public String getTemplatecode() {
        return templatecode;
    }

    public void setTemplatecode(String templatecode) {
        this.templatecode = templatecode == null ? null : templatecode.trim();
    }

    public String getTemplateid() {
        return templateid;
    }

    public void setTemplateid(String templateid) {
        this.templateid = templateid == null ? null : templateid.trim();
    }

    public String getTemplatename() {
        return templatename;
    }

    public void setTemplatename(String templatename) {
        this.templatename = templatename == null ? null : templatename.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getShowtotaladd() {
        return showtotaladd;
    }

    public void setShowtotaladd(Boolean showtotaladd) {
        this.showtotaladd = showtotaladd;
    }

    public String getTypegroup() {
        return typegroup;
    }

    public void setTypegroup(String typegroup) {
        this.typegroup = typegroup == null ? null : typegroup.trim();
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }
}