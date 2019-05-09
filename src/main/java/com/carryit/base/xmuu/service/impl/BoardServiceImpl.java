package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.BoardDao;
import com.carryit.base.xmuu.entity.Board;
import com.carryit.base.xmuu.service.BoardFollowService;
import com.carryit.base.xmuu.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;

    @Autowired
    BoardFollowService boardFollowService;

    @Override
    public List<Board> getBoardByCid(Integer id) {
        return boardDao.getBoardByCid(id);
    }

    @Override
    public Board getBoardById(int bid) {
        return boardDao.getBoardById(bid);
    }

    @Override
    public List<Board> getAllBoard() {

        List<Board> list = boardDao.getAllBoard();

        for(Board b : list){
            int bid = b.getId();
            long topic = boardFollowService.getTopicCount(bid);
            long concerns = this.boardFollowService.getFollowCount(bid);

            b.setTopic((int)topic);
            b.setConcerns((int)concerns);
        }
        return list;
    }
}
