package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.BoardFollowDao;
import com.carryit.base.besttmwuu.entity.BoardFollow;
import com.carryit.base.besttmwuu.service.BoardFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardFollowService")
public class BoardFollowServiceImpl implements BoardFollowService {

    @Autowired
    private BoardFollowDao boardFollowDao;
    @Override
    public BoardFollow getBoardByUid(int uid, int bid) {
        return boardFollowDao.getBoardByUid(uid, bid);
    }
}
