package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.UserGroup;
import com.carryit.base.besttmwuu.service.UserGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userGroup")
public class UserGroupController {

    Logger logger = LoggerFactory.getLogger(UserGroupController.class);

    @Autowired
    private UserGroupService userGroupService;

    @RequestMapping("/getConcerned")
    public List<UserGroup> getConcerned(HttpServletRequest request){
        logger.info("getConcerned...");
        String userId = request.getParameter("userId");
        List<UserGroup> list = new ArrayList<>();
        if(!StringUtils.isEmpty(userId)){

            list =   userGroupService.getConcerned(Integer.parseInt(userId));
        }

        return list;
    }
}
