package com.carryit.base.xmuu.service.impl;

import com.carryit.base.xmuu.dao.ProductDao;
import com.carryit.base.xmuu.entity.Product;
import com.carryit.base.xmuu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }
}
