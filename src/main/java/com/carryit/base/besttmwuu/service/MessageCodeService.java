package com.carryit.base.besttmwuu.service;

import com.carryit.base.besttmwuu.entity.MessageCode;

public interface MessageCodeService {
    String getByPhone(String phoneNumber);

    void insert(MessageCode messageCode);

    void update(MessageCode messageCode);

    MessageCode getIdByPhone(String phoneNumber);
}
