package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.ImsUsers;
import com.carryit.base.besttmwuu.entity.imsEweiShopMember;
import com.carryit.base.besttmwuu.entity.imsEweiShopMemberWithBLOBs;

import java.util.List;

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