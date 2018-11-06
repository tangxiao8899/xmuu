package com.carryit.base.besttmwuu.dao;

import com.bean.req.FriendsReq;
import com.carryit.base.besttmwuu.entity.TFriends;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TFriendsDao {
    /**
     * 保存数据
     * @param friends TFriends实体
     */
    void save(TFriends friends);

    /**
     * 修改数据
     * @param friends TFriends实体
     */
    void update(TFriends friends);

    /**
     * 分页好友列表
     * @param uid 用户ID
     * @param pageIndex 当前页
     * @param pageSize 每页数量
     * @return 好友列表
     */
    List<TFriends> getFriends(@Param("phone") String phone,@Param("pageIndex") int pageIndex, @Param("pageSize")int pageSize);

    /**
     * 统计好友请求数
     * @param uid 用户ID
     * @return 总的好友请求数
     */
    int totalFriends(String phone);

    TFriends isFriends(@Param("loginUid") String loginUid, @Param("phone") String phone);

    FriendsReq getAllByUidPhone(@Param("phone") String phone,@Param("uid") int uid);
}
