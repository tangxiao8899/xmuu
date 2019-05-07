package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.*;

import java.util.List;

public interface BoardFollowService {

    BoardFollow getBoardByUid(int uid, int bid);

    void delete(int uid, int bid);

    void add(int uid, int bid, String time);

    List<Member> getMemberByZhuQuanZiId(Integer zhuquanzi);

    List<Board> getBoardFollowByUId(int uid);

    List<Board> getUnconcerned(int uid);

    long getTopicCount(int bid);

    long getFollowCount(int bid);

    List<Post> getAllBoardTopic(int bid, int pageStart, int pageSize);

    long getAllBoardTopicCount(int bid);

    List<Integer> getboardIDListUId(int uid);

    List<Post> getNewAllBoardTopic(List<Integer> boardIDList, int i, int pageSize);

    long getNewAllBoardTopicCount(List<Integer> boardIDList);

    List<BoardAll> getBoardAll(Integer uid,int page,int pageSize);
}
