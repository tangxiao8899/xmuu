package com.carryit.base.besttmwuu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carryit.base.besttmwuu.entity.*;
import com.carryit.base.besttmwuu.service.*;
import com.util.PayCommonUtil;
import com.util.PropertyUtil;
import com.util.XMLUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
    ActivityService activityService;

    @Autowired
    CashApplyService cashApplyService;

    @Autowired
    ImsUserCapitalFlowService imsUserCapitalFlowService;

    private static final String UUqz = "0";//UU圈主
    private static final String CWQZ = "1";//常务圈主
    private static final String Fqz = "2";//副圈主
    private static final String UCgly = "3";//UU管理员
    private static final String GVIP = "4";//高级VIP
    private static final String MVIP = "5";//梦想VIP


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
            if (!parmJo.containsKey("productNum")) { //商品价格
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
                if (Integer.valueOf(product.getLevel()) >= Integer.valueOf(me.getLevel())) {
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
            }else if (CWQZ.equals(product.getLevel())) {
                if (orderCount >= 1) {
                    jo.put("code", 404);
                    jo.put("msg", "常务圈主名额已满");
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
            order.setPaysource("3");
            orderService.save(order);
            SortedMap<Object, Object> parameters = PayCommonUtil.getWXPrePayID("wxpay.notifyurl"); // 获取预付单，此处已做封装，需要工具类

/*          //根据UID查找iCode
            String iCode=memberService.getICodeByUid(parmJo.getInteger("uid"));
            //根据iCode查找uid直推人的level
            if (iCode!=null){
                Member member=memberService.getLevelByICode(iCode);
                if(MVIP.equals(product.getLevel())){
                    if (member.getLevel().equals(MVIP)){
                        //直接梦想VIP价格30%加给直推人
                        //逻辑判断更新数据库余额字段方法没写

                    }else if (member.getLevel().equals(GVIP)){
                        //高级VIP的40%价格加给直推人
                    }else if (member.getLevel().equals(UCgly)){
                        //管理员的20%价格加给直推人
                    }else if (member.getLevel().equals(Fqz)){
                        //副圈主的20%价格加给直推人
                    }else if (member.getLevel().equals(CWQZ)){
                        //常务圈主的10%价格加给直推人
                    }else if (member.getLevel().equals(UUqz)){
                        //圈主的10%价格加给直推人
                    }

                }else if (GVIP.equals(product.getLevel())){
                    if (member.getLevel().equals(MVIP)){
                        //直接梦想VIP价格30%加给直推人

                    }else if (member.getLevel().equals(GVIP)){
                        //高级VIP的40%价格加给直推人
                    }else if (member.getLevel().equals(UCgly)){
                        //管理员的40%价格加给直推人
                    }else if (member.getLevel().equals(Fqz)){
                        //副圈主的40%价格加给直推人
                    }else if (member.getLevel().equals(CWQZ)){
                        //常务圈主的20%价格加给直推人
                    }else if (member.getLevel().equals(UUqz)){
                        //圈主的20%价格加给直推人
                    }
                }else if (UCgly.equals(product.getLevel())){
                    if (member.getLevel().equals(MVIP)){
                        //直接梦想VIP价格30%加给直推人

                    }else if (member.getLevel().equals(GVIP)){
                        //高级VIP的40%价格加给直推人
                    }else if (member.getLevel().equals(UCgly)){
                        //高级VIP的55%价格加给直推人
                    }else if (member.getLevel().equals(Fqz)){
                        //高级VIP的55%价格加给直推人
                    }else if (member.getLevel().equals(CWQZ)){
                        //常务圈主的22%价格加给直推人
                    }else if (member.getLevel().equals(UUqz)){
                        //圈主的22%价格加给直推人
                    }
                }else if (Fqz.equals(product.getLevel())){
                    if (member.getLevel().equals(MVIP)){
                        //直接梦想VIP价格30%加给直推人

                    }else if (member.getLevel().equals(GVIP)){
                        //高级VIP的40%价格加给直推人
                    }else if (member.getLevel().equals(UCgly)){
                        //高级VIP的65%价格加给直推人
                    }else if (member.getLevel().equals(Fqz)){
                        //高级VIP的65%价格加给直推人
                    }else if (member.getLevel().equals(CWQZ)){
                        //常务圈主的22%价格加给直推人
                    }else if (member.getLevel().equals(UUqz)){
                        //圈主的22%价格加给直推人
                    }
                }else if (CWQZ.equals(product.getLevel())){
                    if (member.getLevel().equals(MVIP)){
                        //直接梦想VIP价格30%加给直推人

                    }else if (member.getLevel().equals(GVIP)){
                        //高级VIP的40%价格加给直推人
                    }else if (member.getLevel().equals(UCgly)){
                        //高级VIP的70%价格加给直推人
                    }else if (member.getLevel().equals(Fqz)){
                        //高级VIP的70%价格加给直推人
                    }else if (member.getLevel().equals(CWQZ)){
                        //常务圈主的23%价格加给直推人
                    }else if (member.getLevel().equals(UUqz)){
                        //圈主的23%价格加给直推人
                    }
                }else if (UUqz.equals(product.getLevel())){
                    if (member.getLevel().equals(MVIP)){
                        //直接梦想VIP价格30%加给直推人

                    }else if (member.getLevel().equals(GVIP)){
                        //高级VIP的40%价格加给直推人
                    }else if (member.getLevel().equals(UCgly)){
                        //高级VIP的75%价格加给直推人
                    }else if (member.getLevel().equals(Fqz)){
                        //高级VIP的75%价格加给直推人
                    }else if (member.getLevel().equals(CWQZ)){
                        //常务圈主的23%价格加给直推人
                    }else if (member.getLevel().equals(UUqz)){
                        //圈主的23%价格加给直推人
                    }
                }
            }
*/


            parameters.put("body", "小马UU-" + product.getLevelName()); //商品描述
            parameters.put("out_trade_no", order.getOrdersn()); // 订单id这里我的订单id生成规则是订单id+时间
            parameters.put("spbill_create_ip", PropertyUtil.getIp());
            parameters.put("total_fee", Math.round(order.getPrice() * 100)); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100

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
        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
            return jo;
        }
    }

    @Override
    public JSONObject wxRecharge(String json) throws Exception {
        JSONObject jo = new JSONObject();

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

            //生成订单号
            String ordersn = parmJo.getString("uid") + "_" + parmJo.getString("type") + "_" + System.currentTimeMillis();
            Order order = new Order();
            order.setOrdersn(ordersn); //订单号
            order.setPrice(Double.valueOf(parmJo.getString("money"))); //订单价格
            order.setStatus(2); //待付款
            order.setUid(Integer.valueOf(parmJo.getString("uid"))); //下单用户
            order.setPaytype(2); //在线支付
            order.setPaysource("4");
            order.setCreatetime(new Date().getTime());//创建时间
            orderService.save(order);

            SortedMap<Object, Object> parameters = PayCommonUtil.getWXPrePayID("wxpay.rechargeNotifyUrl"); // 获取预付单，此处已做封装，需要工具类
            parameters.put("body", "小马UU-用户充值"); //商品描述
            parameters.put("out_trade_no",ordersn); // 订单id这里我的订单id生成规则是uid+充值类型+时间
            parameters.put("spbill_create_ip", PropertyUtil.getIp());
            parameters.put("total_fee", Math.round(Double.valueOf(parmJo.getString("money")) * 100)); // 测试时，每次支付一分钱，微信支付所传的金额是以分为单位的，因此实际开发中需要x100

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


        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
            return jo;
        }

    }

    @Override
    public JSONObject wxReward(String json) {
        JSONObject jo = new JSONObject();
//        SortedMap<Object, Object> parameters = PayCommonUtil.getWXPrePayID("wxpay.rewardNotifyUrl"); // 获取预付单，此处已做封装，需要工具类
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

            if (Float.valueOf(parmJo.getString("money"))==0) { //打赏金额
                jo.put("code", 200);
                jo.put("msg", "金额不能为0");
                jo.put("data", 4);
                return jo;
            }

            if(Integer.valueOf(parmJo.getString("fuid"))==Integer.valueOf(parmJo.getString("tuid"))){
                jo.put("code", 200);
                jo.put("msg", "不能给自己打赏");
                jo.put("data", 3);
                return jo;
            }

            //打赏
            //1、检查账户余额是否足够打赏
            Member m = memberService.getMemberById(Integer.valueOf(parmJo.getString("fuid")));
            if (StringUtils.isEmpty(m)) {
                jo.put("code", 200);
                jo.put("msg", "打赏账户不存在");
                jo.put("data", 2);
                return jo;
            } else {
                float f = m.getCredit2(); //账户余额
                if (f < Float.valueOf(parmJo.getString("money"))) {
                    jo.put("code", 200);
                    jo.put("msg", "打赏账户余额不足，请先充值");
                    jo.put("data", 1);
                    return jo;
                }
            }

            //资金够，开始打赏，变更两个账户金额
            try {
                updateRewardInfo(parmJo.getString("fuid"), parmJo.getString("tuid"), parmJo.getString("money"));
                jo.put("code", 200);
                jo.put("msg", "打赏成功");
                jo.put("data", 0);
                return jo;
            }catch (Exception e){
                e.printStackTrace();
                jo.put("code", 200);
                jo.put("msg", "打赏异常，请稍后重试");
                jo.put("data", 2);
                return jo;
            }

        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
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
                Credit = member.getCredit2() + Float.valueOf(total_fee)/100;
            } else {
                Credit = member.getCredit2() - Float.valueOf(total_fee)/100;
            }

            //更新用户账户情况
            memberService.updateMemberByUid(Integer.valueOf(uid), Credit);
        }
        ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
        if ("Y".equals(type)) {
            //记录资金流水
            entity.setUid(Integer.valueOf(uid));
            entity.setPrice(Float.valueOf(total_fee)/100); //记录单位为分,/100变成元
            entity.setSource(0); //充值
            entity.setType(0);
            imsUserCapitalFlowService.save(entity);
        } else {
            entity.setUid(Integer.valueOf(uid));
            entity.setPrice(Float.valueOf(total_fee)/100); //记录单位为分,/100变成元
            entity.setSource(2); //提现
            entity.setType(1);
            imsUserCapitalFlowService.save(entity);
        }

    }

    @Override
    @Transactional
    public void updateRewardInfo(String fuid, String tuid,String total_fee) {

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
        entity.setPrice(Float.valueOf(total_fee)); //记录单位为分
        entity.setSource(1); //打赏
        entity.setType(1); //支出

        imsUserCapitalFlowService.save(entity);


        ImsUserCapitalFlowEntity entity2 = new ImsUserCapitalFlowEntity();
        entity2.setUid(Integer.valueOf(tuid));
        entity2.setPrice(Float.valueOf(total_fee)); //记录单位为分
        entity2.setSource(1); //打赏
        entity2.setType(0); //收入

        imsUserCapitalFlowService.save(entity2);
    }

    /*报名后更新用户余额.更新资金流水记录*/
    @Transactional
    public void updatewxEnteredInfo(int fuid, int tuid,float total_fee) {

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
        entity.setPrice(total_fee); //记录单位为分
        entity.setSource(3); //报名
        entity.setType(1); //支出

        imsUserCapitalFlowService.save(entity);


        ImsUserCapitalFlowEntity entity2 = new ImsUserCapitalFlowEntity();
        entity2.setUid(Integer.valueOf(tuid));
        entity2.setPrice(total_fee); //记录单位为分
        entity2.setSource(3); //报名
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
                jo.put("code", 200);
                jo.put("msg", "参数异常");
                jo.put("data", 4);
                return jo;
            }
            if (!parmJo.containsKey("money")) { //提现金额
                jo.put("code", 200);
                jo.put("msg", "参数异常");
                jo.put("data", 4);
                return jo;
            }


            MemberData member = memberService.getMemberDataByUId(Integer.valueOf(parmJo.getString("uid")));
            if (StringUtils.isEmpty(member)) {
                jo.put("code", 200);
                jo.put("msg", "该账户不存在");
                jo.put("data", 2);
                return jo;
            } else {
                double credit = member.getCredit2(); //可提现余额
                //校验提现金额是否超过可提现余额
                if (Double.valueOf(parmJo.getString("money")) > credit) {
                    jo.put("code", 200);
                    jo.put("msg", "提现金额超出可提现余额");
                    jo.put("data", 1);
                    return jo;
                }
            }

            parameters.put("mch_appid", PropertyUtil.getProperty("wxpay.appid")); //账户账号appid
            parameters.put("mchid", PropertyUtil.getProperty("wxpay.mchid")); //商户号
            parameters.put("nonce_str", PayCommonUtil.CreateNoncestr()); //随机字符串
            parameters.put("openid", member.getOpenid()); //用户openid
            parameters.put("check_name", "FORCE_CHECK"); //校验用户姓名选项
            parameters.put("re_user_name", member.getRealname()); //真是姓名
            parameters.put("amount", Math.round(Double.valueOf(parmJo.getString("money")) * 100)); //金额
            parameters.put("desc", "用户提现"); //企业付款备注
            parameters.put("spbill_create_ip", PropertyUtil.getIp()); //IP地址


            //往提现申请表保存一条数据
            CashApply cashApply = new CashApply();
            cashApply.setId(cashApplyService.findMaxId()+1);
            cashApply.setOpenid(member.getOpenid());
            cashApply.setCreatetime((int)(System.currentTimeMillis()/1000));
            cashApply.setLogno("RW"+PayCommonUtil.getDateStr()+System.currentTimeMillis());
            cashApply.setMoney(BigDecimal.valueOf(Math.round(Double.valueOf(parmJo.getString("money")) * 100)));
            cashApply.setStatus(0); //提现申请
            cashApply.setTitle("余额提现");
            cashApply.setType(1);
            cashApplyService.save(cashApply);

            parameters.put("partner_trade_no", parmJo.getString("uid") + "A" +cashApply.getId()+"A"+ System.currentTimeMillis()); //商户订单号

            //记一笔提现的流水
            ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
            entity.setUid(Integer.valueOf(parmJo.getString("uid")));
            entity.setPrice(Float.valueOf(parmJo.getString("money"))); //记录单位为元
            entity.setSource(2); //提现
            entity.setType(1); //支出

            imsUserCapitalFlowService.save(entity);
        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", 4);
            return jo;
        }

        String sign = PayCommonUtil.createSign("UTF-8", parameters);
        parameters.put("sign", sign);
        // 封装请求参数结束
        String requestXML = PayCommonUtil.getRequestXml(parameters); // 获取xml结果
        logger.debug("封装请求参数是：" + requestXML);

        // 调用企业付款接口
        CloseableHttpResponse response = PayCommonUtil.Post(PropertyUtil.getProperty("wxpay.merchantPay"),requestXML , true);
        String transfersXml = EntityUtils.toString(response.getEntity(), "utf-8");
        Map<String, String> transferMap = XMLUtil.doXMLParse(transfersXml);
        if (transferMap.size() > 0) {
            String partnerTradeNo = transferMap.get("partner_trade_no");
            String uid = partnerTradeNo.split("A")[0];
            String  amount = transferMap.get("amount"); //金额

            //更新提现状态
            String _id = partnerTradeNo.split("A")[1];
            if (transferMap.get("result_code").equals("SUCCESS") && transferMap.get("return_code").equals("SUCCESS")) {
                //成功需要进行的逻辑操作，


                CashApply ca = new CashApply();
                ca.setId(Integer.valueOf(_id));
                ca.setStatus(1);
                cashApplyService.update(ca);

                //更新账户的余额信息
                Member member = memberService.getMemberById(Integer.valueOf(uid));
                if (!StringUtils.isEmpty(member)) {
                    float Credit = 0f;
                    Credit = member.getCredit2() - Float.valueOf(amount);
                    //更新用户账户情况
                    memberService.updateMemberByUid(Integer.valueOf(uid), Credit);
                }

                jo.put("code", 200);
                jo.put("msg", "SUCCESS");
                jo.put("data", 0);
                return jo;
            }else{
                CashApply ca = new CashApply();
                ca.setId(Integer.valueOf(_id));
                ca.setStatus(-1);
                cashApplyService.update(ca);
                jo.put("code", 200);
                jo.put("msg", "提现失败");
                jo.put("data", 3);
                return jo;
            }
        } else {
            jo.put("code", 200);
            jo.put("msg", "提现出现异常，请稍后重试!");
            jo.put("data", 5);
            return jo;
        }
    }



    public JSONObject wxEntered(String json) throws Exception {
        JSONObject jo = new JSONObject();
        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            //校验授权信息
            if (!parmJo.containsKey("uid")) { //用户ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("aid")) { //活动id
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("age")) {
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("name")) {
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("phone")) {
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            if (!parmJo.containsKey("sex")) {
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            //查询活动信息
            Activity act = activityService.getActivityById(parmJo.getInteger("aid"));
            Member member = memberService.getMemberById(parmJo.getInteger("uid"));
            if (act != null && member != null) {

                if (Long.parseLong(act.getEndTime()) < new Date().getTime()) {
                //活动结束
                jo.put("code", 200);
                jo.put("msg", "活动结束");
                jo.put("data", 2);
                return jo;
            }

            Boolean flag = activityService.getActivityByUIdAndAid(parmJo.getInteger("uid"), parmJo.getInteger("aid"));
            if(flag){
                jo.put("code", 200);
                jo.put("msg", "您已报名,不能重复报名");
                jo.put("data", 3);
                return jo;
            }


            //判断是否符合等级

                if(act.getUid()==parmJo.getInteger("uid")){
                    jo.put("code", 200);
                    jo.put("msg", "圈主不能报名");
                    jo.put("data", 4);
                    return jo;
                }

                if (member.getLevel().equals(act.getLevel())) {
                    ////判断人员是否满员,参加人数小于总人数
                    if(act.getPeopleNumber()!=0){
                        if (act.getJoinNumber() >= act.getPeopleNumber()) {

                            jo.put("code", 200);
                            jo.put("msg", "名额已满");
                            jo.put("data", 5);
                            return jo;
                        }
                    }

                } else if (org.apache.commons.lang3.StringUtils.isBlank(act.getLevel())) {
                    if(act.getPeopleNumber()!=0){
                        if (act.getJoinNumber() >= act.getPeopleNumber()) {
                            jo.put("code", 200);
                            jo.put("msg", "名额已满");
                            jo.put("data", 5);
                            return jo;
                        }
                    }

                }

                float f = member.getCredit2(); //账户余额
                if (f < (float)act.getCost()) {
                    jo.put("code", 200);
                    jo.put("msg", "报名账户余额不足，请先充值");
                    jo.put("data", 1);
                    return jo;
                }
                //资金够，开始报名，变更两个账户金额
                try {
                    updatewxEnteredInfo(parmJo.getInteger("uid"), act.getUid(), (float)act.getCost());
                    //新增报名
                    SignUp signUp = new SignUp();
                    signUp.setUid(parmJo.getInteger("uid"));
                    signUp.setAid(parmJo.getInteger("aid"));
                    signUp.setName(parmJo.getString("name"));
                    signUp.setPhone(parmJo.getString("phone"));
                    signUp.setAge(parmJo.getString("age"));
                    activityService.signUpRelease(signUp);
                    jo.put("code", 200);
                    jo.put("msg", "报名成功");
                    jo.put("data", 0);
                    return jo;
                }catch (Exception e){
                    e.printStackTrace();
                    jo.put("code", 400);
                    jo.put("msg", "报名失败，请稍后重试");
                    jo.put("data", null);
                    return jo;
                }
            } else {
                jo.put("code", 404);
                jo.put("msg", "未找到该活动");
                jo.put("data", null);
                return jo;
            }
        } else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
            return jo;
        }
    }

    @Override
    public JSONObject getBillsbyUid(String json) {
        JSONObject jo = new JSONObject();
        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            if (!parmJo.containsKey("uid")) { //用户ID
                jo.put("code", 400);
                jo.put("msg", "参数异常");
                jo.put("data", null);
                return jo;
            }
            List<ImsUserCapitalFlowEntity> bills = new ArrayList<>();
             bills =  imsUserCapitalFlowService.getBillsbyUid(parmJo.getInteger("uid"));
            DecimalFormat df   = new DecimalFormat("######0.00");
            if(bills!=null&&bills.size()>0){
                for (ImsUserCapitalFlowEntity bill:bills) {
                    if(bill.getType()==0){//收入
                        bill.setTypeName("收入");
                        bill.setFormatPrice("+"+bill.getFormatPrice());
                    }else if(bill.getType()==1){
                        bill.setTypeName("支出");
                        bill.setFormatPrice("-"+bill.getFormatPrice());
                    }
                    if(bill.getSource()==0){//资金来源  0：充值  1：打赏 2:提现 3:报名支付 4:直推
                        bill.setSourceName("充值");
                    }else if(bill.getSource()==1){
                        bill.setSourceName("打赏");
                    }else if(bill.getSource()==2){
                        bill.setSourceName("提现");
                    }else if(bill.getSource()==3){
                        bill.setSourceName("报名支付");
                    }else if(bill.getSource()==4){
                        bill.setSourceName("直推");
                    }
                }
            }
            jo.put("code", 200);
            jo.put("msg", "查询成功");
            jo.put("data", bills);
            return jo;

    }else {
            jo.put("code", 400);
            jo.put("msg", "参数异常");
            jo.put("data", null);
            return jo;
        }
    }

    @Override
    @Transactional
    public JSONObject manualCash(String json){
        JSONObject jo = new JSONObject();
        if (!StringUtils.isEmpty(json)) {
            JSONObject parmJo = JSON.parseObject(json);
            //校验授权信息

            if (!parmJo.containsKey("uid")) { //用户ID
                jo.put("code", 200);
                jo.put("msg", "参数异常");
                jo.put("data", 4);
                return jo;
            }
            if (!parmJo.containsKey("money")) { //提现金额
                jo.put("code", 200);
                jo.put("msg", "参数异常");
                jo.put("data", 4);
                return jo;
            }
            MemberData member = memberService.getMemberDataByUId(Integer.valueOf(parmJo.getString("uid")));
            if (StringUtils.isEmpty(member)) {
                jo.put("code", 200);
                jo.put("msg", "该账户不存在");
                jo.put("data", 2);
                return jo;
            } else {
                double credit = member.getCredit2(); //可提现余额
                //校验提现金额是否超过可提现余额
                if (Double.valueOf(parmJo.getString("money")) > credit) {
                    jo.put("code", 200);
                    jo.put("msg", "提现金额超出可提现余额");
                    jo.put("data", 1);
                    return jo;
                }
            }
            //往提现申请表保存一条数据
            CashApply cashApply = new CashApply();
            cashApply.setId(cashApplyService.findMaxId()+1);
            cashApply.setOpenid(member.getOpenid());
            cashApply.setCreatetime((int)(System.currentTimeMillis()/1000));
            cashApply.setLogno("RW"+PayCommonUtil.getDateStr()+System.currentTimeMillis());
            cashApply.setMoney(BigDecimal.valueOf(Math.round(Double.valueOf(parmJo.getString("money")))));
            cashApply.setStatus(0); //提现申请
            cashApply.setTitle("余额提现");
            cashApply.setType(1);
            cashApplyService.save(cashApply);

            //发起提现申请，直接先扣除账户余额，后台通过后，修改状态，后台拒绝，回退扣除金额
            memberService.updateMemberByUid(member.getUid(),(float) member.getCredit2() - Float.valueOf(parmJo.getString("money")));

            //记一笔提现的流水
            ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
            entity.setUid(Integer.valueOf(parmJo.getString("uid")));
            entity.setPrice(Float.valueOf(parmJo.getString("money"))); //记录单位为元
            entity.setSource(2); //提现
            entity.setType(1); //支出
            imsUserCapitalFlowService.save(entity);
        } else {
            jo.put("code", 200);
            jo.put("msg", "参数异常");
            jo.put("data", 4);
            return jo;
        }
        jo.put("code", 200);
        jo.put("msg", "SUCCESS");
        jo.put("data", 0);
        return jo;
    }
}

