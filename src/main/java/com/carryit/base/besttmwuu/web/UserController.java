package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.entity.UserGroup;
import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.UserGroupReq;
import com.bean.resp.PersonInfo;
import com.carryit.base.besttmwuu.entity.ImsEweiShopSnsPostWithBLOBs;
import com.carryit.base.besttmwuu.service.PublishService;
import com.carryit.base.besttmwuu.service.UserService;
import com.util.Log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	public static final int TO_INDEX = 0;
	public static final int ADD_USER = 1;
	public static final int PUBLISH_CENTER = 2;
	@Autowired
	private PublishService publishService;

	@RequestMapping(value = "/showUser", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public JSONObject toIndex(@RequestBody(required = false) String data) {
		return callHttpReqTask(data, TO_INDEX);
	}

	@RequestMapping(value = "/addUser", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public JSONObject addUser(@RequestBody(required = false) String data) {
		return callHttpReqTask(data, ADD_USER);
	}

	// 发布中心cbx
	@RequestMapping(value = "/publishCenter", method = { RequestMethod.GET,
			RequestMethod.POST })
	public JSONObject publishCenter(@RequestBody(required = false) String data) {
//	public JSONObject publishCenter(@RequestParam(value = "data", required = true)String data){
		//@RequestParam(value = "data", required = true)
		return callHttpReqTask(data, PUBLISH_CENTER);
	}

	@Override
	public JSONObject runTask(String json, int cmd) {
		Log.e("请求字符串=" + json + "|命令|" + cmd);
		switch (cmd) {
		case TO_INDEX:
			UserGroupReq req = p(json, UserGroupReq.class);
			User data;
			if (req != null) {
				int userId = Integer.parseInt(req.id);
				data = this.userService.getUserById(userId);
			} else {
				return faild("请求参数异常~", false);
			}
			return doObjResp(data);
		case ADD_USER:
			User reqII = p(json, User.class);
			boolean flg = userService.addUser(reqII);
			return flg ? doObjRespSuccess("成功") : faild("失败~", false);
		case PUBLISH_CENTER:
			List<PersonInfo> datas = new ArrayList<PersonInfo>();
			List<ImsEweiShopSnsPostWithBLOBs> list = publishService.getimsEweiShopSnsPostWithBLOBs();
			for (ImsEweiShopSnsPostWithBLOBs imsEweiShopSnsPost : list) {
				PersonInfo person = new PersonInfo();
				person.id = imsEweiShopSnsPost.getId();
				person.Nickname = imsEweiShopSnsPost.getNickname();
				person.title = imsEweiShopSnsPost.getTitle();
				person.content = imsEweiShopSnsPost.getContent();
				datas.add(person);
			}
			return doArraysResp(datas); // 返回 list 用它
		default:
			return null;
		}
	}
}
