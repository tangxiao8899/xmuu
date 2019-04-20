package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.imsEweiShopDiypage;

public interface imsEweiShopDiypageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopDiypage record);

    int insertSelective(imsEweiShopDiypage record);

    imsEweiShopDiypage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopDiypage record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopDiypage record);

    int updateByPrimaryKey(imsEweiShopDiypage record);
}