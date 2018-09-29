package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.WxPayReq;
import com.carryit.base.besttmwuu.entity.Order;
import com.carryit.base.besttmwuu.service.OrderService;
import com.carryit.base.besttmwuu.service.WxPayService;
import com.util.PayCommonUtil;
import com.util.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/wx")
public class WxPayControllrt extends BaseController {

    Logger logger = LoggerFactory.getLogger(WxPayControllrt.class);

    public static final int WX_PAY = 0;

    @Resource
    WxPayService wxPayService;

    @Autowired
    OrderService orderService;


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
                    if (req.getProductNum() <= 0) // 防止抓包修改订单金额造成损失
                        return doObjResp(false,-999,"付款金额错误!");
                    else{
                        try{
                            return wxPayService.wxPay(json);
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

    // 微信异步通知
//    @RequestMapping(value = "/ngjf")
//    @ResponseBody
//    public String notify(HttpServletRequest request, HttpServletResponse response)
//            throws Exception {
//        String result;//返回给微信的处理结果
//        String inputLine;
//        String notityXml = "";
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        //微信给返回的东西
//        try {
//            while ((inputLine = request.getReader().readLine()) != null) {
//                notityXml += inputLine;
//            }
//            request.getReader().close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            result = setXml("fail","xml获取失败");
//        }
//        if (StringUtils.isEmpty(notityXml)) {
//            result = setXml("fail","xml为空");
//        }
//        Map map =  XMLUtil.doXMLParse(notityXml);
//        // 解析各种数据
//        String appid = (String) map.get("appid");//应用ID
//        String attach = (String) map.get("attach");//商家数据包
//        String bank_type = (String) map.get("bank_type");//付款银行
//        String cash_fee = (String) map.get("cash_fee");//现金支付金额
//        String fee_type = (String) map.get("fee_type");//货币种类
//        String is_subscribe = (String) map.get("is_subscribe");//是否关注公众账号
//        String mch_id = (String) map.get("mch_id");//商户号
//        String nonce_str = (String) map.get("nonce_str");//随机字符串
//        String openid = (String) map.get("openid");//用户标识
//        String out_trade_no = (String) map.get("out_trade_no");// 获取商户订单号
//        String result_code = (String) map.get("result_code");// 业务结果
//        String return_code = (String) map.get("return_code");// SUCCESS/FAIL
//        String sign = (String) map.get("sign");// 获取签名
//        String time_end = (String) map.get("time_end");//支付完成时间
//        String total_fee = (String) map.get("total_fee");// 获取订单金额
//        String trade_type = (String) map.get("trade_type");//交易类型
//        String transaction_id = (String) map.get("transaction_id");//微信支付订单号
//
//        SortedMap<String, String> parameters = new TreeMap<String, String>();
//        // 数据加密
//        parameters.put("appid", appid);//应用ID
//        parameters.put("attach", attach);//商家数据包
//        parameters.put("bank_type", bank_type);//付款银行
//        parameters.put("cash_fee", cash_fee);//现金支付金额
//        parameters.put("fee_type", fee_type);//货币种类
//        parameters.put("is_subscribe", is_subscribe);//是否关注公众账号
//        parameters.put("mch_id", mch_id);//商户号
//        parameters.put("nonce_str", nonce_str);//随机字符串
//        parameters.put("openid", openid);//用户标识
//        parameters.put("out_trade_no", out_trade_no);// 商户订单号
//        parameters.put("result_code", result_code);// 业务结果
//        parameters.put("return_code", return_code);// SUCCESS/FAIL
//        parameters.put("time_end", time_end);// 支付完成时间
//        parameters.put("total_fee", total_fee);// 获取订单金额
//        parameters.put("trade_type", trade_type);//交易类型
//        parameters.put("transaction_id", trade_type);//微信支付订单号
//        //加密前验证notify支付订单网关---https://gw.tenpay.com/gateway/simpleverifynotifyid.xml
////        RequestHandler reqHandler = new RequestHandler(request, response);
////        reqHandler.init(appid, appsecret, partnerkey);
////        //MD5加密
////        String endsign = reqHandler.createSign(parameters);
//
//
//        System.out.println("**************************************************************************************************");
//        System.out.println(appid+"-------------------应用ID");
//        System.out.println(attach+"-------------------商家数据包");
//        System.out.println(bank_type+"-------------------付款银行");
//        System.out.println(cash_fee+"-------------------现金支付金额");
//        System.out.println(fee_type+"-------------------货币种类");
//        System.out.println(is_subscribe+"-------------------是否关注公众账号");
//        System.out.println(mch_id+"-------------------商户号");
//        System.out.println(nonce_str+"-------------------随机字符串");
//        System.out.println(openid+"-------------------用户标识");
//        System.out.println(out_trade_no+"-------------------获取商户订单号");
//        System.out.println(result_code+"-------------------业务结果");
//        System.out.println(return_code+"------------------- SUCCESS/FAIL");
//        System.out.println(sign+"-------------------获取签名-微信回调的签名");
//        System.out.println(time_end+"-------------------支付完成时间");
//        System.out.println(total_fee+"-------------------获取订单金额");
//        System.out.println(trade_type+"-------------------交易类型");
//        System.out.println(transaction_id+"-------------------微信支付订单号");
//        System.out.println(sign+"-------------------第二次加密sign");
//        System.out.println("**************************************************************************************************");
//
//
//        // 验证签名
//        if (sign.equals(sign)) {
//            result = setXml("SUCCESS", "OK");
//        } else {
//            System.err.println("签名不一致！");
//            result = setXml("fail", "签名不一致！");
//        }
//        if (!"SUCCESS".equals("SUCCESS")) {
//            System.err.println("微信返回的交易状态不正确（result_code=" + "SUCCESS" + "）");
//            result =  setXml("fail", "微信返回的交易状态不正确（result_code=" + "SUCCESS" + "）");
//        }
//        //如果成功写入数据库
//        if ("SUCCESS".equals("SUCCESS")) {// 如果微信返回的结果是success，则修改订单状态
//            //判断订单号是否重复
//            List<Order> userList = orderService.queryOrder(out_trade_no);
//            if (userList.size() > 0) {
//                result = setXml("fail", "订单号重复");
//            }else{
//                //更新订单状态
//                Order order = new Order();
//                order.setOrdersn(out_trade_no);
//                order.setStatus(3); //付款成功
//
//                orderService.update(order);
//            }
//        }
//        System.out.println("回调成功");
//        System.out.println("----返回给微信的xml：" + result);
//        return result;
//    }
//
//    //通过xml 发给微信消息
//    public static String setXml(String return_code, String return_msg) {
//        SortedMap<String, String> parameters = new TreeMap<String, String>();
//        parameters.put("return_code", return_code);
//        parameters.put("return_msg", return_msg);
//        return "<xml><return_code><![CDATA[" + return_code + "]]>" +
//                "</return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
//    }



    /**
     * 微信异步通知
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/notify")
    @ResponseBody
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = PayCommonUtil.reciverWx(request); // 接收到异步的参数
        Map<String, String> m = new HashMap<String, String>();// 解析xml成map
        if (m != null && !"".equals(m)) {
            m = XMLUtil.doXMLParse(result);
        }
        // 过滤空 设置 TreeMap
        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);
            String v = "";
            if (null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
        // 判断签名是否正确
        String resXml = "";
        if (PayCommonUtil.isTenpaySign("UTF-8", packageParams)) {
            if ("SUCCESS".equals((String) packageParams.get("return_code"))) {
                // 如果返回成功
                String mch_id = (String) packageParams.get("mch_id"); // 商户号
                String out_trade_no = (String) packageParams.get("out_trade_no"); // 商户订单号
                String total_fee = (String) packageParams.get("total_fee");
                // String transaction_id = (String)
                // packageParams.get("transaction_id"); // 微信支付订单号
                // 查询订单 根据订单号查询订单

                //判断订单号是否重复
                List<Order> userList = orderService.queryOrder(out_trade_no);
                if (userList.size() > 0) {
                    result = this.setXml("fail", "订单号重复");
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[交易失败]]></return_msg>" + "</xml> ";
                } else {
                    //更新订单状态
                    Order order = new Order();
                    order.setOrdersn(out_trade_no);
                    order.setStatus(3); //付款成功

                    orderService.update(order);
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                }

                {

                }
            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[通知签名验证失败]]></return_msg>" + "</xml> ";
            }

            // 处理业务完毕，将业务结果通知给微信
            // ------------------------------
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        }
    }

    /**
     * @Description：返回给微信的参数
     * @param return_code
     *            返回编码
     * @param return_msg
     *            返回信息
     * @return
     */
    public static String setXml(String return_code, String return_msg)
    {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }

}
