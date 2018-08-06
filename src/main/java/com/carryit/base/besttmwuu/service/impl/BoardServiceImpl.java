package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.BoardDao;
import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;

    @Override
    public List<Board> getBoardByCid(Integer id) {
        return boardDao.getBoardByCid(id);
    }

    @Override
    public Board getBoardById(int bid) {
        return boardDao.getBoardById(bid);
    }
}
