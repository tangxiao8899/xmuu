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
    public JSONObject getCircles(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
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
		return null;
	}

}
