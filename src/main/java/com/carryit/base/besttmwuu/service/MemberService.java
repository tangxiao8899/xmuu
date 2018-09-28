package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Level;
import com.carryit.base.besttmwuu.entity.Member;

import java.util.List;

public interface MemberService {

    Member getMemberById(int uid);

    Member getWealthById(int uid);

    int getMemberByUIdAndLevel(int zhuquanzi,int level);

    void updateMemberByUIdAndLevel(int uid, int level);

    void updateNumber(int uid);
}
