package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberDao {

    Member getMemberById(int uid);

    Member getWealthById(int uid);

    int getMemberByUIdAndLevel(@Param("zhuquanzi") int zhuquanzi, @Param("level") String level);

    void updateMemberByUIdAndLevel(@Param("uid") int uid, @Param("level") String level);

    List<Member> getnormalMember(@Param("zhuquanzi") Integer zhuquanzi,@Param("startTime") long startTime,@Param("endTime")long endTime, RowBounds rowBounds);

    long getnormalMemberCount(@Param("zhuquanzi") Integer zhuquanzi);

    List<Member> getadminMember(@Param("zhuquanzi") Integer zhuquanzi,@Param("startTime") long startTime,@Param("endTime")long endTime, RowBounds rowBounds);

    long getadminMemberCount(@Param("zhuquanzi") Integer zhuquanzi);

    MemberDTO showMember(@Param("uid") int uid);

    /**
     * 修改账户余额
     * @param uid 用户ID
     * @param credit2 余额
     */
    void updateMemberByUid(@Param("uid") int uid, @Param("credit2") float credit2);


    void addMember(Member member);

    MemberData getMemberDataByUId(int uid);

    void updateMemberDataByUId(MemberData req);

    Member getMemberByPhone(String phone);

    void updateCredit1(@Param("uid") Integer uid, @Param("credit1") float credit1);

    void updateMemberZhuQuanZi(@Param("uid") Integer uid, @Param("bid") int bid, @Param("level") String level);

    Member getMember(@Param("uid") Integer uid, @Param("bid") Integer bid);

    void updateUserByUid(UserReq uReq);

    void updateSincerityByUid(UserQeq uQeq);

    List<UserCodeRep> getUserByCode(UserCode userCode);

    /**
     * 指数人数
     * @return
     */
    int count(UserCode userCode);

    List<UserCodeRep> getAllByCode(UserCode userCode);

    int allCount(UserCode userCode);

    String getICodeByUid(Integer uid);

    Member getLevelByICode(String iCode);
}
