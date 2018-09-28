package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.BoardFollowDao;
import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.BoardFollow;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.service.BoardFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardFollowService")
public class BoardFollowServiceImpl implements BoardFollowService {

    @Autowired
    private BoardFollowDao boardFollowDao;
    @Override
    public BoardFollow getBoardByUid(int uid, int bid) {
        return boardFollowDao.getBoardByUid(uid, bid);
    }

    @Override
    public void delete(int uid, int bid) {
        boardFollowDao.delete(uid,bid);
    }

    @Override
    public void add(int uid, int bid, String time) {
        boardFollowDao.add(uid,bid,time);
    }

    @Override
    public List<Member> getMemberByZhuQuanZiId(Integer zhuquanzi) {
        return boardFollowDao.getMemberByZhuQuanZiId(zhuquanzi);
    }

    @Override
    public List<Board> getBoardFollowByUId(int uid) {
        return boardFollowDao.getBoardFollowByUId(uid);
    }

    @Override
    public List<Board> getUnconcerned(int uid) {
        return boardFollowDao.getUnconcerned(uid);
    }
}
