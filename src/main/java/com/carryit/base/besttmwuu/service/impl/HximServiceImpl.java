package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.dao.MemberDao;
import com.carryit.base.besttmwuu.entity.*;
import com.carryit.base.besttmwuu.service.HximService;
import com.util.JerseyClientUtil;
import com.util.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.base.BaseController.p;

@Service
public class HximServiceImpl implements HximService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public ResultPojo getToken() throws Exception {


        JSONObject param = new JSONObject();
        param.put("grant_type", "client_credentials");
        param.put("client_id", PropertyUtil.getProperty("hxim.clientId"));
        param.put("client_secret", PropertyUtil.getProperty("hxim.clientSecret"));

        return JerseyClientUtil.postMethod(param.toString(), "/token");

    }

    @Override
    public ResultPojo registerUser(String json) throws Exception {
        ResultPojo rp = new ResultPojo();
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = JSON.parseObject(json);
            //校验授权信息
            if (!jo.containsKey("token")) {
                rp.setCode(401);
                rp.setMsg("请先获取授权信息");
                return rp;
            }
            //判断是单个注册还是多个注册
            if (jo.containsKey("users")) { // 多个注册
                JSONArray ja = JSONArray.parseArray(jo.getString("users"));
                //校验参数
                for (int i = 0; i < ja.size(); i++) {
                    JSONObject jao = ja.getJSONObject(i);
                    if (checkParam(rp, jao)) return rp;
                }
                rp = JerseyClientUtil.postTokenMethod(ja.toString(), jo.getString("token"), "/users");
            } else { // 单个注册
                //校验参数
                if (checkParam(rp, jo)) return rp;

                JSONObject subJo = new JSONObject();
                subJo.put("username", jo.getString("username"));
                subJo.put("password", jo.getString("password"));
                rp = JerseyClientUtil.postTokenMethod(subJo.toString(), jo.getString("token"), "/users");

            }
        } else {
            rp.setCode(400);
            rp.setMsg("请求参数异常");

        }
        return rp;

    }

    @Override
    public ResultPojo addFriends(String json) throws Exception {
        ResultPojo rp = new ResultPojo();
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = JSON.parseObject(json);
            rp = getToken();
            JSONObject subjo = JSON.parseObject(rp.getData().toString());
            String token ="";
            if(subjo.containsKey("access_token")){
                token = subjo.getString("access_token");
                if(StringUtils.isEmpty(token)){
                    rp.setCode(400);
                    rp.setMsg("token请求异常");
                }
            }else {
                rp.setCode(400);
                rp.setMsg("token请求异常");
            }
            if (!jo.containsKey("owner_username") || !jo.containsKey("friend_username")) {
                rp.setCode(400);
                rp.setMsg("请求参数异常");
            } else {
                Integer type = 1; //1 post 2 get  3 delete 4 put
                if(!StringUtils.isEmpty(jo.getString("type"))){
                    String t = jo.getString("type").toLowerCase();
                    switch (t){
                        case "post":
                            type = 1;
                            break;
                        case "get":
                            type = 2;
                            break;
                        case "delete":
                            type = 3;
                            break;
                        case "put":
                            type = 4;
                            break;
                    }
                }
                rp = JerseyClientUtil.postTokenMethod(jo.getString("token"), "/users/" + jo.getString("owner_username") + "/contacts/users/" + jo.getString("friend_username"),type);
                TFriends dFriends=new TFriends();


            }
        } else {
            rp.setCode(400);
            rp.setMsg("请求参数异常");

        }
        return rp;
    }

    @Override
    public ResultPojo getFriends(String json) throws Exception {
        ResultPojo rp = new ResultPojo();
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = JSON.parseObject(json);
            rp = getToken();
            JSONObject subjo = JSON.parseObject(rp.getData().toString());
            String token ="";
            if(subjo.containsKey("access_token")){
                 token = subjo.getString("access_token");
                if(StringUtils.isEmpty(token)){
                    rp.setCode(400);
                    rp.setMsg("token请求异常");
                }
            }else {
                rp.setCode(400);
                rp.setMsg("token请求异常");
            }

            if (!jo.containsKey("owner_username") || !jo.containsKey("type")) {
                rp.setCode(400);
                rp.setMsg("请求参数异常");
            } else {
                rp = JerseyClientUtil.postTokenMethod(token, "/users/" + jo.getString("owner_username") + "/"+jo.getString("type")+"/users" ,2);
                FriendResp rpData = new FriendResp();
                if (rp.getData() != null) {
                    Object o = JSONObject.toJSON(rp.getData());
                    String s = JSON.toJSONString(o);
                    rpData = p(s, FriendResp.class);
                    List<FriendRespUser> frList = new ArrayList<>();
                    if (rpData.getData() != null && rpData.getData().size() > 0) {
                        for (String phone : rpData.getData()) {
                            FriendRespUser frp = new FriendRespUser();
                            Member member = memberDao.getMemberByPhone(phone);
                            frp.setAvatar(member.getAvatar());
                            frp.setUid(member.getUid());
                            frp.setNickName(member.getNickName());
                            frp.setPhone(phone);
                            frList.add(frp);
                        }
                    }
                    rpData.setFriendRespUser(frList);
                    rp.setData(rpData);
                }

            }
        } else {
            rp.setCode(400);
            rp.setMsg("请求参数异常");

        }
        return rp;
    }

    @Override
    public ResultPojo getUserStatus(String json) throws Exception {
        ResultPojo rp = new ResultPojo();
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = JSON.parseObject(json);
            //校验授权信息
            if (!jo.containsKey("token")) {
                rp.setCode(401);
                rp.setMsg("请先获取授权信息");
                return rp;
            }
            if (!jo.containsKey("username")) {
                rp.setCode(400);
                rp.setMsg("请求参数异常");
            } else {
                rp = JerseyClientUtil.getTokenMethod(jo.getString("token"), "/users/" + jo.getString("username") +"/status" );
            }
        } else {
            rp.setCode(400);
            rp.setMsg("请求参数异常");

        }
        return rp;
    }

    @Override
    public ResultPojo offlineMsgCount(String json) throws Exception {
        ResultPojo rp = new ResultPojo();
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = JSON.parseObject(json);
            //校验授权信息
            if (!jo.containsKey("token")) {
                rp.setCode(401);
                rp.setMsg("请先获取授权信息");
                return rp;
            }
            if (!jo.containsKey("owner_username")) {
                rp.setCode(400);
                rp.setMsg("请求参数异常");
            } else {
                rp = JerseyClientUtil.postTokenMethod(jo.getString("token"), "/users/" + jo.getString("owner_username") +"/offline_msg_count" ,2);
            }
        } else {
            rp.setCode(400);
            rp.setMsg("请求参数异常");

        }
        return rp;
    }

    @Override
    public ResultPojo sendMessages(String json) throws Exception {
        ResultPojo rp = new ResultPojo();
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = JSON.parseObject(json);
            //校验授权信息
            if (!jo.containsKey("token")) {
                rp.setCode(401);
                rp.setMsg("请先获取授权信息");
                return rp;
            }
            if (!jo.containsKey("target_type") || !jo.containsKey("target") || !jo.containsKey("msg") || !jo.containsKey("from")) {
                rp.setCode(400);
                rp.setMsg("请求参数异常");
            } else {
                JSONObject subJo = new JSONObject();
                subJo.put("target_type",jo.getString("target_type"));
                subJo.put("target",JSONArray.parseArray(jo.getString("target")));
                subJo.put("msg",JSON.parseObject(jo.getString("msg")));
                subJo.put("from",jo.getString("from"));
                rp = JerseyClientUtil.postTokenMethod(subJo.toString(),jo.getString("token"), "/messages");
            }
        } else {
            rp.setCode(400);
            rp.setMsg("请求参数异常");

        }
        return rp;
    }

    @Override
    public ResultPojo resetPassword(String json) throws Exception {
        ResultPojo rp = new ResultPojo();
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = JSON.parseObject(json);
            //校验授权信息
            if (!jo.containsKey("token")) {
                rp.setCode(401);
                rp.setMsg("请先获取授权信息");
                return rp;
            }
            if (!jo.containsKey("username") || !jo.containsKey("newpassword")) {
                rp.setCode(400);
                rp.setMsg("请求参数异常");
            } else {
                JSONObject subJo = new JSONObject();
                subJo.put("newpassword", jo.getString("newpassword"));
                rp = JerseyClientUtil.postTokenMethod(subJo.toString(),jo.getString("token"), "/users/" + jo.getString("username") + "/password",4);
            }
        } else {
            rp.setCode(400);
            rp.setMsg("请求参数异常");

        }
        return rp;
    }

    @Override
    public ResultPojo getUser(String json) throws Exception {
        ResultPojo rp = new ResultPojo();
        if (!StringUtils.isEmpty(json)) {
            JSONObject jo = JSON.parseObject(json);
            //校验授权信息
            if (!jo.containsKey("token")) {
                rp.setCode(401);
                rp.setMsg("请先获取授权信息");
                return rp;
            }
            //判断是单个还是
            if (jo.containsKey("limit")) { // 多个用户
                rp = JerseyClientUtil.getTokenMethod(jo.getString("token"), "/users?limit="+jo.getString("limit"));
            } else { // 单个注册
                rp = JerseyClientUtil.getTokenMethod(jo.getString("token"), "/users/"+jo.getString("username"));
            }
        } else {
            rp.setCode(400);
            rp.setMsg("请求参数异常");

        }
        return rp;
    }

    /**
     * 校验参数
     *
     * @param rp
     * @param jao
     * @return
     */
    private boolean checkParam(ResultPojo rp, JSONObject jao) {
        if (!jao.containsKey("username") || !jao.containsKey("password")) {
            rp.setCode(400);
            rp.setMsg("请求参数异常");
            return true;
        }
        return false;
    }


}
