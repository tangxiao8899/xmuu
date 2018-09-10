package com.carryit.base.besttmwuu.service.impl;


import com.carryit.base.besttmwuu.dao.MessageCodeDao;
import com.carryit.base.besttmwuu.entity.MessageCode;
import com.carryit.base.besttmwuu.service.MessageCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Override
    public void saveMessageCode(String phoneNumber,int code) {
        MessageCode phone = this.getIdByPhone(phoneNumber);
        MessageCode messageCode=new MessageCode();
        messageCode.setCreateTime(new Date());
        messageCode.setCode(code);
        messageCode.setStatus(1);
        if(phone==null){
            messageCode.setPhone(phoneNumber);
            this.insert(messageCode);
        }else{
            messageCode.setId(phone.getId());
            this.update(messageCode);
        }
    }


}
