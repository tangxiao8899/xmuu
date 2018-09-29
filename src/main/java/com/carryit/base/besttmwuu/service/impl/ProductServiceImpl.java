package com.carryit.base.besttmwuu.service.impl;

import com.carryit.base.besttmwuu.dao.ProductDao;
import com.carryit.base.besttmwuu.entity.Product;
import com.carryit.base.besttmwuu.service.ProductService;
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
