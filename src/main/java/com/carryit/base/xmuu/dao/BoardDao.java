package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.Board;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardDao {

    public List<Board> getBoardByCid(@Param("id") Integer id);

    Board getBoardById(@Param("bid") int bid);

    List<Board> getAllBoard();
}
