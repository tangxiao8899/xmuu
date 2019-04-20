package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.ImsEweiShopSnsPostMapper;
import com.carryit.base.xmuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.xmuu.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("publishService")
public class PublishServiceImpl implements PublishService {
    @Autowired
    private ImsEweiShopSnsPostMapper imsEweiShopSnsPostMapper;
    @Override
    public List<ImsEweiShopSnsPostWithBLOBs> getimsEweiShopSnsPostWithBLOBs() {
        return imsEweiShopSnsPostMapper.getimsEweiShopSnsPostWithBLOBs();
    }
}
