package com.carryit.base.xmuu.dao;

import com.carryit.base.xmuu.entity.MessageCode;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageCodeDao {


    MessageCode getIdByPhone(String phoneNumber);

    void update(MessageCode messageCode);

    void insert(MessageCode messageCode);
}
