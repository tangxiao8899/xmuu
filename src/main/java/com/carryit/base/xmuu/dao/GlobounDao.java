package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.Globouns;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobounDao {


    Globouns getFindAll(int uid);
}
