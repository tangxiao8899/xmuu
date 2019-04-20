package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.TPraise;

import java.util.List;

/**
 * 点赞服务类
 * */
public interface PraiseService {
	/**
	 * @param praise 点赞类
	 * @return 返回是否点赞成功，每篇文章每个人只能点赞一次
	 * */
	boolean praise(TPraise praise);


	long getPraiseCount(Integer id);

	List<String> getPraiseImage(Integer id);

	TPraise getPraise(int uid, Integer id);

	void deletepraise(int id, int uid);
}
