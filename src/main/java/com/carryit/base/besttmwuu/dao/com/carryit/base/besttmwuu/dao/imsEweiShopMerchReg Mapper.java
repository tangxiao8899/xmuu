package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopMerchReg WithBLOBs;
import com.carryit.base.besttmwuu.entity.imsEweiShopMerchReg;

public interface imsEweiShopMerchReg Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopMerchReg WithBLOBs record);

    int insertSelective(imsEweiShopMerchReg WithBLOBs record);

    imsEweiShopMerchReg WithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopMerchReg WithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopMerchReg WithBLOBs record);

    int updateByPrimaryKey(imsEweiShopMerchReg record);
}