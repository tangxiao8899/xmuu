package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.UserDTO;

import java.util.List;

public interface WealthService {
    List<UserDTO> onTheList(long startTime, long endTime);
}
