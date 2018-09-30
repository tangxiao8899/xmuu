package com.carryit.base.besttmwuu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carryit.base.besttmwuu.dao.TPraiseMapper;
import com.carryit.base.besttmwuu.entity.TPraise;
import com.carryit.base.besttmwuu.entity.TPraiseExample;
import com.carryit.base.besttmwuu.entity.TPraiseExample.Criteria;
import com.carryit.base.besttmwuu.service.PraiseService;

@Service
public class PraiseServiceImpl implements PraiseService {
	
	@Autowired
	TPraiseMapper praiseMapper;

	@Override
	public boolean praise(TPraise praise) {
		TPraiseExample example = new TPraiseExample();
		Criteria criteria = example.createCriteria();
//		查询同一个用户，同一个动态id是否存在
		criteria.andUidEqualTo(praise.getUid());
		criteria.andImsEweiShopSnsPostIdEqualTo(praise.getImsEweiShopSnsPostId());
		List<TPraise> praises = praiseMapper.selectByExample(example);
//		存在则不能付出点赞
		if(praises != null && praises.size() > 0) {
			return false;
		} else {
//		不存在则点赞
			praiseMapper.insert(praise);
			return true;
		}
	}

}
