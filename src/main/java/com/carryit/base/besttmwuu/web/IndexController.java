package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.Board;
import com.carryit.base.besttmwuu.entity.Circles;
import com.carryit.base.besttmwuu.entity.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/onTheList")
    public UserDTO onTheList() {

        return null;
    }
}
