package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.WxPayReq;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.Order;
import com.carryit.base.besttmwuu.service.MemberService;
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


    /**
     * 微信充值
     * @param json
     * @return
     */
    @RequestMapping(value = "/wxRecharge", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject wxRecharge(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }

    /**
     * 微信打赏
     * @param json
     * @return
     */
    @RequestMapping(value = "/wxReward", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject wxReward(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 2);
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
            case 1:
                try{
                    return wxPayService.wxRecharge(json);
                } catch (Exception e){
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    return doObjResp(false,-999,"程序异常!");
                }
            case 2:
                try{
                    return wxPayService.wxReward(json);
                } catch (Exception e){
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    return doObjResp(false,-999,"程序异常!");
                }

        }
        return null;
    }


    /**
     * 支付微信异步通知
     */
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
                    result = this.setXml("FAIL", "订单号重复");
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
     * 充值异步通知
     */
    @RequestMapping("/rechargeNotify")
    @ResponseBody
    public void rechargeNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
                    result = this.setXml("FAIL", "订单号重复");
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[交易失败]]></return_msg>" + "</xml> ";
                } else {
                    //更新订单状态，记录资金流水
                    wxPayService.updateRechargeInfo(out_trade_no,total_fee);



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
     * 打赏异步通知
     */
    @RequestMapping("/rewardNotify")
    @ResponseBody
    public void rewardNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
                    result = this.setXml("FAIL", "订单号重复");
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[交易失败]]></return_msg>" + "</xml> ";
                } else {
                    //更新两个 用户账户情况
                    //TODO


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
