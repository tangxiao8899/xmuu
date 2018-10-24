package com.carryit.base.besttmwuu.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.carryit.base.besttmwuu.entity.MessageCode;
import com.carryit.base.besttmwuu.service.MessageCodeService;
import com.carryit.base.besttmwuu.service.UserService;
import com.carryit.base.besttmwuu.service.impl.MessageService;
import com.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ServiceConfig;
import com.bean.RegisterReq;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.impl.RegisterServiceImpl;
import com.util.Log;

@RestController
@RequestMapping("/register")
public class RegisterController extends BaseController {
    @SuppressWarnings("unchecked")
    public void resetResultType() {
        this.isHttpArraysJson = false;
    }

    @Autowired
    public MessageCodeService messageCodeService;
    @Autowired
    public RegisterServiceImpl mRegisterService;
    @Autowired
    public MessageService messageService;
    @Autowired
    public UserService userService;
    //isHttpArraysJson

    /**
     * consumes = MediaType.APPLICATION_JSON_VALUE
     *
     * @param data = {json}
     * @return 伪json
     */
    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject beginRegister(@RequestParam(value = "data", required = true) String data) {
        Log.e("注册=" + data);
        return callHttpReqTask(data.toString(), 0);
    }

    /**
     * 纯json
     *
     * @param data
     * @return
     */
    @RequestMapping(value = "/reg", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject beginRegisterII(@RequestBody(required = false) String data) {
        Log.e("注册信息=" + data);
        return callHttpReqTask(data, 0);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd) {
            case 2:
                try {
                    User user = p(json, User.class);
                    if (user != null) {
                        //获取验证码
                        String code = messageService.securityCode();

                        //保存手机号和验证码
                        messageCodeService.saveMessageCode(user.getPhone(), Integer.parseInt(code));
                        //发送短信
                        Map<String, String> map = messageService.sendSms(user.getPhone(), code);
                        if ("1".equals(map.get("code"))) {
                            return doObjRespSuccess("成功");
                        } else {
                            return faild("失败~", false);
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
            case 3:
                try {
                    User _user = p(json, User.class);
                    if (_user != null) {
                        if (_user.getPhone() != null) {
                            User data = mRegisterService.selectByPhone(_user.getPhone());
                            if (data == null) {
                                boolean flag = mRegisterService.addUser(_user);
                                if (flag) {
                                    return doObjRespSuccess("成功");
                                } else {
                                    return faild("失败~", false);
                                }
                            } else {
                                return faild("用户已存在~", false);
                            }

                        } else {
                            return faild("号码为空~", false);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
            case 4:
                try {
                    return messageCodeService.checkCode(json);
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
            case 5:
                try {
                    JSONObject jo = JSON.parseObject(json);
                    String phoneNumber = jo.getString("phone");
                    int code = jo.getInteger("code");
                    MessageCode messageCode=messageCodeService.getIdByPhone(phoneNumber);
                    if(StringUtils.isEmpty(messageCode)){ //验证码表没数据
                        return faild("未收到验证码~", false);
                    }else {
                        //有效期：创建时间 + 有效时长（60秒） > 当前时间
                        Long SMS_TIME = Long.valueOf(PropertyUtil.getProperty("sms_time"));
                        Long userfulTime = messageCode.getCreateTime().getTime() + SMS_TIME;
                        //验证码失效
                        if (messageCode.getStatus() != 1 || messageCode.getCode() != code || userfulTime < new Date().getTime()) {
                            return faild("验证码失效~", false);
                         }
                     }
                    User u = userService.selectByPhone(phoneNumber);
                    if (u!=null) {
                        int i = userService.updatePassWord(json);
                        if (i == 1) {
                            return doObjRespSuccess("成功");
                        } else {
                            return faild("失败~", false);
                        }
                    } else {
                        return faild("用户为空~", false);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }
//			default:
//			return register(json);
            case 6:
                try {
                    User newUser = p(json, User.class);
                    if (newUser != null) {
                        if (newUser.getUid() != null && newUser.getUid() != 0) {
                            User data = mRegisterService.getUserById(newUser.getUid());
                            if (data != null) {
                                int flag = mRegisterService.updateUser(newUser);
                                if (flag==1) {
                                    return doObjRespSuccess("成功");
                                } else {
                                    return faild("失败~", false);
                                }
                            } else {
                                return faild("用户不存在~", false);
                            }

                        } else {
                            return faild("用户不存在~", false);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~", false);
                }

        }
        return null;
    }

/*    public JSONObject register(String json) {
        try {
            Log.e("请求字符串=" + json);
            if (json == null || "\"\"".equals(json) || json.isEmpty()) {
                String phone = new Random().nextInt(Integer.MAX_VALUE) + "";
                json = "{\"phone\":\"18311" + phone + "45251\",\"password\":\"123456\"}";
            }
            Log.e("请求字符串长度=" + json.length());
            RegisterReq reqJson = p(json, RegisterReq.class);
            if (reqJson != null) {
                Log.e("reqJson=" + reqJson.toString());
            }
            Log.e("reqParamJson=" + reqParamJson);
            Log.e("debug开关=" + ServiceConfig.DEBUG);
            User data = mRegisterService.selectByPhone(reqJson.phone);
            boolean result = false;
            if (data == null) {
                data = new User();

                data.setUserName(reqJson.code);
                data.setPassword(reqJson.password);
                data.setAddress(reqJson.address);
                data.setAge(reqJson.age);
                data.setEducation(reqJson.education);
                data.setIdCard(reqJson.idCard);
                data.setNeed(reqJson.need);
                data.setPhone(reqJson.phone);
                data.setCompanyProfile(reqJson.companyProfile);
                data.setUserName(reqJson.userName);
                data.setCorporateName(reqJson.corporateName);
                data.setMailbox(reqJson.mailbox);
                data.setMarriage(reqJson.marriage);
                data.setServices(reqJson.services);
                data.setSex(reqJson.sex);

                result = mRegisterService.addUser(data);
                Log.e("注册结果=" + result);
            } else {
                return faild("该用户已经存在~");
            }
            return result ? doObjResp(data) : faild("注册失败~");
        } catch (Exception e) {
            e.printStackTrace();
            return faild("失败~", false);
        }
    }*/


    //TODO
    //发送短信验证码，调用saveMessageCode(String phoneNum,int code)方法，保存验证码信息
    @RequestMapping(value = "/getSecurityCode")
    public JSONObject getSecurityCode(@RequestBody(required = false) String json) {
        Log.e("号码=" + json);
        return callHttpReqTask(json, 2);
    }

    //TODO
    //校验验证码是否正确，正确->跳转完善个人注册信息页面；错误->给出提示
    @RequestMapping(value = "/checkCode")
    public JSONObject checkCode(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 4);
    }

    //TODO
    //完善个人信息
    @RequestMapping(value = "/addUser", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject addUser(@RequestBody(required = false) String json) {
        Log.e("号码=" + json);
        return callHttpReqTask(json, 3);
    }

    //TODO
    //修改密码
    @RequestMapping(value = "/updatePassWord", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject updatePassWord(@RequestBody(required = false) String json) {
        Log.e("号码=" + json);
        return callHttpReqTask(json, 5);
    }

    //TODO
    //修改个人信息
    @RequestMapping(value = "/updateUser", method = {RequestMethod.GET, RequestMethod.POST})
    public JSONObject updateUser(@RequestBody(required = false) String json) {
        Log.e("号码=" + json);
        return callHttpReqTask(json, 6);
    }
}
