package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Board;

import java.util.List;

public interface BoardService {

    List<Board> getBoardByCid(Integer id);

    Board getBoardById(int bid);
}
