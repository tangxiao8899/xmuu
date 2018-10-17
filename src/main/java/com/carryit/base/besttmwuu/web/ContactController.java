package com.carryit.base.besttmwuu.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.ContactsWithoudAddReq;
import com.carryit.base.besttmwuu.entity.ImsUsers;
import com.carryit.base.besttmwuu.service.ContactService;

/**
 * 联系人相关controller
 * */
@RestController
@RequestMapping("/contact")
public class ContactController extends BaseController {
	
	@Autowired
	ContactService contactService;
	
	/**
	 * 获取当前用户的所有联系人中，还未添加好友的人员
	 * */
    @RequestMapping(value = "/contactswithoutadd", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getNotFrinends(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }
    
    /**
     * 查询指定用户的好友数量
     * 请求参数格式：
     * {
		'uid':12
		}
     * 
     * 
     * 返回参数格式：
	   {
		    "msg": "ok",
		    "code": 200,
		    "data": {
		        "count": 1
		    }
		}
     * */
    @RequestMapping(value = "/friendsCount", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getFriendsCount(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }

	@Override
	public JSONObject runTask(String json, int cmd) {
		if(cmd == 0) {
//			获取当前用户的所有联系人中，还未添加好友的人员
			ContactsWithoudAddReq contactsWithoudAddReq = p(json, ContactsWithoudAddReq.class);
			if(contactsWithoudAddReq == null 
					|| contactsWithoudAddReq.getPhones() == null 
					|| contactsWithoudAddReq.getPhones().size() < 1
					|| contactsWithoudAddReq.getUid() == 0) {
				return doArraysResp(null, 400, "参数不符合要求");
			}
			List<ImsUsers> notFriends = contactService.getContactsWithoutAdd(contactsWithoudAddReq.getUid(), contactsWithoudAddReq.getPhones());
			return doArraysResp(notFriends.stream().map(user -> user.getPhone()).collect(Collectors.toList()));
		}
		
		switch (cmd) {
		case 0:
//			获取当前用户的所有联系人中，还未添加好友的人员
			ContactsWithoudAddReq contactsWithoudAddReq = p(json, ContactsWithoudAddReq.class);
			if(contactsWithoudAddReq == null 
					|| contactsWithoudAddReq.getPhones() == null 
					|| contactsWithoudAddReq.getPhones().size() < 1
					|| contactsWithoudAddReq.getUid() == 0) {
				return doArraysResp(null, 400, "参数不符合要求");
			}
			List<ImsUsers> notFriends = contactService.getContactsWithoutAdd(contactsWithoudAddReq.getUid(), contactsWithoudAddReq.getPhones());
			return doArraysResp(notFriends.stream().map(user -> user.getPhone()).collect(Collectors.toList()));
		case 1:
//			查询
			ContactsWithoudAddReq user = p(json, ContactsWithoudAddReq.class);
			if(user == null 
					|| user.getUid() == 0) {
				return doObjResp(null, 400, "参数不符合要求, 例如{'uid':12}");
			}
			List<ImsUsers> friends = contactService.getFriends(user.getUid());
			JSONObject resp = new JSONObject();
			resp.put("count", friends.size());
			return doObjResp(resp, 200, "ok");
		}
		return null;
	}

}
