package com.carryit.base.besttmwuu.service;


import com.carryit.base.besttmwuu.entity.Sincerity;
import com.carryit.base.besttmwuu.entity.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SincerityService {

    Sincerity getNumberById(int uid);

    boolean addLikes(int uid);

    void updateCredit(int uid);

    List<UserDTO> getSincerityList(String startTime, String endTime);

    List<UserDTO> queryList(String startTime, String endTime);

    void addOne(Sincerity newSincerity);
}
