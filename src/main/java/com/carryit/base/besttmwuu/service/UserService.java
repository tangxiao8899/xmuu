package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.User;

public interface UserService {
    public User getUserById(int userId);
    public User selectByPhone(String phone);
    boolean addUser(User record);
}
