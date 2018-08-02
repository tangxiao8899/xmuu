package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.Circles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/circle")
public class CirclesController {

    @RequestMapping("/getCircles")
    public List<Circles> getCircles(){
        List<Circles> circles = new ArrayList<>();
        return circles;
    }

}
