package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.imsEweiShopMemberMessageTemplateType;

public interface imsEweiShopMemberMessageTemplateTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(imsEweiShopMemberMessageTemplateType record);

    int insertSelective(imsEweiShopMemberMessageTemplateType record);

    imsEweiShopMemberMessageTemplateType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(imsEweiShopMemberMessageTemplateType record);

    int updateByPrimaryKey(imsEweiShopMemberMessageTemplateType record);
}