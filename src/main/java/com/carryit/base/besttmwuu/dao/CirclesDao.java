package com.carryit.base.besttmwuu.dao;

import com.bean.Page;
import com.bean.req.PageParam;
import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.Circles;
import com.carryit.base.besttmwuu.entity.Culturewall;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CirclesDao {

    List<Circles> getCircles();

    List<Circles> getCirclesInfo(@Param("title") String title,@Param("pageIndex") int pageIndex, @Param("pageSize")int pageSize);

    void updateCulturewallByBid(Culturewall culturewall);
}
