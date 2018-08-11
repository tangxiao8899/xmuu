package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.UserDTO;

import java.util.List;

public interface WealthService {
    List<UserDTO> onTheList(String startTime, String endTime);
}
