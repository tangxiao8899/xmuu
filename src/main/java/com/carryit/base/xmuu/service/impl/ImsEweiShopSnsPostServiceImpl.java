package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.ImsEweiShopSnsPostMapper;
import com.carryit.base.xmuu.dao.MemberDao;
import com.carryit.base.xmuu.dao.imsEweiShopMemberMapper;
import com.carryit.base.xmuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.xmuu.entity.Member;
import com.carryit.base.xmuu.entity.Post;
import com.carryit.base.xmuu.service.ImsEweiShopSnsPostService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ImsEweiShopSnsPostServiceImpl implements ImsEweiShopSnsPostService {
	@Autowired
	ImsEweiShopSnsPostMapper postMapper;

	@Autowired
	imsEweiShopMemberMapper memberMapper;

	@Autowired
	MemberDao memberDao;

	@Override
	public boolean addOne(ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs) {
//		查询评论人的昵称,并设置
		Member member = memberDao.getMemberById(imsEweiShopSnsPostWithBLOBs.getUid());
		imsEweiShopSnsPostWithBLOBs.setNickname(member.getNickName());
//		设置创建时间
		long seconds = System.currentTimeMillis();
		imsEweiShopSnsPostWithBLOBs.setCreatetime(Long.valueOf(String.valueOf(seconds)));
		
		postMapper.insert(imsEweiShopSnsPostWithBLOBs);
		return true;
	}

	@Override
	public void addTreds(ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs) {
		Member member = memberDao.getMemberById(imsEweiShopSnsPostWithBLOBs.getUid());
		//查询发布动态人的昵称，头像。
		imsEweiShopSnsPostWithBLOBs.setNickname(member.getNickName());
		imsEweiShopSnsPostWithBLOBs.setAvatar(member.getAvatar());
		long seconds = System.currentTimeMillis();
		imsEweiShopSnsPostWithBLOBs.setCreatetime(Long.valueOf(String.valueOf(seconds)));
		postMapper.insert(imsEweiShopSnsPostWithBLOBs);
	}

	@Override
	public List<Post> getcommentBypid(Integer id) {
		return postMapper.getcommentBypid(id);
	}

	@Override
	public List<Post> getTredsList(int uid, int pageStart, int pageSize) {
		return postMapper.getTredsList(uid,new RowBounds(pageStart,pageSize));
	}

	@Override
	public long getTredsCount(int uid) {
		return postMapper.getTredsCount(uid);
	}

	@Override
	public void delTreds(Integer id) {
		postMapper.delTreds(id);
	}

}
