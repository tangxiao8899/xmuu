package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.UserGroup;

import java.util.List;

public interface UserGroupService {
    List<UserGroup> getConcerned(int userId);
}
