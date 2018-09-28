package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Level;
import com.carryit.base.besttmwuu.entity.Member;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {

    Member getMemberById(int uid);

    Member getWealthById(int uid);

    int getMemberByUIdAndLevel(@Param("zhuquanzi") int zhuquanzi, @Param("level") int level);

    void updateMemberByUIdAndLevel(@Param("uid") int uid, @Param("level") int level);

    void updateNumber(int uid);
}
