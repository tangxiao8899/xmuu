package com.carryit.base.besttmwuu.web;

import ch.qos.logback.core.joran.spi.XMLUtil;
import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.WxPayReq;
import com.carryit.base.besttmwuu.service.WxPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/wx")
public class WxPayControllrt extends BaseController {

    Logger logger = LoggerFactory.getLogger(WxPayControllrt.class);

    public static final int WX_PAY = 0;

    @Resource
    WxPayService wxPayService;


    /**
     * 获取微信预付单
     * @param json
     * @return
     */
    @RequestMapping(value = "/wxPay", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getWxPay(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }


    @Override
    public JSONObject runTask(String json, int cmd) {
        switch (cmd){
            case 0:
                WxPayReq req = p(json, WxPayReq.class);
                if (req != null) {
                    if (req.totalFee <= 0) // 防止抓包修改订单金额造成损失
                        return doObjResp(false,-999,"付款金额错误!");
                    else{
                        try{
                            return wxPayService.wxPay(this.request.getRemoteAddr(),String.valueOf(req.totalFee));
                        } catch (Exception e){
                            e.printStackTrace();
                            logger.error(e.getMessage());
                            return doObjResp(false,-999,"程序异常!");
                        }
                    }
                } else {
                    return faild("请求参数异常~", false);
                }
        }
        return null;
    }
}
