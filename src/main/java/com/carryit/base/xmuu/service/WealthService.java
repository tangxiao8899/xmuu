package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.UserDTO;

import java.util.List;

public interface WealthService {
    List<UserDTO> onTheList(long startTime, long endTime);

   

    long queryPageCount(long startTime, long endTime);

    List<UserDTO> queryPage(long startTime, long endTime);
}
