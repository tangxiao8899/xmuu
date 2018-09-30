package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.besttmwuu.entity.Post;

import java.util.List;

/**
 * 处理评价的类
 * */
public interface ImsEweiShopSnsPostService {
	public boolean addOne(ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs);

	void addTreds(ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPostWithBLOBs);

	List<Post> getcommentBypid(Integer id);
}
