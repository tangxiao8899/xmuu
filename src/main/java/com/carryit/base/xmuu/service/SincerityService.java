package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.Sincerity;
import com.carryit.base.xmuu.entity.UserDTO;

import java.util.List;

public interface SincerityService {

    int getNumberById(int uid);

    boolean addLikes(int uid);

    void updateCredit(int uid);

    List<UserDTO> getSincerityList(String startTime, String endTime);

    List<UserDTO> queryList(String startTime, String endTime);

    void addOne(Sincerity newSincerity);
}
