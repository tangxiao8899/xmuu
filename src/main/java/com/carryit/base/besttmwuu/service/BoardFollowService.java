package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.BoardFollow;

public interface BoardFollowService {

    BoardFollow getBoardByUid(int uid, int bid);

    void delete(int uid, int bid);

    void add(int uid, int bid, long time);
}
