package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.Circles;
import com.carryit.base.xmuu.entity.Culturewall;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CirclesDao {

    List<Circles> getCircles();

    List<Circles> getCirclesInfo(@Param("title") String title,@Param("pageIndex") int pageIndex, @Param("pageSize")int pageSize);

    void updateCulturewallByBid(Culturewall culturewall);
}
