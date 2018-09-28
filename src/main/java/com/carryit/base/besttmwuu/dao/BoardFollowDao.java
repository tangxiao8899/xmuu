package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.BoardFollow;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.Post;
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
}
