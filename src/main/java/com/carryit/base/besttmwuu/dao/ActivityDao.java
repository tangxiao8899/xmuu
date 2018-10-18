package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Activity;
import com.carryit.base.besttmwuu.entity.Board;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDao {


    void add(Activity activity);

    long getPageCount();

    List<Activity> getPage(RowBounds rowBounds);
}
