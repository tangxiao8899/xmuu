package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.ResultPojo;
import com.carryit.base.besttmwuu.entity.*;
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
public class WxPayServiceImpl implements WxPayService {

    Logger logger = LoggerFactory.getLogger(WxPayServiceImpl.class);

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    MemberService memberService;

    @Autowired
    ImsUserCapitalFlowService imsUserCapitalFlowService;

    private static final String UUqz = "0";//UU圈主
    private static final String Fqz = "1";//副圈主
    private static final String UCgly = "2";//UU管理员


    @Override
    public JSONObject wxPay(String json) throws Exception {

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

            if (!parmJo.containsKey("productId")) { //商品ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("productNum")) { //商品数量
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("bid")) { //圈子id
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("uid")) { //圈子id
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }

            //生成商品订单
            Product product = productService.findById(Integer.valueOf(parmJo.getString("productId")));

            if (StringUtils.isEmpty(product)) {
                jo.put("code", 404);
                jo.put("msg", "未找到该产品");
                jo.put("data", null);
                return jo;
            }
            if (StringUtils.isEmpty(product.getLevel())) {
                jo.put("code", 404);
                jo.put("msg", "未找到该产品");
                jo.put("data", null);
                return jo;
            }

            //用户id,查询会员信息
            Member me = memberService.getMemberById(parmJo.getInteger("uid"));


            if (me != null && me.getZhuquanzi() != 0) {
                //说明已经是会员,购买过圈子
                if (Integer.valueOf(parmJo.getString("productId")) >= Integer.valueOf(me.getLevel())) {
                    //说明买过同等级或者小于的会员,不能购买
                    jo.put("code", 404);
                    jo.put("msg", "您是" + me.getLevelName() + ",不能购买此会员");
                    jo.put("data", null);
                    return jo;
                }
            }

            //根据圈子id,职位level查询member表中该圈子有多少职位
            int orderCount = memberService.getMemberByUIdAndLevel(parmJo.getInteger("bid"), product.getLevel());
            if (UUqz.equals(product.getLevel())) {
                if (orderCount >= 1) {
                    jo.put("code", 404);
                    jo.put("msg", "圈主名额已满");
                    jo.put("data", null);
                    return jo;
                }
            } else if (Fqz.equals(product.getLevel())) {
                if (orderCount >= 10) {
                    jo.put("code", 404);
                    jo.put("msg", "副圈主名额已满");
                    jo.put("data", null);
                    return jo;
                }
            } else if (UCgly.equals(product.getLevel())) {
                if (orderCount >= 50) {
                    jo.put("code", 404);
                    jo.put("msg", "管理员名额已满");
                    jo.put("data", null);
                    return jo;
                }
            }


            Order order = new Order();
            order.setOrdersn(System.currentTimeMillis() + PropertyUtil.random() + ""); //订单号
            order.setPrice(product.getPrice() * Double.valueOf(parmJo.getString("productNum"))); //订单价格
            order.setStatus(2); //待付款
            order.setUid(Integer.valueOf(parmJo.getString("uid"))); //下单用户
            order.setPaytype(2); //在线支付
            order.setBid(Integer.valueOf(parmJo.getString("bid")));
            order.setLevel(product.getLevel());
            order.setCreatetime(new Date().getTime());//创建时间


            orderService.save(order);
//            parameters.put("notify_url",PropertyUtil.getProperty("wxpay.notifyurl"));//通知地址
            parameters.put("body", "小马UU-" + product.getLevelName()); //商品描述
            parameters.put("out_trade_no", order.getOrdersn()); // 订单id这里我的订单id生成规则是订单id+时间
            parameters.put("spbill_create_ip", PropertyUtil.getIp());
            parameters.put("total_fee", Math.round(order.getPrice() * 100)); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100
        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
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
        String result = PayCommonUtil.httpsRequest(PropertyUtil.getProperty("wxpay.payUrl"), "POST", requestXML);
        logger.debug("调用统一下单接口：" + result);
        SortedMap<Object, Object> parMap = PayCommonUtil.startWXPay(result);
        logger.debug("最终的map是：" + parMap.toString());
        if (parMap != null) {
            jo.put("code", 200);
            jo.put("msg", "SUCCESS");
            jo.put("data", parMap);
            return jo;

        } else {
            jo.put("code", -999);
            jo.put("msg", "支付出现异常，请稍后重试!");
            jo.put("data", null);
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

            if (!parmJo.containsKey("uid")) { //用户ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("money")) { //充值金额
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("type")) { //充值类型
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }

            Order order = new Order();
            order.setOrdersn(System.currentTimeMillis() + PropertyUtil.random() + ""); //订单号
            order.setPrice(Double.valueOf(parmJo.getString("money"))); //订单价格
            order.setStatus(2); //待付款
            order.setUid(Integer.valueOf(parmJo.getString("uid"))); //下单用户
            order.setPaytype(2); //在线支付
            order.setCreatetime(new Date().getTime());//创建时间


            orderService.save(order);

            parameters.put("body", "小马UU-用户充值"); //商品描述
            parameters.put("out_trade_no", parmJo.getString("uid") + "_" + parmJo.getString("type") + "_" + System.currentTimeMillis()); // 订单id这里我的订单id生成规则是uid+充值类型+时间
            parameters.put("spbill_create_ip", PropertyUtil.getIp());
            parameters.put("total_fee", Math.round(Double.valueOf(parmJo.getString("money")) * 100)); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100
        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
            return jo;
        }


        // 设置签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        // 封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters); // 获取xml结果
        logger.debug("封装请求参数是：" + requestXML);
        // 调用统一下单接口
        String result = PayCommonUtil.httpsRequest(PropertyUtil.getProperty("wxpay.payUrl"), "POST", requestXML);
        logger.debug("调用统一下单接口：" + result);
        SortedMap<Object, Object> parMap = PayCommonUtil.startWXPay(result);
        logger.debug("最终的map是：" + parMap.toString());
        if (parMap != null) {
            jo.put("code", 200);
            jo.put("msg", "SUCCESS");
            jo.put("data", parMap);
            return jo;

        } else {
            jo.put("code", -999);
            jo.put("msg", "支付出现异常，请稍后重试!");
            jo.put("data", null);
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

            if (!parmJo.containsKey("fuid")) { //打赏用户ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("tuid")) { //被打赏用户ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("money")) { //打赏金额
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }

            //打赏
            //1、检查账户余额是否足够打赏
            Member m = memberService.getMemberById(Integer.valueOf(parmJo.getString("fuid")));
            if (StringUtils.isEmpty(m)) {
                jo.put("code", 404);
                jo.put("msg", "打赏账户不存在");
                jo.put("data", null);
                return jo;
            } else {
                float f = m.getCredit2(); //账户余额
                if (f < Float.valueOf(parmJo.getString("money"))) {
                    jo.put("code", 400);
                    jo.put("msg", "打赏账户余额不足，请先充值");
                    jo.put("data", null);
                    return jo;
                }
            }

            //2、打赏下单
            Order order = new Order();
            order.setOrdersn(System.currentTimeMillis() + PropertyUtil.random() + ""); //订单号
            order.setPrice(Double.valueOf(parmJo.getString("money"))); //订单价格
            order.setStatus(2); //待付款
            order.setUid(Integer.valueOf(parmJo.getString("fuid"))); //下单用户
            order.setPaytype(2); //在线支付
            order.setCreatetime(new Date().getTime());//创建时间
            order.setPaysource(1);


            parameters.put("body", "小马UU-用户打赏"); //商品描述
            parameters.put("out_trade_no", parmJo.getString("fuid") + "_" + parmJo.getString("tuid") + "_" + System.currentTimeMillis()); // 订单id这里我的订单id生成规则是uid+充值类型+时间
            parameters.put("spbill_create_ip", PropertyUtil.getIp());
            parameters.put("total_fee", Math.round(Double.valueOf(parmJo.getString("money")) * 100)); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100
        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
            return jo;
        }


        // 设置签名
        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        // 封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters); // 获取xml结果
        logger.debug("封装请求参数是：" + requestXML);
        // 调用统一下单接口
        String result = PayCommonUtil.httpsRequest(PropertyUtil.getProperty("wxpay.payUrl"), "POST", requestXML);
        logger.debug("调用统一下单接口：" + result);
        SortedMap<Object, Object> parMap = PayCommonUtil.startWXPay(result);
        logger.debug("最终的map是：" + parMap.toString());
        if (parMap != null) {
            jo.put("code", 200);
            jo.put("msg", "SUCCESS");
            jo.put("data", parMap);
            return jo;

        } else {
            jo.put("code", -999);
            jo.put("msg", "支付出现异常，请稍后重试!");
            jo.put("data", null);
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
        String uid = out_trade_no.split("_")[0];
        //充值类型
        String type = out_trade_no.split("_")[1];

        //支付成功，更新订单状态
        orderService.update(order);
        //查询Member表有无该用户
        Member member = memberService.getMemberById(Integer.valueOf(uid));
        if (!StringUtils.isEmpty(member)) {
            float Credit = 0f;
            //更新后的余额
            if ("Y".equals(type)) {
                Credit = member.getCredit2() + Float.valueOf(total_fee);
            } else {
                Credit = member.getCredit2() - Float.valueOf(total_fee);
            }

            //更新用户账户情况
            memberService.updateMemberByUid(Integer.valueOf(uid), Credit);
        }

        //记录资金流水
        ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
        entity.setUid(Integer.valueOf(uid));
        entity.setPrice(Long.valueOf(total_fee) * 100); //记录单位为分
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
        String fuid = out_trade_no.split("_")[0];
        //被打赏用户ID
        String tuid = out_trade_no.split("_")[1];

        //查询Member表账户信息
        Member fmember = memberService.getMemberById(Integer.valueOf(fuid));
        Member tmember = memberService.getMemberById(Integer.valueOf(tuid));

        float Creditf = fmember.getCredit2() - Float.valueOf(total_fee);
        float Creditt = tmember.getCredit2() + Float.valueOf(total_fee);

        //更新用户账户情况
        memberService.updateMemberByUid(Integer.valueOf(fuid), Creditf);
        memberService.updateMemberByUid(Integer.valueOf(tuid), Creditt);

        //记录资金流水
        ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
        entity.setUid(Integer.valueOf(fuid));
        entity.setPrice(Long.valueOf(total_fee) * 100); //记录单位为分
        entity.setSource(0); //充值
        entity.setType(1); //支出

        imsUserCapitalFlowService.save(entity);


        ImsUserCapitalFlowEntity entity2 = new ImsUserCapitalFlowEntity();
        entity2.setUid(Integer.valueOf(tuid));
        entity2.setPrice(Long.valueOf(total_fee) * 100); //记录单位为分
        entity2.setSource(1); //打赏
        entity2.setType(0); //收入

        imsUserCapitalFlowService.save(entity2);
    }

    @Override
    public JSONObject getCash(String json) throws Exception {
        JSONObject jo = new JSONObject();
        SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            //校验授权信息

            if (!parmJo.containsKey("uid")) { //用户ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", "");
                return jo;
            }
            if (!parmJo.containsKey("money")) { //提现金额
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", "");
                return jo;
            }


            MemberData member = memberService.getMemberDataByUId(Integer.valueOf(parmJo.getString("uid")));
            if (StringUtils.isEmpty(member)) {
                jo.put("code", 404);
                jo.put("msg", "该账户不存在");
                jo.put("data", "");
                return jo;
            } else {
                double credit = member.getCredit2(); //可提现余额
                //校验提现金额是否超过可提现余额
                if (Double.valueOf(parmJo.getString("money")) > credit) {
                    jo.put("code", 400);
                    jo.put("msg", "提现金额超出可提现余额");
                    jo.put("data", "");
                    return jo;
                }
            }

            parameters.put("mch_appid", PropertyUtil.getProperty("wxpay.appid")); //账户账号appid
            parameters.put("mchid", PropertyUtil.getProperty("wxpay.mchid")); //商户号
            parameters.put("body", "小马UU-用户提现"); //商品描述
            parameters.put("nonce_str", PayCommonUtil.CreateNoncestr()); //随机字符串
            parameters.put("sign", "CNY"); //签名
            parameters.put("partner_trade_no", parmJo.getString("uid") + "_" + System.currentTimeMillis()); //商户订单号
            parameters.put("openid", member.getOpenid()); //用户openid
            parameters.put("check_name", "NO_CHECK"); //校验用户姓名选项
            parameters.put("amount", Math.round(Double.valueOf(parmJo.getString("money")) * 100)); //金额
            parameters.put("desc", "用户提现"); //企业付款备注
            parameters.put("spbill_create_ip", PropertyUtil.getIp()); //IP地址

            //往提现申请表保存一条数据


        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
            return jo;
        }

        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        // 封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters); // 获取xml结果
        logger.debug("封装请求参数是：" + requestXML);

        // 调用企业付款接口
        String result = PayCommonUtil.httpsRequest(PropertyUtil.getProperty("wxpay.merchantPay"), "POST", requestXML);
        logger.debug("调用统一下单接口：" + result);
        SortedMap<Object, Object> parMap = PayCommonUtil.startWXPay(result);
        logger.debug("最终的map是：" + parMap.toString());
        if (parMap != null) {
            jo.put("code", 200);
            jo.put("msg", "SUCCESS");
            jo.put("data", parMap);
            return jo;

        } else {
            jo.put("code", -999);
            jo.put("msg", "提现出现异常，请稍后重试!");
            jo.put("data", null);
            return jo;
        }
    }

