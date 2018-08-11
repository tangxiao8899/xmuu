package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Member;

public interface MemberService {

    Member getMemberById(int uid);

    Member getWealthById(int uid);
}
