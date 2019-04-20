package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.UserDTO;

import java.util.List;

public interface WealthService {
    List<UserDTO> onTheList(long startTime, long endTime);

   

    long queryPageCount(long startTime, long endTime);

    List<UserDTO> queryPage(long startTime, long endTime);
}
