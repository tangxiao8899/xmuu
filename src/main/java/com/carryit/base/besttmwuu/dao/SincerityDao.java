package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.Sincerity;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface SincerityDao {


    Sincerity getNumberById(int uid);

    boolean insterLikes(int uid);

    void updateCredit(int uid);
    
    Sincerity querySincerityByUserId(int uid);

	void addOne(Sincerity newSincerity);
	
	void updateOne(Sincerity sincerity);
}
