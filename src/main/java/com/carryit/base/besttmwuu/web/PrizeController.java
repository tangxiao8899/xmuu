package com.carryit.base.besttmwuu.web;

import com.carryit.base.besttmwuu.entity.Prize;
import com.carryit.base.besttmwuu.service.PrizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/prize")
public class PrizeController {

    @Autowired
    private PrizeService prizeService;

    @RequestMapping(value = "/shakePrize", method = {RequestMethod.POST, RequestMethod.GET})
    public Object shakePrize(@RequestBody(required = false) String userId) {
        Map<String, Object> res = new HashMap<>();
        Prize prize = processShake();
        if (prize != null) {
            res.put("isSuccess", true);
            res.put("prizeName", prize.getName());
            res.put("prizePhone", prize.getPhone());
        } else {
            res.put("isSuccess", false);
            res.put("prizeName", "摇奖完毕");
            res.put("prizePhone", 1);
        }
        return res;
    }

    private Prize processShake() {
        List<Prize> prizes;
        Random rand = new Random();
        if (ContextData.getAllSize() == 0) {
            if (ContextData.getSize() == 0) {
                int size = 0;
                prizes = prizeService.getAllPrize();
                for (Prize p : prizes) {
                    ContextData.add(p);
                    size += p.getNumber();
                }
                ContextData.setAllSize(size);
            }else {
                for (Prize p: ContextData.getAll()){
                    prizeService.updatePrizeById(p);
                }
                ContextData.removeAll();
                return null;
            }
        }
        int ri = rand.nextInt((ContextData.getSize()));
        Prize rp = ContextData.getIdx(ri);
        if (rp.getNumber() > 0) {
            rp.setNumber(rp.getNumber() - 1);
            return rp;
        } else {
            return new Prize();
        }
    }

}

