package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopSnsBoard;
import com.carryit.base.besttmwuu.entity.imsEweiShopSnsBoardWithBLOBs;

public interface imsEweiShopSnsBoardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopSnsBoardWithBLOBs record);

    int insertSelective(imsEweiShopSnsBoardWithBLOBs record);

    imsEweiShopSnsBoardWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopSnsBoardWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopSnsBoardWithBLOBs record);

    int updateByPrimaryKey(imsEweiShopSnsBoard record);
}