package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {
    /**
     * 通过ID查找
     * @param id
     * @return
     */
    Product findById(int id);
}
