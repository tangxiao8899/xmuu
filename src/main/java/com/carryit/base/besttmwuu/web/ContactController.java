package com.carryit.base.besttmwuu.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.ContactUserResp;
import com.bean.req.ContactsWithoudAddReq;
import com.carryit.base.besttmwuu.entity.ImsUsers;
import com.carryit.base.besttmwuu.entity.imsMcMembersWithBLOBs;
import com.carryit.base.besttmwuu.service.ContactService;
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
	ImsMcMemberService memberService;
	
	/**
	 * 获取当前用户的所有联系人中，还未添加好友的人员&好友
	 * 返回數據格式：
	 * {
    "msg": "ok",
    "code": 200,
    "data": {
        "notFriends": [
            {
                "uid": 2,
                "phone": "17762369527",
                "nickname": null,
                "avatar": null
            },
            {
                "uid": 4,
                "phone": "18772374640",
                "nickname": null,
                "avatar": null
            },
            {
                "uid": 23,
                "phone": "13571860966",
                "nickname": "@",
                "avatar": "http://thirdwx.qlogo.cn/mmopen/ajNVdqHZLLC4XGk36PHkCGWjdFue1ELMiaKkOxhDl5oicNvJqYErb31kyfQejkVKYEEU48qLxYnXvDIClD7T0Pug/132132"
            },
            {
                "uid": 19,
                "phone": "18171277713",
                "nickname": "王自知Eric",
                "avatar": "http://thirdwx.qlogo.cn/mmopen/n1fGhtL2oJQ3L0tff2tO9mpCvXbhq3dpicYdukncIhzdENv35CaplbicYceTjbibdk5El9oZ3u5sWibLfnMggX0QuOZ8mDicFXoBs/132"
            },
            {
                "uid": 20,
                "phone": "18727582389",
                "nickname": "幻影 黄R",
                "avatar": "http://thirdwx.qlogo.cn/mmopen/LIND77SSex8olfQZcR9Mmjx8YMrnG2yAice3RyVAvlugUuSRK2iaSApd1CnR5ibPO8CVC6QAkceWNpOUQRoHI5ohfVw3mFEx3sK/132"
            }
        ],
        "friends": [
            {
                "uid": 21,
                "phone": "15171635573",
                "nickname": "",
                "avatar": ""
            }
        ]
    }
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
//			获取当前用户的所有联系人中，还未添加好友的人员&好友
			ContactsWithoudAddReq contactsWithoudAddReq = p(json, ContactsWithoudAddReq.class);
			if(contactsWithoudAddReq == null 
					|| contactsWithoudAddReq.getPhones() == null 
					|| contactsWithoudAddReq.getPhones().size() < 1
					|| contactsWithoudAddReq.getUid() == 0) {
				return doArraysResp(null, 400, "参数不符合要求");
			}
			List<ImsUsers> notFriends = contactService.getContactsWithoutAdd(contactsWithoudAddReq.getUid(), contactsWithoudAddReq.getPhones());
			List<ImsUsers> friends = contactService.getFriends(contactsWithoudAddReq.getUid());
			
			JSONObject result = new JSONObject();
			result.put("notFriends", convertFrom(notFriends));
			result.put("friends", convertFrom(friends));
			return doObjResp(result, 200, "ok");
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
	
	
	private List<ContactUserResp> convertFrom(List<ImsUsers> users) {
		List<ContactUserResp> result = new ArrayList<>();
		for(ImsUsers user: users) {
			ContactUserResp userResp = new ContactUserResp();
			BeanUtils.copyProperties(user, userResp);
			imsMcMembersWithBLOBs member = memberService.findMemberByUid(user.getUid());
			if(member != null) {
				userResp.setNickname(member.getNickname());
				userResp.setAvatar(member.getAvatar());
			}
			result.add(userResp);
		}
		
		return result;
	}

}
