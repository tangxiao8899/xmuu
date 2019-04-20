package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    /**
     * 保存订单信息
     * @param order
     */
    void save(Order order);

    List<Order> queryOrder(String orderCode);

    void update(Order order);

    int queryOrderCount(@Param("bid") Integer bid, @Param("level") String level);
}
