package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.Board;

import java.util.List;

public interface BoardService {

    List<Board> getBoardByCid(Integer id);

    Board getBoardById(int bid);

    List<Board> getAllBoard();
}
