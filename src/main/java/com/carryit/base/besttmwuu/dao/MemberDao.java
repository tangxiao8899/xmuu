package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDao {

    Member getMemberById(int uid);

    Member getWealthById(int uid);

}
