package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.BoardFollow;
import com.carryit.base.besttmwuu.entity.Level;
import com.carryit.base.besttmwuu.entity.Member;

import java.util.List;

public interface BoardFollowService {

    BoardFollow getBoardByUid(int uid, int bid);

    void delete(int uid, int bid);

    void add(int uid, int bid, long time);

    List<Member> getMemberByZhuQuanZiId(Integer zhuquanzi);

    List<Board> getBoardFollowByUId(int uid);
}
