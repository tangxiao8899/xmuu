package com.carryit.base.besttmwuu.web;


import com.carryit.base.besttmwuu.entity.Globouns;
import com.carryit.base.besttmwuu.entity.Member;
import com.carryit.base.besttmwuu.entity.Sincerity;
import com.carryit.base.besttmwuu.entity.imsEweiShopMember;
import com.carryit.base.besttmwuu.service.GlobounService;
import com.carryit.base.besttmwuu.service.MemberService;
import com.carryit.base.besttmwuu.service.SincerityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private GlobounService globounService;

    @Autowired
    private SincerityService sincerityService;

    //用户中心
    @RequestMapping(value = "/member")
    public Member Member(int uid){
        Member member=new Member();

        member= memberService.getMemberById(uid);

//        member.getId();
//        member.getAvatar();
//        member.getRealName();
//        member.getCity();
//        member.getNickName();
//        member.getLevelName();

        return member;
    }

    //财富值
    @RequestMapping(value = "/wealth")
    public Object Wealth(int uid){
        Member member =new Member();
        member=memberService.getWealthById(uid);
        String Wealth=member.getCredit2();
        return Wealth;
    }

    //诚信值
    @RequestMapping(value = "sincerity")
    public Object Sincerity(int uid){
        Sincerity sincerity=new Sincerity();

        sincerity=sincerityService.getNumberById(uid);
        return null;
    }

    //我的团队


    //我的资产
    @RequestMapping(value = "/globouns")
    public Globouns Globouns(int uid){

        Globouns globouns=new Globouns();

        globouns=globounService.getFindAll(uid);


        return globouns;
    }


    //圈子管理中心
    @RequestMapping(value = "/circles")
    public String CircleCenter(){

        return null;
    }




}
