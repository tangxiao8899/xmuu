package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.bean.req.WxPayReq;
import com.carryit.base.besttmwuu.entity.ImsUserCapitalFlowEntity;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.Order;
import com.carryit.base.besttmwuu.entity.Sincerity;
import com.carryit.base.besttmwuu.service.*;
import com.util.PayCommonUtil;
import com.util.TimeUtils;
import com.util.XMLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/wx")
public class WxPayControllrt extends BaseController {
    private static final String UUqz = "0";//UU圈主
    private static final String CWQZ = "1";//常务圈主
    private static final String Fqz = "2";//副圈主
    private static final String UCgly = "3";//UU管理员
    private static final String GVIP = "4";//高级VIP
    private static final String MVIP = "5";//梦想VIP
    Logger logger = LoggerFactory.getLogger(WxPayControllrt.class);

    public static final int WX_PAY = 0;

    @Resource
    WxPayService wxPayService;

    @Autowired
    OrderService orderService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private SincerityService sincerityService;

    @Autowired
    ImsUserCapitalFlowService imsUserCapitalFlowService;


    private static final  String two = "2";
    private static final  String three = "3";

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



    /**
     * 微信提现
     * @param json
     * @return
     */
    @RequestMapping(value = "/getCash", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getCash(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 4);
    }

    /**
     * 手动提现
     * @param json
     * @return
     */
    @RequestMapping(value = "/manualCash", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject manualCash(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 7);
    }


