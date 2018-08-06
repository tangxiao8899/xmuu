package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Circles;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CirclesDao {

    List<Circles> getCircles();

}
