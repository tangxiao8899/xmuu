package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.Order;

import java.util.List;

public interface OrderService {

    void save(Order order);

    /**
     * 根据订单号查订单
     * @param orderCode
     * @return
     */
    List<Order> queryOrder(String orderCode);

    /**
     * 修改订单状态
     */
    void update(Order order);

    int queryOrderCount(Integer bid, String level);
}
