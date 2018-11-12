package com.carryit.base.besttmwuu.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.ContactUserResp;
import com.bean.req.ContactsWithoudAddReq;
import com.carryit.base.besttmwuu.entity.ImsUsers;
import com.carryit.base.besttmwuu.entity.imsEweiShopMember;
import com.carryit.base.besttmwuu.entity.imsMcMembersWithBLOBs;
import com.carryit.base.besttmwuu.service.ContactService;
import com.carryit.base.besttmwuu.service.ImsEweiShopMemberService;
import com.carryit.base.besttmwuu.service.ImsMcMemberService;

/**
 * 联系人相关controller
 * */
@RestController
@RequestMapping("/contact")
public class ContactController extends BaseController {
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	ImsEweiShopMemberService memberService;
	
	/**
	 * 获取当前用户的所有联系人中，还未添加好友的人员&好友
	 * 返回數據格式：
	 * 
	 * {
    "msg": "ok",
    "code": 200,
    "data": [
        {
            "uid": 10,
            "phone": "18772374640",
            "isFriends": true,
            "nickname": "周杨",
            "avatar": "http://thirdwx.qlogo.cn/mmopen/Q3auHgzwzM7LPct8ZDAPgVAWjYkcyib1ae3wjgU9ib8UEaJr036LoOMX5Y2NFVZo66nJJu8QpmWWyNKTZRwCTna3zcrU6NGhPyiaIyr8Zs4w3I/132"
        },
        {
            "uid": 26,
            "phone": "13522129197",
            "isFriends": false,
            "nickname": "陆荣前^0^正能量",
            "avatar": "http://thirdwx.qlogo.cn/mmopen/n1fGhtL2oJSJsAyxrdXqKHUHhcdJahloIBiaUGUkC2ictjofPNQAwiaRjNVSGjWUKIuicn0fksMayZJw6sqyuibm6mo5XKZK9MGF3/132"
        }
    ]
}
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
        "count": 2
    }
}
     * */
    @RequestMapping(value = "/friendsCount", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getFriendsCount(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }

	@Override
	public JSONObject runTask(String json, int cmd) {
		
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
//			获取当前用户的所有联系人中，还未添加好友的人员&好友
			if(contactsWithoudAddReq == null 
					|| contactsWithoudAddReq.getPhones() == null 
					|| contactsWithoudAddReq.getPhones().size() < 1
					|| contactsWithoudAddReq.getUid() == 0) {
				return doArraysResp(null, 400, "参数不符合要求");
			}
			List<ImsUsers> notFriends = contactService.getContactsWithoutAdd(contactsWithoudAddReq.getUid(), contactsWithoudAddReq.getPhones());
			List<ImsUsers> friends = contactService.getFriends(contactsWithoudAddReq.getUid());
			
			List<ContactUserResp> allUsers = convertFrom(notFriends, false);
			allUsers.addAll(convertFrom(friends, true));
			return doArraysResp(allUsers, 200, "ok");
		case 1:
//			查询
			ContactsWithoudAddReq user = p(json, ContactsWithoudAddReq.class);
			if(user == null 
					|| user.getUid() == 0) {
				return doObjResp(null, 400, "参数不符合要求, 例如{'uid':12}");
			}
			List<ImsUsers> friends2 = contactService.getFriends(user.getUid());
			JSONObject resp = new JSONObject();
			resp.put("count", friends2.size());
			return doObjResp(resp, 200, "ok");
		}
		return null;
	}
	
	
	private List<ContactUserResp> convertFrom(List<ImsUsers> users, Boolean isFriends) {
		List<ContactUserResp> result = new ArrayList<>();
		for(ImsUsers user: users) {
			ContactUserResp userResp = new ContactUserResp();
			BeanUtils.copyProperties(user, userResp);
			imsEweiShopMember member = memberService.findNicknameavatarAndByUid(user.getUid());
			if(member != null) {
				userResp.setNickname(member.getNickname());
				userResp.setAvatar(member.getAvatar());
			}
			userResp.setIsFriends(isFriends);
			result.add(userResp);
		}
		
		return result;
	}

}
