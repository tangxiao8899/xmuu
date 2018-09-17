package com.bean.resp;

import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.UserDTO;

import java.io.Serializable;
import java.util.List;

public class BoardResp implements Serializable {
    private List<UserDTO> userList;
    private List<Board> boardList;

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public List<Board> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<Board> boardList) {
        this.boardList = boardList;
    }
}
