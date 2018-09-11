package com.carryit.base.besttmwuu.service.impl;


import com.carryit.base.besttmwuu.dao.MessageCodeDao;
import com.carryit.base.besttmwuu.entity.MessageCode;
import com.carryit.base.besttmwuu.service.MessageCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageCodeService")
public class MessageCodeServiceImpl implements MessageCodeService{
    @Autowired
    public MessageCodeDao messageCodeDao;



    @Override
    public String getByPhone(String phoneNumber) {
        return null;
    }

    @Override
    public void insert(MessageCode messageCode) {
        messageCodeDao.insert(messageCode);
    }

    @Override
    public void update(MessageCode messageCode) {
        messageCodeDao.update(messageCode);
    }

    @Override
    public MessageCode getIdByPhone(String phoneNumber) {
        return messageCodeDao.getIdByPhone(phoneNumber);
    }


}
