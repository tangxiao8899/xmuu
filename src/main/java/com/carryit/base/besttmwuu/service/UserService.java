package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.User;

public interface UserService {
    public User getUserById(int userId);

    boolean addUser(User record);
}
