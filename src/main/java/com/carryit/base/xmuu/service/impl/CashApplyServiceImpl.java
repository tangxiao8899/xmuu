package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.CashApplyDao;
import com.carryit.base.besttmwuu.entity.CashApply;
import com.carryit.base.besttmwuu.entity.CashDataDTO;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.service.CashApplyService;
import com.carryit.base.besttmwuu.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class CashApplyServiceImpl implements CashApplyService {

    @Autowired
    CashApplyDao cashApplyDao;

    @Autowired
    MemberService memberService;

    @Override
    public void save(CashApply cashApply) {
        cashApplyDao.save(cashApply);
    }

    @Override
    public void update(CashApply cashApply) {
        cashApplyDao.update(cashApply);
    }

    @Override
    public int findMaxId() {
        return cashApplyDao.findMaxId();
    }

    @Override
    public List<CashDataDTO> cashData(String phone,int page,int limit) {
        int size = limit*(page-1);
        return cashApplyDao.cashData(phone,size,limit);
    }

    @Override
    public int count(String phone) {
        return cashApplyDao.count(phone);
    }

    @Override
    @Transactional
    public void updateCash(CashDataDTO dto) {
        //更新状态
        CashApply cashApply = new CashApply();
        cashApply.setId(dto.getId());
        cashApply.setStatus(dto.getStatus());
        cashApplyDao.update(cashApply);
        //如果是拒绝，回退之前扣除金额
        if(dto.getStatus() == -1){
            Member m = memberService.getMemberById(dto.getUid());
            memberService.updateMemberByUid(dto.getUid(),m.getCredit2() + (float) dto.getMoney());
        }
    }
}
