package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Globouns;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobounDao {


    Globouns getFindAll(int uid);
}
