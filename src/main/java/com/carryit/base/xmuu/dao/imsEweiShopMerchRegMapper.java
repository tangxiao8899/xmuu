package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopMerchReg;
import com.carryit.base.besttmwuu.entity.imsEweiShopMerchRegWithBLOBs;

public interface imsEweiShopMerchRegMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopMerchRegWithBLOBs record);

    int insertSelective(imsEweiShopMerchRegWithBLOBs record);

    imsEweiShopMerchRegWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopMerchRegWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopMerchRegWithBLOBs record);

    int updateByPrimaryKey(imsEweiShopMerchReg record);
}