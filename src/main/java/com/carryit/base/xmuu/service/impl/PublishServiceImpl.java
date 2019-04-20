package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.ImsEweiShopSnsPostMapper;
import com.carryit.base.besttmwuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.besttmwuu.service.PublishService;
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
