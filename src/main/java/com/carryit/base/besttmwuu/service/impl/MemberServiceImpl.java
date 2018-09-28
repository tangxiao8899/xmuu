package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.MemberDao;
import com.carryit.base.besttmwuu.dao.imsEweiShopMemberMapper;
import com.carryit.base.besttmwuu.entity.Level;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.imsEweiShopMember;
import com.carryit.base.besttmwuu.service.MemberService;
import org.apache.ibatis.session.RowBounds;
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
    public List<Member> getnormalMember(Integer zhuquanzi,long startTime,long endTime, int pageStart, int pageSize) {
        return memberDao.getnormalMember(zhuquanzi, startTime, endTime,new RowBounds(pageStart,pageSize));
    }

    @Override
    public long getnormalMemberCount(Integer zhuquanzi) {
        return memberDao.getnormalMemberCount(zhuquanzi);
    }

    @Override
    public List<Member> getadminMember(Integer zhuquanzi, long startTime, long endTime, int pageStart, int pageSize) {
        return memberDao.getadminMember(zhuquanzi, startTime, endTime,new RowBounds(pageStart,pageSize));
    }

    @Override
    public long getadminMemberCount(Integer zhuquanzi) {
        return memberDao.getadminMemberCount(zhuquanzi);
    }


}
