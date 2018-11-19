package com.carryit.base.besttmwuu.dao;

import com.bean.req.TredsReq;
import com.carryit.base.besttmwuu.entity.Post;
import com.carryit.base.besttmwuu.entity.imsEweiShopSnsPost;
import com.carryit.base.besttmwuu.entity.ImsEweiShopSnsPostWithBLOBs;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImsEweiShopSnsPostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ImsEweiShopSnsPostWithBLOBs record);

    int insertSelective(ImsEweiShopSnsPostWithBLOBs record);

    ImsEweiShopSnsPostWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImsEweiShopSnsPostWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ImsEweiShopSnsPostWithBLOBs record);

    int updateByPrimaryKey(imsEweiShopSnsPost record);

    List<ImsEweiShopSnsPostWithBLOBs> getimsEweiShopSnsPostWithBLOBs();

    List<Post> getcommentBypid(Integer id);

    List<Post> getTredsList(int uid, RowBounds rowBounds);

    long getTredsCount(int uid);

    void delTreds(Integer id);
}