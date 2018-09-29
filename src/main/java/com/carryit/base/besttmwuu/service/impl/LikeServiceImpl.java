package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.service.LikeService;
import com.carryit.base.besttmwuu.service.MemberService;
import com.carryit.base.besttmwuu.service.SincerityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("likeService")
public class LikeServiceImpl implements LikeService{
    @Autowired
    private SincerityService sincerityService;

    @Autowired
    private MemberService memberService;

    @Override
    @Transactional//事物执行两个方法
    public void saveCreditNumber(int uid) {
        sincerityService.updateCredit(uid);
    }
}
