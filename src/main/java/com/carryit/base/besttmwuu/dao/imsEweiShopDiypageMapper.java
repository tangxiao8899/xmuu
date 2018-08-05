package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopDiypage;

public interface imsEweiShopDiypageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopDiypage record);

    int insertSelective(imsEweiShopDiypage record);

    imsEweiShopDiypage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopDiypage record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopDiypage record);

    int updateByPrimaryKey(imsEweiShopDiypage record);
}