package com.carryit.base.xmuu.dao;


import java.util.List;

import com.carryit.base.xmuu.entity.Sincerity;
import com.carryit.base.xmuu.entity.UserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SincerityDao {


    int getNumberById(int uid);

    boolean insterLikes(int uid);

    void updateCredit(int uid);

    List<Sincerity> querySincerityByUserId(int uid);

	void addOne(Sincerity newSincerity);
	
	void updateOne(Sincerity sincerity);

    List<UserDTO> getSincerityList(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<UserDTO> queryList(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
