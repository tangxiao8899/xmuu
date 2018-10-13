package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.carryit.base.besttmwuu.entity.Prize;
import com.carryit.base.besttmwuu.entity.UserPrize;
import com.carryit.base.besttmwuu.service.PrizeService;
import com.carryit.base.besttmwuu.service.UserPrizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/prize")
public class PrizeController extends BaseController {

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private UserPrizeService userPrizeService;

    @RequestMapping(value = "/shakePrize", method = {RequestMethod.POST, RequestMethod.GET},
            produces = "application/json;charset=UTF-8")
    public JSONObject shakePrize(@RequestBody(required = false) String userId) {
        return callHttpReqTask(userId, 0);
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
            } else {
                for (Prize p : ContextData.getAll()) {
                    prizeService.updatePrizeById(p);
                }
                ContextData.removeAll();
                ContextData.removeShakedUid();
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

    @Override
    public JSONObject runTask(String userId, int cmd) {
        try {
            Map<String, Object> res = new HashMap<>();
            if (ContextData.isShaked(userId)) {
                res.put("isSuccess", true);
                res.put("prizeName", "你已经摇过奖");
                res.put("prizePhone", null);
                res.put("uid", userId);
                return doObjResp(res);
            }
            Prize prize = processShake();
            if (prize != null) {
                res.put("isSuccess", true);
                res.put("prizeName", prize.getName());
                res.put("prizePhone", prize.getPhone());
                res.put("uid", userId);
                if (prize.getName() != null) {
                    ContextData.addShakedUid(userId);
                    UserPrize userPrize = new UserPrize();
                    userPrize.setPid(prize.getId());
                    userPrize.setUid(userId);
                    userPrizeService.addUserPrize(userPrize);
                }
            } else {
                res.put("isSuccess", false);
                res.put("prizeName", "摇奖完毕");
                res.put("prizePhone", 1);
                res.put("uid", userId);
            }
            return doObjResp(res);
        } catch (Exception e) {
            e.printStackTrace();
            return faild("失败~", false);
        }
    }
}

