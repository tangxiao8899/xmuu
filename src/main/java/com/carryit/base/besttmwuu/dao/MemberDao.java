package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Level;
import com.carryit.base.besttmwuu.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {

    Member getMemberById(int uid);

    Member getWealthById(int uid);

    int getMemberByUIdAndLevel(@Param("zhuquanzi") int zhuquanzi, @Param("level") int level);

    void updateMemberByUIdAndLevel(@Param("uid") int uid, @Param("level") int level);

    List<Member> getnormalMember(@Param("zhuquanzi") Integer zhuquanzi,@Param("startTime") long startTime,@Param("endTime")long endTime, RowBounds rowBounds);

    long getnormalMemberCount(@Param("zhuquanzi") Integer zhuquanzi);

    List<Member> getadminMember(@Param("zhuquanzi") Integer zhuquanzi,@Param("startTime") long startTime,@Param("endTime")long endTime, RowBounds rowBounds);

    long getadminMemberCount(@Param("zhuquanzi") Integer zhuquanzi);
}
