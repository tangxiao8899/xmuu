package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.carryit.base.besttmwuu.entity.Mesage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

/*
* 短信发送验证码&批量发送短信（不能超过100条）
*
* */

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);


    @Value(value = "${aysmsapi}")
    private String Dysmsapi;

    @Value(value = "${dysmsapi.aliyuncs.com}")
    private String dysmsapi_aliyuncs_com;

    @Value(value = "${accessKeyId}")
    private String accessKeyId;

    @Value(value = "${accessKeySecret}")
    private String accessKeySecret;

    private IAcsClient acsClient = null;

    @PostConstruct
    private void initOssClient() throws ClientException {
        logger.info("acs start, Dysmsapi: " + Dysmsapi
                + ", accessKeyId:" + accessKeyId
                + ", accessKeySecret:" + accessKeySecret
                + ", dysmsapi_aliyuncs_com:" + dysmsapi_aliyuncs_com);
        // 初始化ascClient,暂时不支持多region
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", Dysmsapi,
                dysmsapi_aliyuncs_com);
        acsClient = new DefaultAcsClient(profile);
        logger.info("ossClient已实例");
//        List<String> phoneNumber = new ArrayList<>();
//        phoneNumber.add("17762369527");
//        phoneNumber.add("13135671520");
//
//        List<String> signName = new ArrayList<>();
//        signName.add("陈志胜");
//        signName.add("陈志胜");
//
//        List<Mesage> mesList = new ArrayList<>();
//        Mesage m = new Mesage();
//        Mesage m1 = new Mesage();
//        m.setCode("7788");
//        m1.setCode("520");
//        mesList.add(m);
//        mesList.add(m1);
//
//        String phoneNumberList = JSON.toJSONString(phoneNumber);
//        String signNameList = JSON.toJSONString(signName);
//        String mes = JSON.toJSONString(mesList);
//
//        SendBatchSmsRequest request = new SendBatchSmsRequest();
//        request.setMethod(MethodType.POST);
//        request.setPhoneNumberJson(phoneNumberList);
//        request.setSignNameJson(signNameList);
//        request.setTemplateCode("SMS_78370060");
//        request.setTemplateParamJson(mes);
//        try {
//            SendBatchSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//            if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//                logger.info("请求成功");
//            }
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
    }

    /*
    * 生成六位随机验证码
    *
    * */

    public String securityCode() {
        //生成验证码
        String code = new Random().nextInt(1000000)+"";
        if(code.length()!=6){
            return securityCode();
        }
        return code;
    }

    /*
    * 短信发送验证码
    *
    * */
public void sendSms(String phoneNumber, int code) {
        //生成验证码
        //int code = new Random().nextInt(1000000);
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(phoneNumber);
        request.setSignName("陈志胜");
        request.setTemplateCode("SMS_78370060");
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        try {
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                logger.info("请求成功");
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    //批量发送
    public void sendBatchSms(List<String> phoneNumber, List<String> signName, List<Mesage> mesList) {
        String phoneNumberList = JSON.toJSONString(phoneNumber);
        String signNameList = JSON.toJSONString(signName);
        String mes = JSON.toJSONString(mesList);

        SendBatchSmsRequest request = new SendBatchSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumberJson(phoneNumberList);
        request.setSignNameJson(signNameList);
        request.setTemplateCode("SMS_78370060");
        request.setTemplateParamJson(mes);
        try {
            SendBatchSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
                logger.info("请求成功");
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }


}
