package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Message {
    private static final Logger logger = LoggerFactory.getLogger(Message.class);


    @Value("#{config['Dysmsapi']}")
    private String Dysmsapi;

    @Value("#{config['dysmsapi.aliyuncs.com']}")
    private String dysmsapi_aliyuncs_com;

    @Value("#{config['accessKeyId']}")
    private String accessKeyId;

    @Value("#{config['accessKeySecret']}")
    private String accessKeySecret;
}
