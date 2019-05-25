package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.xmuu.entity.Post;
import com.carryit.base.xmuu.entity.imsEweiShopSnsPost;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    List<Map<String, Object>> receiveTreds(@Param("id") Integer id,@Param("page") int page,@Param("pageSize") int pageSize);
}