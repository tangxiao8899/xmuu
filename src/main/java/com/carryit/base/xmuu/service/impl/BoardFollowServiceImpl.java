package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.BoardFollowDao;
import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.BoardFollow;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.Post;
import com.carryit.base.besttmwuu.service.BoardFollowService;
import org.apache.ibatis.session.RowBounds;
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

    @Override
    public long getTopicCount(int bid) {
        return boardFollowDao.getTopicCount(bid);
    }

    @Override
    public long getFollowCount(int bid) {
        return boardFollowDao.getFollowCount(bid);
    }

    @Override
    public List<Post> getAllBoardTopic(int bid, int pageStart, int pageSize) {
        return boardFollowDao.getAllBoardTopic(bid,new RowBounds(pageStart,pageSize));
    }

    @Override
    public long getAllBoardTopicCount(int bid) {
        return boardFollowDao.getAllBoardTopicCount(bid);
    }

    @Override
    public List<Integer> getboardIDListUId(int uid) {
        return boardFollowDao.getboardIDListUId(uid);
    }

    @Override
    public List<Post> getNewAllBoardTopic(List<Integer> boardIDList, int pageStart, int pageSize) {
        return boardFollowDao.getNewAllBoardTopic(boardIDList,new RowBounds(pageStart,pageSize));
    }

    @Override
    public long getNewAllBoardTopicCount(List<Integer> boardIDList) {
        return boardFollowDao.getNewAllBoardTopicCount(boardIDList);
    }
}
