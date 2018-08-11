package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.UserDTO;
import com.carryit.base.besttmwuu.service.WealthService;
import com.carryit.base.besttmwuu.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private WealthService wealthService;

    @RequestMapping("/onTheList")
    public List<UserDTO> onTheList() {
        //获取当天零点的时间
        String todayDateString = DateUtil.getToDateString(DateUtil.PATTERN_DATE);
        String startTime = todayDateString+" 00:00:00";
        //获取当天24点的时间
        String endTime = todayDateString+" 23:59:59";
       List<UserDTO> userDTOList = wealthService.onTheList(startTime,endTime);
        return null;
    }

}
