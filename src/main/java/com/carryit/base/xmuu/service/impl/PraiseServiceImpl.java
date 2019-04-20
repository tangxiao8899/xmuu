package com.carryit.base.xmuu.service.impl;

import java.util.List;

import com.carryit.base.xmuu.dao.TPraiseMapper;
import com.carryit.base.xmuu.entity.TPraise;
import com.carryit.base.xmuu.entity.TPraiseExample;
import com.carryit.base.xmuu.service.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PraiseServiceImpl implements PraiseService {
	
	@Autowired
	TPraiseMapper praiseMapper;

	@Override
	public boolean praise(TPraise praise) {
		TPraiseExample example = new TPraiseExample();
		TPraiseExample.Criteria criteria = example.createCriteria();
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

	@Override
	public long getPraiseCount(Integer id) {
		return praiseMapper.getPraiseCount(id);
	}

	@Override
	public List<String> getPraiseImage(Integer id) {
		return praiseMapper.getPraiseImage(id);
	}

	@Override
	public TPraise getPraise(int uid, Integer id) {
		return praiseMapper.getPraise(uid,id);
	}

	@Override
	public void deletepraise(int id, int uid) {
		praiseMapper.deletepraise(id,uid);
	}

}
