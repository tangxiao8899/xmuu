package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.entity.ImsUserCapitalFlowEntity;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.Order;
import com.carryit.base.besttmwuu.entity.Product;
import com.carryit.base.besttmwuu.service.*;
import com.util.PayCommonUtil;
import com.util.PropertyUtil;
import com.util.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    MemberService memberService;

    @Autowired
    ImsUserCapitalFlowService imsUserCapitalFlowService;


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

        SortedMap<Object, Object> parameters = PayCommonUtil.getWXPrePayID("wxpay.notifyurl"); // 获取预付单，此处已做封装，需要工具类

//        TravelFly travelFly = new TravelFly(); // 商品对象
//        travelFly.setId(orders.getProductId());
//        travelFly = travelFlyMapper.selectById(travelFly);
//        travelFly.setBusinesser(businesserMapper.selectByPrimaryKey(travelFly.getBusinesserId()));
//        orders.setTravelFly(travelFly);
//        parameters.put("body", "xxx产品-" + travelFly.getProductName());

        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            //校验授权信息

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

            if(StringUtils.isEmpty(product)){
                jo.put("code",404);
                jo.put("msg","未找到该产品");
                jo.put("data","");
                return jo;
            }

            Order order = new Order();
            order.setOrdersn(System.currentTimeMillis() + PropertyUtil.random() + ""); //订单号
            order.setPrice(product.getPrice() * Long.valueOf(parmJo.getString("productNum"))); //订单价格
            order.setStatus(2); //待付款
            order.setUid(Integer.valueOf(parmJo.getString("uid"))); //下单用户
            order.setPaytype(2); //在线支付
            order.setCreatetime(new Date().getTime());//创建时间


            orderService.save(order);
//            parameters.put("notify_url",PropertyUtil.getProperty("wxpay.notifyurl"));//通知地址
            parameters.put("body","小马UU-"+product.getLevelName()); //商品描述
            parameters.put("out_trade_no",  order.getOrdersn()); // 订单id这里我的订单id生成规则是订单id+时间
            parameters.put("spbill_create_ip", PropertyUtil.getIp());
            parameters.put("total_fee", order.getPrice()*100); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100
        }else{
            jo.put("code",400);
            jo.put("msg","参数异常");
            jo.put("data","");
            return jo;
        }


        // 设置签名


