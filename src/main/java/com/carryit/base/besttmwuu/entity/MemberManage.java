package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.util.List;

public class MemberManage implements Serializable {

    private Board board;
    private List<Member> adminMember;
    private List<Member> normalMember;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Member> getAdminMember() {
        return adminMember;
    }

    public void setAdminMember(List<Member> adminMember) {
        this.adminMember = adminMember;
    }

    public List<Member> getNormalMember() {
        return normalMember;
    }

    public void setNormalMember(List<Member> normalMember) {
        this.normalMember = normalMember;
    }
}
