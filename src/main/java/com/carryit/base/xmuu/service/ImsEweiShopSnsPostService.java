package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.xmuu.entity.Post;

import java.util.List;

/**
 * 处理评价的类
 * */
public interface ImsEweiShopSnsPostService {
	public boolean addOne(ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs);

	void addTreds(ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs);

	List<Post> getcommentBypid(Integer id);

    List<Post> getTredsList(int uid, int pageStart, int pageSize);

    long getTredsCount(int uid);

	void delTreds(Integer id);
}
