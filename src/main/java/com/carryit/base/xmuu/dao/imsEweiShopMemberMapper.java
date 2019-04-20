package com.carryit.base.xmuu.dao;


import java.util.List;

import com.carryit.base.xmuu.entity.ImsUsers;
import com.carryit.base.xmuu.entity.imsEweiShopMember;
import com.carryit.base.xmuu.entity.imsEweiShopMemberWithBLOBs;
import org.springframework.stereotype.Repository;

@Repository
public interface imsEweiShopMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopMemberWithBLOBs record);

    int insertSelective(imsEweiShopMemberWithBLOBs record);

    imsEweiShopMemberWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopMemberWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopMemberWithBLOBs record);

    int updateByPrimaryKey(imsEweiShopMember record);
    
    imsEweiShopMember getByUid(ImsUsers user);

	List<imsEweiShopMember> findNicknameavatarAndByUid(int uid);
}