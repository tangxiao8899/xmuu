package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.OrderDao;
import com.carryit.base.besttmwuu.entity.Order;
import com.carryit.base.besttmwuu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public List<Order> queryOrder(String orderCode) {
        return orderDao.queryOrder(orderCode);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public int queryOrderCount(Integer bid, String level) {
        return orderDao.queryOrderCount(bid,level);
    }
}