    public JSONObject wxEntered(String json) throws Exception {
        JSONObject jo = new JSONObject();
        SortedMap<Object, Object> parameters = PayCommonUtil.getWXPrePayID("wxpay.notifyurl"); // 获取预付单，此处已做封装，需要工具类
        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            //校验授权信息
            if (!parmJo.containsKey("productId")) { //商品ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("productNum")) { //商品数量
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("bid")) { //圈子id
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("uid")) { //用户id
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            //生成商品订单
            Product product = productService.findById(Integer.valueOf(parmJo.getString("productId")));

            if (StringUtils.isEmpty(product)) {
                jo.put("code", 404);
                jo.put("msg", "未找到该产品");
                jo.put("data", null);
                return jo;
            }
            if (StringUtils.isEmpty(product.getLevel())) {
                jo.put("code", 404);
                jo.put("msg", "未找到该产品");
                jo.put("data", null);
                return jo;
            }


            Order order = new Order();
            order.setOrdersn(System.currentTimeMillis() + PropertyUtil.random() + ""); //订单号
            order.setPrice(product.getPrice() * Double.valueOf(parmJo.getString("productNum"))); //订单价格
            order.setStatus(2); //待付款
            order.setUid(Integer.valueOf(parmJo.getString("uid"))); //下单用户
            order.setPaytype(2); //在线支付
            order.setBid(Integer.valueOf(parmJo.getString("bid")));
            order.setLevel(product.getLevel());
            order.setCreatetime(new Date().getTime());//创建时间
            order.setPaysource(2);


            orderService.save(order);
//            parameters.put("notify_url",PropertyUtil.getProperty("wxpay.notifyurl"));//通知地址
            parameters.put("body", "小马UU-" + product.getLevelName()); //商品描述
            parameters.put("out_trade_no", order.getOrdersn()); // 订单id这里我的订单id生成规则是订单id+时间
            parameters.put("spbill_create_ip", PropertyUtil.getIp());
            parameters.put("total_fee", Math.round(order.getPrice() * 100)); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100
        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
            return jo;
        }


        // 封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters); // 获取xml结果
        logger.debug("封装请求参数是：" + requestXML);
        // 调用统一下单接口
        String result = PayCommonUtil.httpsRequest(PropertyUtil.getProperty("wxpay.payUrl"), "POST", requestXML);
        logger.debug("调用统一下单接口：" + result);
        SortedMap<Object, Object> parMap = PayCommonUtil.startWXPay(result);
        logger.debug("最终的map是：" + parMap.toString());
        if (parMap != null) {
            jo.put("code", 200);
            jo.put("msg", "SUCCESS");
            jo.put("data", parMap);
            return jo;

        } else {
            jo.put("code", -999);


            jo.put("msg", "支付出现异常，请稍后重试!");

            jo.put("data", null);
            return jo;
        }
    }
}
