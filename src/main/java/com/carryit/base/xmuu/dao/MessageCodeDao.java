package com.carryit.base.besttmwuu.dao;

import com.carryit.base.besttmwuu.entity.MessageCode;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageCodeDao {


    MessageCode getIdByPhone(String phoneNumber);

    void update(MessageCode messageCode);

    void insert(MessageCode messageCode);
}
