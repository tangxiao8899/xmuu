package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.TPraise;
import com.carryit.base.besttmwuu.entity.TPraiseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPraiseMapper {
    int countByExample(TPraiseExample example);

    int deleteByExample(TPraiseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TPraise record);

    int insertSelective(TPraise record);

    List<TPraise> selectByExample(TPraiseExample example);

    TPraise selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TPraise record, @Param("example") TPraiseExample example);

    int updateByExample(@Param("record") TPraise record, @Param("example") TPraiseExample example);

    int updateByPrimaryKeySelective(TPraise record);

    int updateByPrimaryKey(TPraise record);
}