package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopSnsPost;
import com.carryit.base.besttmwuu.entity.imsEweiShopSnsPostWithBLOBs;

public interface imsEweiShopSnsPostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopSnsPostWithBLOBs record);

    int insertSelective(imsEweiShopSnsPostWithBLOBs record);

    imsEweiShopSnsPostWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopSnsPostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(imsEweiShopSnsPostWithBLOBs record);

    int updateByPrimaryKey(imsEweiShopSnsPost record);
}