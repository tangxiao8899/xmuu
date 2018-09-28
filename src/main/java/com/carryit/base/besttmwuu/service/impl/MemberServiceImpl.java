package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.MemberDao;
import com.carryit.base.besttmwuu.dao.imsEweiShopMemberMapper;
import com.carryit.base.besttmwuu.entity.Level;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.imsEweiShopMember;
import com.carryit.base.besttmwuu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public Member getMemberById(int uid) {
        return memberDao.getMemberById(uid);
    }

    @Override
    public Member getWealthById(int uid) {
        return memberDao.getWealthById(uid);
    }

    @Override
    public int getMemberByUIdAndLevel(int zhuquanzi, int level) {
        return memberDao.getMemberByUIdAndLevel(zhuquanzi,level);
    }

    @Override
    public void updateMemberByUIdAndLevel(int uid, int level) {
        memberDao.updateMemberByUIdAndLevel(uid,level);
    }

    @Override
    public void updateNumber(int uid) {
        memberDao.updateNumber(uid);
    }


}
