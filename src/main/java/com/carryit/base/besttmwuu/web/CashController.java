package com.carryit.base.besttmwuu.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/cashLogin")
public class CashController {

    Logger logger = LoggerFactory.getLogger(CashController.class);

    @RequestMapping(value = "/login")
    public String cashLogin() {
        logger.info("=====");
        return "login";

    }
}
