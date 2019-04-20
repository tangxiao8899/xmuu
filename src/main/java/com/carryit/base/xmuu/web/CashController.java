package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.carryit.base.besttmwuu.entity.CashApply;
import com.carryit.base.besttmwuu.entity.CashDataDTO;
import com.carryit.base.besttmwuu.entity.User;
import com.carryit.base.besttmwuu.service.CashApplyService;
import com.carryit.base.besttmwuu.service.UserService;
import com.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cashLogin")
public class CashController extends HttpServlet {
    @Autowired
    private UserService userService;

    @Autowired
    private CashApplyService cashApplyService;

    Logger logger = LoggerFactory.getLogger(CashController.class);

    /**
     * 登录页面
      * @return
     */
    @RequestMapping(value = "/login")
    public String cashLogin(HttpServletRequest request){
        logger.info("=====");
        request.getSession().removeAttribute("user");
        return "login";
    }

    /**
     * 登录
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/txLogin")
    @ResponseBody
    public JSONObject txLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject jo = new JSONObject();
        String phone=req.getParameter("phone");
        String password=req.getParameter("password");
        if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){
            jo.put("code",400);
            jo.put("msg","账号或者密码不能为空");
        }
        User user = userService.getUserByPoneAndPassword(phone);
        if(StringUtils.isEmpty(user)){
            jo.put("code",404);
            jo.put("msg","用户不存在");
        }else if(!password.equals(user.getPassword())){
            jo.put("code",401);
            jo.put("msg","密码不正确");
        }else if(!PropertyUtil.getProperty("login_salt").equals(user.getSalt())){
            jo.put("code",401);
            jo.put("msg","没有登录权限！，请联系管理员");
        } else {
            req.getSession().setAttribute("user",user.getUserName());
            jo.put("code",200);
            jo.put("msg","登录成功");
        }
        return jo;
    }

    /**
     * 首页
     * @return
     */
    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }

    /**
     * 登录用户
     * @param req
     * @return
     */
    @RequestMapping("/loginUser")
    @ResponseBody
    public JSONObject loginUser(HttpServletRequest req){
        JSONObject jo = new JSONObject();
        jo.put("username",req.getSession().getAttribute("user"));
        return jo;
    }

    /**
     * 提现页面
     * @return
     */
    @RequestMapping("/getCash")
    public String getCash(){
        return "views/cash";
    }

    /**
     * 提现数据
     * @return
     */
    @RequestMapping("/cashData")
    @ResponseBody
    public JSONObject cashData(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        String phone = request.getParameter("phone");
        int page = Integer.valueOf(request.getParameter("page"));Integer.valueOf(request.getParameter("page"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        List<CashDataDTO> list = cashApplyService.cashData(phone,page,limit);

        int count = cashApplyService.count(phone);
        jo.put("code",0);
        jo.put("msg","");
        jo.put("data",list);
        jo.put("count",count);

        return jo;
    }

    /**
     * 修改状态
     * @param request
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public JSONObject updateStatus(HttpServletRequest request){
        JSONObject jo = new JSONObject();
        CashDataDTO dto = new CashDataDTO();

        String id = request.getParameter("id");
        String status = request.getParameter("status");
        if("-1".equals(status)){ //拒绝时，需要回退之前扣除金额
            String money = request.getParameter("money");
            String uid = request.getParameter("uid");
           dto.setMoney(Double.valueOf(money));
           dto.setUid(Integer.valueOf(uid));

        }
        dto.setId(Integer.valueOf(id));
        dto.setStatus(Integer.valueOf(status));
        try {
            cashApplyService.updateCash(dto);
            jo.put("code",200);
            jo.put("msg","修改成功");
        }catch (Exception e){
            jo.put("code",500);
            jo.put("msg","修改失败");
        }
        return jo;
    }
}
