package com.carryit.base.besttmwuu.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);


    @Value(value="${aysmsapi}")
    private String Dysmsapi;

    @Value(value="${dysmsapi.aliyuncs.com}")
    private String dysmsapi_aliyuncs_com;

    @Value(value="${accessKeyId}")
    private String accessKeyId;

    @Value(value="${accessKeySecret}")
    private String accessKeySecret;

    @Value(value="${signName}")
    private String signName;

    private IAcsClient acsClient =null;
    @PostConstruct
    private void initOssClient() throws ClientException {
        logger.info("acs start, Dysmsapi: "+Dysmsapi
                +", accessKeyId:"+accessKeyId
                +", accessKeySecret:"+accessKeySecret
                +", dysmsapi_aliyuncs_com:"+dysmsapi_aliyuncs_com);
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", Dysmsapi,
                dysmsapi_aliyuncs_com);
         acsClient = new DefaultAcsClient(profile);
        logger.info("ossClient已实例");
    }

    public  void sendSms(String phoneNumber){

    }
}
