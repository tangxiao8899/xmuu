package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Level;
import com.carryit.base.besttmwuu.entity.Member;

import java.util.List;

public interface MemberService {

    Member getMemberById(int uid);

    Member getWealthById(int uid);

}
