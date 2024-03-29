package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardFollowDao {

    BoardFollow getBoardByUid(@Param("uid") int uid, @Param("bid")int bid);

    void delete(@Param("uid") int uid, @Param("bid") int bid);

    void add(@Param("uid")int uid, @Param("bid") int bid, @Param("time") String time);

    List<Member> getMemberByZhuQuanZiId(@Param("zhuquanzi") Integer zhuquanzi);

    List<Board> getBoardFollowByUId(@Param("uid") int uid);

    List<Board> getUnconcerned(@Param("uid")int uid);

    long getTopicCount(@Param("bid") int bid);

    long getFollowCount(@Param("bid")int bid);

    List<Post> getAllBoardTopic(@Param("bid")int bid, RowBounds rowBounds);

    long getAllBoardTopicCount(@Param("bid")int bid);

    List<Integer> getboardIDListUId(int uid);

    List<Post> getNewAllBoardTopic(@Param("boardIDList") List<Integer> boardIDList, RowBounds rowBounds);

    long getNewAllBoardTopicCount(@Param("boardIDList") List<Integer> boardIDList);

    List<BoardAll> getBoardAll(@Param("uid") int uid,@Param("page") int page,@Param("pageSize") int pageSize);
}
