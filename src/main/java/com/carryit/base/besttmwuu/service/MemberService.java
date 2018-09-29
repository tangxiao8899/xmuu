package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Level;
import com.carryit.base.besttmwuu.entity.Member;

import java.util.List;

public interface MemberService {

    Member getMemberById(int uid);

    Member getWealthById(int uid);

    int getMemberByUIdAndLevel(int zhuquanzi, int level);

    void updateMemberByUIdAndLevel(int uid, int level);

    List<Member> getnormalMember(Integer zhuquanzi, long startTime, long endTime, int pageStart, int pageSize);

    long getnormalMemberCount(Integer zhuquanzi);

    List<Member> getadminMember(Integer zhuquanzi, long startTime, long endTime, int pageStart, int pageSize);

    long getadminMemberCount(Integer zhuquanzi);
}