    /**
     * 报名支付
     * @param json
     * @return
     */
    @RequestMapping(value = "/wxEntered", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject wxEntered(@RequestBody(required = false) String json) {

        return callHttpReqTask(json, 5);
    }
    /**
     * 账单
     * @param json
     * @return
     */
    @RequestMapping(value = "/getBills", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getBillsbyUid(@RequestBody(required = false) String json) {

        return callHttpReqTask(json, 6);
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
            case 4:
                try{
                    return wxPayService.getCash(json);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    return doObjResp(false, -999, "程序异常!");
                }
            case 5  :
                try {
                    return wxPayService.wxEntered(json);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    return doObjResp(false, -999, "程序异常!");
                }
            case 6  :
                try {
                    return wxPayService.getBillsbyUid(json);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    return doObjResp(false, -999, "程序异常!");
                }
            case 7 :
                try {
                    return  wxPayService.manualCash(json);
                }catch (Exception e){
                    e.printStackTrace();
                    logger.error(e.getMessage());
                    return doObjResp(false, -999, "程序异常!");
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
                if (userList==null||userList.size() > 1) {
                    result = this.setXml("FAIL", "订单号不存在或者订单号重复");
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[交易失败]]></return_msg>" + "</xml> ";
                } else {
                        //更新订单状态
                        Order order = new Order();
                        order.setOrdersn(out_trade_no);
                        order.setStatus(3); //付款成功
                        orderService.update(order);
                        //绑定会员表的主圈子和会员等级
                        memberService.updateMemberZhuQuanZi(userList.get(0).getUid(),userList.get(0).getBid(),userList.get(0).getLevel());
                        //给充值和商城购买，100RMB等于1分的换算，给t_sincerity表诚信值number+1分，不够100不加，最高100分，
                    //根据UID查找iCode
                    String iCode=memberService.getICodeByUid(userList.get(0).getUid());
                    //根据iCode查找uid直推人的level
                    if (iCode!=null){
                        Member member=memberService.getLevelByICode(iCode);
                        if(MVIP.equals(userList.get(0).getLevel())){
                            if (member.getLevel().equals(MVIP)){
                                //直接梦想VIP价格30%加给直推人
                                //逻辑判断更新数据库余额字段方法没写
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.3));
                            }else if (member.getLevel().equals(GVIP)){
                                //高级VIP的40%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.4));
                            }else if (member.getLevel().equals(UCgly)){
                                //管理员的20%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.2));
                            }else if (member.getLevel().equals(Fqz)){
                                //副圈主的20%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.2));
                            }else if (member.getLevel().equals(CWQZ)){
                                //常务圈主的10%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.1));
                            }else if (member.getLevel().equals(UUqz)){
                                //圈主的10%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.1));
                            }

                        }else if (GVIP.equals(userList.get(0).getLevel())){
                            if (member.getLevel().equals(MVIP)){
                                //直接梦想VIP价格30%加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.3));
                            }else if (member.getLevel().equals(GVIP)){
                                //高级VIP的40%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.4));
                            }else if (member.getLevel().equals(UCgly)){
                                //管理员的40%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.4));
                            }else if (member.getLevel().equals(Fqz)){
                                //副圈主的40%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.4));
                            }else if (member.getLevel().equals(CWQZ)){
                                //常务圈主的20%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.2));
                            }else if (member.getLevel().equals(UUqz)){
                                //圈主的20%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.2));
                            }
                        }else if (UCgly.equals(userList.get(0).getLevel())){
                            if (member.getLevel().equals(MVIP)){
                                //直接梦想VIP价格30%加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.3));
                            }else if (member.getLevel().equals(GVIP)){
                                //高级VIP的40%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.4));
                            }else if (member.getLevel().equals(UCgly)){
                                //高级VIP的55%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.55));
                            }else if (member.getLevel().equals(Fqz)){
                                //高级VIP的55%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.55));
                            }else if (member.getLevel().equals(CWQZ)){
                                //常务圈主的22%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.22));
                            }else if (member.getLevel().equals(UUqz)){
                                //圈主的22%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.22));
                            }
                        }else if (Fqz.equals(userList.get(0).getLevel())){
                            if (member.getLevel().equals(MVIP)){
                                //直接梦想VIP价格30%加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.3));
                            }else if (member.getLevel().equals(GVIP)){
                                //高级VIP的40%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.4));
                            }else if (member.getLevel().equals(UCgly)){
                                //高级VIP的65%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.65));
                            }else if (member.getLevel().equals(Fqz)){
                                //高级VIP的65%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.65));
                            }else if (member.getLevel().equals(CWQZ)){
                                //常务圈主的22%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.22));
                            }else if (member.getLevel().equals(UUqz)){
                                //圈主的22%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.22));
                            }
                        }else if (CWQZ.equals(userList.get(0).getLevel())){
                            if (member.getLevel().equals(MVIP)){
                                //直接梦想VIP价格30%加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.3));
                            }else if (member.getLevel().equals(GVIP)){
                                //高级VIP的40%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.4));
                            }else if (member.getLevel().equals(UCgly)){
                                //高级VIP的70%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.7));
                            }else if (member.getLevel().equals(Fqz)){
                                //高级VIP的70%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.7));
                            }else if (member.getLevel().equals(CWQZ)){
                                //常务圈主的23%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.23));
                            }else if (member.getLevel().equals(UUqz)){
                                //圈主的23%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.23));
                            }
                        }else if (UUqz.equals(userList.get(0).getLevel())){
                            if (member.getLevel().equals(MVIP)){
                                //直接梦想VIP价格30%加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.3));
                            }else if (member.getLevel().equals(GVIP)){
                                //高级VIP的40%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.4));
                            }else if (member.getLevel().equals(UCgly)){
                                //高级VIP的75%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.75));
                            }else if (member.getLevel().equals(Fqz)){
                                //高级VIP的75%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.75));
                            }else if (member.getLevel().equals(CWQZ)){
                                //常务圈主的23%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.25));
                            }else if (member.getLevel().equals(UUqz)){
                                //圈主的23%价格加给直推人
                                updateDirectpushInfo(member.getUid(), (float) (userList.get(0).getPrice()*0.25));
                            }
                        }
                    }
                    if(userList.get(0).getPrice()>=100){
                        int ceil = (int)Math.floor(userList.get(0).getPrice() / 100);
                        Sincerity newSincerity = new Sincerity();
                        newSincerity.setUid(userList.get(0).getUid());
                        newSincerity.setNumber(ceil);
                        sincerityService.addOne(newSincerity);
                    }
                    }
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
                if (userList==null||userList.size() > 1) {
                    result = this.setXml("FAIL", "订单号重复");
                    resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                            + "<return_msg><![CDATA[交易失败]]></return_msg>" + "</xml> ";
                } else {
                    //更新订单状态，记录资金流水
                    wxPayService.updateRechargeInfo(out_trade_no,total_fee);
                    if(userList.get(0).getPrice()>=100){
                        int ceil = (int)Math.floor(userList.get(0).getPrice() / 100);
                        Sincerity newSincerity = new Sincerity();
                        newSincerity.setUid(userList.get(0).getUid());
                        newSincerity.setNumber(ceil);
                        sincerityService.addOne(newSincerity);
                    }
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

    //直推
    @Transactional
    public void updateDirectpushInfo(Integer uid, Float total_fee) {

        //查询Member表账户信息
        Member fmember = memberService.getMemberById(uid);

        float Creditf = fmember.getCredit2() + total_fee;

        //更新用户账户情况
        memberService.updateMemberByUid(uid, Creditf);

        //记录资金流水
        ImsUserCapitalFlowEntity entity = new ImsUserCapitalFlowEntity();
        entity.setUid(uid);
        entity.setPrice(total_fee);
        entity.setSource(4); //打赏
        entity.setType(0); //收入

        imsUserCapitalFlowService.save(entity);
    }

}
