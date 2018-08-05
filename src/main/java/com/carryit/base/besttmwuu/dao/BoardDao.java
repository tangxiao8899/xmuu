package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Board;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardDao {

    public List<Board> getBoardByCid(@Param("id") Integer id);

    Board getBoardById(@Param("bid") int bid);
}
