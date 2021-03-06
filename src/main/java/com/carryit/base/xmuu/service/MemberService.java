package com.carryit.base.xmuu.service;

import com.alibaba.fastjson.JSONObject;
import com.carryit.base.xmuu.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberService {

    Member getMemberById(int uid);

    Member getWealthById(int uid);

    int getMemberByUIdAndLevel(int zhuquanzi, String level);

    void updateMemberByUIdAndLevel(int uid, String level);

    List<Member> getnormalMember(Integer zhuquanzi, long startTime, long endTime, int pageStart, int pageSize);

    long getnormalMemberCount(Integer zhuquanzi);

    List<Member> getadminMember(Integer zhuquanzi, long startTime, long endTime, int pageStart, int pageSize);

    long getadminMemberCount(Integer zhuquanzi);

    MemberDTO showMember(int uid);

    /**
     * 修改账户余额
     * @param uid 用户ID
     * @param credit2 余额
     */
    void updateMemberByUid(@Param("uid") int uid, @Param("credit2") float credit2);


    void addMember(Member member);

    MemberData getMemberDataByUId(int uid);

    void updateMemberDataByUId(MemberData req);

    /**
     * 通过手机号获取member信息
     * @param phone 手机号
     * @return member信息
     */
    Member getMemberByPhone(String phone);

    void updateMemberZhuQuanZi(Integer uid, int bid, String level);

    Member getMember(Integer uid, Integer bid);

    void updateUserByUid(UserReq uReq);

    void updateSincerityByUid(UserQeq uQeq);

    JSONObject getUserByCode(UserCode userCode);

    JSONObject getAllByCode(UserCode userCode);

    String getICodeByUid(Integer uid);

    Member getLevelByICode(String iCode);

    /**
     * 查询当前等级下的所有会员
     * @param level
     * @return
     */
    List<Member> getMemberForLevel(Integer level);

    /**
     * 查询所有城市
     * @return
     */
    List<Member> getMemberCity();

    /**
     *查询同城，当前等级下的所有会员
     * @param city
     * @param level
     * @return
     */
    List<Member> getMemberByCityAndLevel(String city,Integer level);

    /**
     * 查询所有城市、圈子
     * @return
     */
    List<Member> getMemberByCityAndQz();

    List<Member> getMemberByCityAndQzAndLevel(String city,Integer zhuquanzi,Integer level);

    void updateMemberDataInfo(MemberInfo req);

    String getSincerityInfo(int uid);
}
