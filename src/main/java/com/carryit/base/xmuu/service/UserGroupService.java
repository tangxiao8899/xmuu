package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.Member;
import com.carryit.base.xmuu.entity.UserGroup;

import java.util.List;

public interface UserGroupService {
    List<UserGroup> getConcerned(int userId);

    List<Member> getMemberByZhuQuanZiId(Integer zhuquanzi);
}
