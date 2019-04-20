package com.carryit.base.besttmwuu.entity;

import java.io.Serializable;
import java.util.List;

public class Circles implements Serializable {

    private Integer id;
    private String name;
    private List<Board> boards;

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
        this.name = name;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }
}
