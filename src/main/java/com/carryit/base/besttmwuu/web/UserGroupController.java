package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.UserGroupReq;
import com.carryit.base.besttmwuu.entity.UserGroup;
import com.carryit.base.besttmwuu.service.UserGroupService;
import com.util.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userGroup")
public class UserGroupController extends BaseController {
	Logger logger = LoggerFactory.getLogger(UserGroupController.class);
	@Autowired
	private UserGroupService userGroupService;

	@RequestMapping(value = "/getConcerned", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json;charset=UTF-8")
	public JSONObject getConcerned(@RequestBody(required = false) String data) {
		return callHttpReqTask(data, 0);
	}

	@Override
	public JSONObject runTask(String json, int cmd) {
		logger.info("getConcerned...");
		Log.e("请求串=" + json);
		UserGroupReq req = p(json, UserGroupReq.class);
		List<UserGroup> list = new ArrayList<>();
		if (req != null && !StringUtils.isEmpty(req.userId)) {
			list = userGroupService.getConcerned(Integer.parseInt(req.userId));
		}
		return doArraysResp(list);
	}
}