//        String sign = PayCommonUtil.wxSignature(parameters,PropertyUtil.getProperty("wxpay.key"));

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
            jo.put("data",parMap);
            return jo;

        } else
        {
            jo.put("code",-999);
            jo.put("msg","支付出现异常，请稍后重试!");
            jo.put("data","");
            return jo;
        }
    }

    @Override
    public JSONObject wxRecharge(String json) throws Exception {
        JSONObject jo = new JSONObject();
        SortedMap<Object, Object> parameters = PayCommonUtil.getWXPrePayID("wxpay.rechargeNotifyUrl"); // 获取预付单，此处已做封装，需要工具类

        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            //校验授权信息

            if(!parmJo.containsKey("uid")){ //用户ID
                jo.put("code",400);
                jo.put("msg","参数异常");
                jo.put("data","");
                return jo;
            }
            if(!parmJo.containsKey("money")){ //充值金额
                jo.put("code",400);
                jo.put("msg","参数异常");
                jo.put("data","");
                return jo;
            }
            if(!parmJo.containsKey("type")){ //充值类型
                jo.put("code",400);
                jo.put("msg","参数异常");
                jo.put("data","");
                return jo;
            }

            Order order = new Order();
            order.setOrdersn(System.currentTimeMillis() + PropertyUtil.random() + ""); //订单号
            order.setPrice(Long.valueOf(parmJo.getString("money"))); //订单价格
            order.setStatus(2); //待付款
            order.setUid(Integer.valueOf(parmJo.getString("uid"))); //下单用户
            order.setPaytype(2); //在线支付
            order.setCreatetime(new Date().getTime());//创建时间


            orderService.save(order);

            parameters.put("body","小马UU-用户充值"); //商品描述
            parameters.put("out_trade_no", parmJo.getString("uid") + "_" +parmJo.getString("type")+"_" + System.currentTimeMillis()); // 订单id这里我的订单id生成规则是uid+充值类型+时间
            parameters.put("spbill_create_ip", PropertyUtil.getIp());
            parameters.put("total_fee", Long.valueOf(parmJo.getString("money")) *100); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100
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
            jo.put("data",parMap);
            return jo;

        } else
        {
            jo.put("code",-999);
            jo.put("msg","支付出现异常，请稍后重试!");
            jo.put("data","");
            return jo;
        }
    }

    @Override
    public JSONObject wxReward(String json) throws Exception {
        JSONObject jo = new JSONObject();
        SortedMap<Object, Object> parameters = PayCommonUtil.getWXPrePayID("wxpay.rewardNotifyUrl"); // 获取预付单，此处已做封装，需要工具类

        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            //校验授权信息

            if(!parmJo.containsKey("fuid")){ //打赏用户ID
                jo.put("code",400);
                jo.put("msg","参数异常");
                jo.put("data","");
                return jo;
            }
            if(!parmJo.containsKey("tuid")){ //被打赏用户ID
                jo.put("code",400);
                jo.put("msg","参数异常");
                jo.put("data","");
                return jo;
            }
            if(!parmJo.containsKey("money")){ //打赏金额
                jo.put("code",400);
                jo.put("msg","参数异常");
                jo.put("data","");
                return jo;
            }

            //打赏
            //1、检查账户余额是否足够打赏
            Member m = memberService.getMemberById(Integer.valueOf(parmJo.getString("fuid")));
            if(StringUtils.isEmpty(m)){
                jo.put("code",404);
                jo.put("msg","打赏账户不存在");
                jo.put("data","");
                return jo;
            }else{
                float f = m.getCredit2(); //账户余额
                if(f < Float.valueOf(parmJo.getString("money"))){
                    jo.put("code",400);
                    jo.put("msg","打赏账户余额不足，请先充值");
                    jo.put("data","");
                    return jo;
                }
            }

            //2、打赏下单
            Order order = new Order();
            order.setOrdersn(System.currentTimeMillis() + PropertyUtil.random() + ""); //订单号
            order.setPrice(Long.valueOf(parmJo.getString("money"))); //订单价格
            order.setStatus(2); //待付款
            order.setUid(Integer.valueOf(parmJo.getString("fuid"))); //下单用户
            order.setPaytype(2); //在线支付
            order.setCreatetime(new Date().getTime());//创建时间


            parameters.put("body","小马UU-用户打赏"); //商品描述
            parameters.put("out_trade_no", parmJo.getString("fuid") + "_" +parmJo.getString("tuid")+"_" + System.currentTimeMillis()); // 订单id这里我的订单id生成规则是uid+充值类型+时间
            parameters.put("spbill_create_ip", PropertyUtil.getIp());
            parameters.put("total_fee", Long.valueOf(parmJo.getString("money")) *100); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100
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
            jo.put("data",parMap);
            return jo;

        } else
        {
            jo.put("code",-999);
            jo.put("msg","支付出现异常，请稍后重试!");
            jo.put("data","");
            return jo;
        }
    }

    @Override
    @Transactional
    public void updateRechargeInfo(String out_trade_no, String total_fee) {
        Order order = new Order();
        order.setOrdersn(out_trade_no);
        order.setStatus(3); //付款成功

        //用户ID
        String  uid = out_trade_no.split("_")[0];
        //充值类型
        String type = out_trade_no.split("_")[1];

        //支付成功，更新订单状态
        orderService.update(order);
        //查询Member表有无该用户
        Member member = memberService.getMemberById(Integer.valueOf(uid));
        if(!StringUtils.isEmpty(member)){
            float Credit = 0f;
            //更新后的余额
            if("Y".equals(type)){
                Credit  =   member.getCredit2() + Float.valueOf(total_fee);
            }else{
                Credit =   member.getCredit2() - Float.valueOf(total_fee);
            }

            //更新用户账户情况
            memberService.updateMemberByUid(Integer.valueOf(uid),Credit);
        }

        //记录资金流水
        ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
        entity.setUid(Integer.valueOf(uid));
        entity.setPrice(Long.valueOf(total_fee)*100); //记录单位为分
        entity.setSource(0); //充值
        entity.setType(0);

        imsUserCapitalFlowService.save(entity);
    }

    @Override
    @Transactional
    public void updateRewardInfo(String out_trade_no, String total_fee) {
        //TODO 待后期优化

       //更新订单状态
        Order order = new Order();
        order.setOrdersn(out_trade_no);
        order.setStatus(3); //付款成功
        //支付成功，更新订单状态
        orderService.update(order);


        //打赏用户ID
        String  fuid = out_trade_no.split("_")[0];
        //被打赏用户ID
        String tuid = out_trade_no.split("_")[1];

        //查询Member表账户信息
        Member fmember = memberService.getMemberById(Integer.valueOf(fuid));
        Member tmember = memberService.getMemberById(Integer.valueOf(tuid));

        float Creditf = fmember.getCredit2()  - Float.valueOf(total_fee);
        float Creditt = tmember.getCredit2()  + Float.valueOf(total_fee);

        //更新用户账户情况
        memberService.updateMemberByUid(Integer.valueOf(fuid),Creditf);
        memberService.updateMemberByUid(Integer.valueOf(tuid),Creditt);

        //记录资金流水
        ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
        entity.setUid(Integer.valueOf(fuid));
        entity.setPrice(Long.valueOf(total_fee)*100); //记录单位为分
        entity.setSource(0); //充值
        entity.setType(1); //支出

        imsUserCapitalFlowService.save(entity);


        ImsUserCapitalFlowEntity entity2 = new ImsUserCapitalFlowEntity();
        entity2.setUid(Integer.valueOf(tuid));
        entity2.setPrice(Long.valueOf(total_fee)*100); //记录单位为分
        entity2.setSource(1); //打赏
        entity2.setType(0); //收入

        imsUserCapitalFlowService.save(entity2);
    }


}
