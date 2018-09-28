package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.entity.Order;
import com.carryit.base.besttmwuu.entity.Product;
import com.carryit.base.besttmwuu.service.OrderService;
import com.carryit.base.besttmwuu.service.ProductService;
import com.carryit.base.besttmwuu.service.WxPayService;
import com.util.PayCommonUtil;
import com.util.PropertyUtil;
import com.util.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Service("wxPayService")
public class WxPayServiceImpl implements WxPayService{

    Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;


    @Override
    public JSONObject wxPay(String json) throws Exception{

        JSONObject jo = new JSONObject();




        /*
        应用ID	appid
        商户号	mch_id
        随机字符串	nonce_str
        签名	sign
        商品描述	body
        商户订单号	out_trade_no
        总金额	total_fee
        终端IP	spbill_create_ip
        通知地址	notify_url
        交易类型	trade_type
         */

        SortedMap<Object, Object> parameters = PayCommonUtil.getWXPrePayID(); // 获取预付单，此处已做封装，需要工具类

//        TravelFly travelFly = new TravelFly(); // 商品对象
//        travelFly.setId(orders.getProductId());
//        travelFly = travelFlyMapper.selectById(travelFly);
//        travelFly.setBusinesser(businesserMapper.selectByPrimaryKey(travelFly.getBusinesserId()));
//        orders.setTravelFly(travelFly);
//        parameters.put("body", "xxx产品-" + travelFly.getProductName());

        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            //校验授权信息
            if (!parmJo.containsKey("remoteAddrIP")) {
                jo.put("code",400);
                jo.put("msg","参数异常");
                jo.put("data","");
                return jo;
            }
            if(!parmJo.containsKey("productId")){ //商品ID
                jo.put("code",400);
                jo.put("msg","参数异常");
                jo.put("data","");
                return jo;
            }
            if(!parmJo.containsKey("productNum")){ //商品数量
                jo.put("code",400);
                jo.put("msg","参数异常");
                jo.put("data","");
                return jo;
            }

            //生成商品订单
            Product product = productService.findById(Integer.valueOf(parmJo.getString("productId")));

            Order order = new Order();
            order.setOrdersn(System.currentTimeMillis() + PropertyUtil.random() + ""); //订单号
            order.setPrice(product.getPrice() * Long.valueOf(parmJo.getString("productNum"))); //订单价格
            order.setStatus(2); //待付款
            order.setUid(Integer.valueOf(parmJo.getString("uid"))); //下单用户
            order.setPaytype(2); //在线支付
            order.setCreatetime(new Date().getTime());//创建时间


            orderService.save(order);
            parameters.put("notify_url",PropertyUtil.getProperty("wxpay.notifyurl"));//通知地址
            parameters.put("body","小马UU-"+product.getLevelName()); //商品描述
            parameters.put("out_trade_no",  order.getOrdersn()); // 订单id这里我的订单id生成规则是订单id+时间
            parameters.put("spbill_create_ip", parmJo.getString("remoteAddrIP"));
            parameters.put("total_fee", order.getPrice()*100); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100
        }else{
            jo.put("code",400);
            jo.put("msg","参数异常");
            jo.put("data","");
            return jo;
        }


        // 设置签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        // 封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters); // 获取xml结果
        logger.debug("封装请求参数是：" + requestXML);
        // 调用统一下单接口
        String result = PayCommonUtil.httpsRequest(PropertyUtil.getProperty("wxpay.payUrl"), "POST",requestXML);
        logger.debug("调用统一下单接口：" + result);
        SortedMap<Object, Object> parMap = PayCommonUtil.startWXPay(result);
        logger.debug("最终的map是：" + parMap.toString());
        if (parMap != null)
        {
            jo.put("code",200);
            jo.put("msg","SUCCESS");
            jo.put("data",parameters);
            return jo;

        } else
        {
            jo.put("code",-999);
            jo.put("msg","支付出现异常，请稍后重试!");
            jo.put("data","");
            return jo;
        }
    }
}
