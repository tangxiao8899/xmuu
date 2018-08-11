package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Sincerity;
import org.springframework.stereotype.Repository;

@Repository
public interface SincerityDao {


    Sincerity getNumberById(int uid);
}
