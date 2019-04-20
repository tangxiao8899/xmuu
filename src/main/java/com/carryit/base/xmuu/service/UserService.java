package com.carryit.base.xmuu.service;

import com.carryit.base.xmuu.entity.User;

public interface UserService {
    public User getUserById(int userId);
    public User selectByPhone(String phone);
    boolean addUser(User record);

    int updatePassWord(String json);

    User getUserByPoneAndPassword(String phone);

    //boolean updateByPhone(User user);
}
