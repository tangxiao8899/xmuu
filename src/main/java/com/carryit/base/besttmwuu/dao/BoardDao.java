package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardDao {

    public List<Board> getBoardByCid(Integer id);
}
