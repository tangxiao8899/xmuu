package com.carryit.base.xmuu.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.carryit.base.xmuu.dao.MemberDao;
import com.carryit.base.xmuu.dao.UserDao;
import com.carryit.base.xmuu.entity.*;
import com.carryit.base.xmuu.service.MemberService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    UserDao userDao;

    @Override
    public Member getMemberById(int uid) {
        return memberDao.getMemberById(uid);
    }

    @Override
    public Member getWealthById(int uid) {
        return memberDao.getWealthById(uid);
    }

    @Override
    public int getMemberByUIdAndLevel(int zhuquanzi, String level) {
        return memberDao.getMemberByUIdAndLevel(zhuquanzi,level);
    }

    @Override
    public void updateMemberByUIdAndLevel(int uid, String level) {
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

    @Override
    public MemberDTO showMember(int uid) {
        MemberDTO memberDTO=new MemberDTO();
        memberDTO=memberDao.showMember(uid);
        if (memberDTO.getCompanyProfile()==null)
        {
            memberDTO.setCompanyProfile("");
        }
        if (memberDTO.getCorporateName()==null)
        {
            memberDTO.setCorporateName("");
        }
        return memberDTO;
    }

    @Override
    public void updateMemberByUid(int uid, float credit2) {
        memberDao.updateMemberByUid(uid,credit2);
    }

    @Override
    public void addMember(Member member) {
        memberDao.addMember(member);
    }

    @Override
    public MemberData getMemberDataByUId(int uid) {
        return memberDao.getMemberDataByUId(uid);
    }

    @Override
    public void updateMemberDataByUId(MemberData req) {
        memberDao.updateMemberDataByUId(req);
    }

    @Override
    public Member getMemberByPhone(String phone) {
        return memberDao.getMemberByPhone(phone);

    }

    @Override
    public void updateMemberZhuQuanZi(Integer uid, int bid, String level) {
        memberDao.updateMemberZhuQuanZi(uid,bid,level);
    }

    @Override
    public Member getMember(Integer uid, Integer bid) {
        return memberDao.getMember(uid,bid);
    }

    @Override
    public void updateUserByUid(UserReq uReq) {
        memberDao.updateUserByUid(uReq);
    }

    @Override
    public void updateSincerityByUid(UserQeq uQeq) {
        memberDao.updateSincerityByUid(uQeq);
    }

    @Override
    public JSONObject getUserByCode(UserCode userCode) {
        JSONObject jo = new JSONObject();
       try {
           List<UserCodeRep> list = memberDao.getUserByCode(userCode);
           int count = memberDao.count(userCode);
           jo.put("data",list);
           jo.put("count",count);
           jo.put("msg","操作成功");
           jo.put("code",200);
       }catch (Exception e){
           e.printStackTrace();
           jo.put("msg",e.getMessage());
           jo.put("code",500);
       }

        return jo;
    }

    @Override
    public  JSONObject getAllByCode(UserCode userCode) {

      JSONObject jo = new JSONObject();

      try {
          List<UserCodeRep> list =  memberDao.getAllByCode(userCode);

          int count = memberDao.allCount(userCode);jo.put("data",list);
          jo.put("count",count);
          jo.put("msg","操作成功");
          jo.put("code",200);
      }catch (Exception e){
          e.printStackTrace();
          jo.put("msg",e.getMessage());
          jo.put("code",500);
      }
       return jo;
    }

    @Override
    public Member getLevelByICode(String iCode) {
        return memberDao.getLevelByICode(iCode);
    }

    @Override
    public List<Member> getMemberForLevel(Integer level) {

        return memberDao.getMemberForLevel(level);
    }

    @Override
    public List<Member> getMemberByCityAndLevel(String city, Integer level) {
        return memberDao.getMemberByCityAndLevel(city,level);
    }

    @Override
    public List<Member> getMemberByCityAndQz() {
        return memberDao.getMemberByCityAndQz();
    }

    @Override
    public List<Member> getMemberByCityAndQzAndLevel(String city, Integer zhuquanzi, Integer level) {
        return memberDao.getMemberByCityAndQzAndLevel(city,zhuquanzi,level);
    }

    @Override
    public List<Member> getMemberCity() {

        return memberDao.getMemberCity();
    }

    @Override
    public String getICodeByUid(Integer uid) {
        return memberDao.getICodeByUid(uid);
    }

    @Override
    @Transactional
    public void updateMemberDataInfo(MemberInfo req) {

        Member member=new Member();
        member.setUid(req.getUid());
        member.setAvatar(req.getAvatar());
        member.setAutograph(req.getAutograph());
        member.setCity(req.getCity());
        member.setNickName(req.getNickname());
        memberDao.updateMemberInfo(member);

        User user=new User();
        user.setUid(req.getUid());
        user.setIdCard(req.getIdCord());
        user.setNeed(req.getNeed());
        user.setMailbox(req.getMailbox());
        user.setEducation(req.getEducation());
        user.setMarriage(req.getMarriage());
        userDao.updateUserInfo(user);

    }

    @Override
    public String getSincerityInfo(int uid) {
        return this.memberDao.getSincerityInfo(uid);
    }
}
