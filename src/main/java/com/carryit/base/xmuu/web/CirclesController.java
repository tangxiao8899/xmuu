package com.carryit.base.besttmwuu.web;

import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.ServiceConfig;
import com.bean.Page;
import com.bean.req.BoardReq;
import com.carryit.base.besttmwuu.entity.*;
import com.carryit.base.besttmwuu.service.*;
import com.util.Log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/circle")
public class CirclesController extends BaseController {

    Logger logger = LoggerFactory.getLogger(CirclesController.class);

    @Autowired
    CirclesService circlesService;
    @Autowired
    BoardService boardService;
    @Autowired
    BoardFollowService boardFollowService;
    @Autowired
    MemberService memberService;
    @Autowired
    ImsEweiShopSnsPostService postService;
    @Autowired
    PraiseService praiseService;

    private static final  String Fqz = "1";//副圈主
    private static final  String UCgly = "2";//UU管理员


    @RequestMapping(value = "/getCircles", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getCircles(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 0);
    }

    /*
     * 根据用户id,圈子id获取圈子信息，并且判断用户是否关注圈子
     */
    @RequestMapping(value = "/getBoardByUid", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getBoardByUid(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 1);
    }

    @Override
    public JSONObject runTask(String json, int cmd) {
        //分红机制
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        first = first + " 00:00:00";
        Calendar firstc = Calendar.getInstance();
        try {
            firstc.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(first));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long startTime = firstc.getTimeInMillis();

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        last = last + " 23:59:59";
        Calendar lastc = Calendar.getInstance();
        try {
            lastc.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(last));
        } catch (ParseException e) {
            e.printStackTrace();
            return faild("失败~",false);
        }
        long endTime = lastc.getTimeInMillis();

        switch (cmd) {
            case 0:
                List<Circles> data = new ArrayList<>();
                try {
                    data = circlesService.getCircles();
                    if (data != null && data.size() > 0) {
                        for (Circles circle : data) {
                            List<Board> boardList = boardService.getBoardByCid(circle.getId());
                            circle.setBoards(boardList);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
                return doArraysResp(data);
            case 1:
                Board board = new Board();

                BoardFollow bf = null;

                try {
                    BoardReq req = p(json, BoardReq.class);
                    if (req != null) {
//                        if (ServiceConfig.DEBUG) {
//                            req = new BoardReq();
//                            req.bid = 17;
//                            req.uid = 17;
//                        }
                        bf = boardFollowService.getBoardByUid(req.uid, req.bid);// 查询该用户是否关注该圈子
                        if (bf != null) {
                            return doObjResp(true);
                        } else {
                            return doObjResp(false);
                        }
                    }
                    //Log.e("请求字符串=" + json + "|命令|" + cmd);
                    //board = boardService.getBoardById(req.bid);// 查询圈子详细信息
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
//            case 2:
//                MemberManage manage = new MemberManage();
//                //所有会员集合
//                List<Member> memberList = new ArrayList<>();
//
//                List<String> adminList = new ArrayList<>();
//                adminList.add(UU圈主);//UU圈主
//                adminList.add(副圈主);//副圈主
//                adminList.add(UC管理员);//UC管理员
//                //管理员集合
//                List<Member> adminMember = new ArrayList<>();
//                //普通用户集合
//                List<Member> normalMember = new ArrayList<>();
//                BoardReq req = p(json, BoardReq.class);
//                try {
//                    if (req != null) {
//                        Member member = memberService.getMemberById(req.uid);
//                        if (member != null && member.getZhuquanzi() != null) {
//                            //主圈子信息
//                            Board zhuQuanZiboard = boardService.getBoardById(member.getZhuquanzi());
//                            //升序，查找所有该主圈子的用户
//                            memberList = boardFollowService.getMemberByZhuQuanZiId(member.getZhuquanzi());
//                            //符合uu圈主，副圈主，UC管理员的要分离出来
//                            for (int i = 0; i < memberList.size(); i++) {
//                                if (adminList.contains(memberList.get(i).getLevel())) {
//                                    adminMember.add(memberList.get(i));
//                                } else {
//                                    normalMember.add(memberList.get(i));
//                                }
//                            }
//                            manage.setBoard(zhuQuanZiboard);
//                            manage.setAdminMember(adminMember);
//                            manage.setNormalMember(normalMember);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return doObjResp(manage);
            case 3:
                List<Board> boardList = new ArrayList<>();
                List<Board> boardListDTO = new ArrayList<>();
                Board bo = new Board();
                bo.setId(0);
                bo.setTitle("全部");
                boardListDTO.add(bo);
                BoardReq _req = p(json, BoardReq.class);
                try {
                    if (_req != null) {
                        boardList = boardFollowService.getBoardFollowByUId(_req.uid);
                    if(boardList!=null&&boardList.size()>0){
                        for (Board _bo:boardList) {

                            if(_bo.getBanner()!=null){
                                List<String> result = Arrays.asList(_bo.getBanner().split(","));
                                _bo.setBannerList(result);
                            }
                            boardListDTO.add(_bo);
                        }
                    }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
                return doObjResp(boardListDTO);
            case 4:
                List<Board> newboardList = new ArrayList<>();

                BoardReq unconcerned = p(json, BoardReq.class);
                try {
                    if (unconcerned != null) {
                        newboardList = boardFollowService.getUnconcerned(unconcerned.uid);
                        if (newboardList != null && newboardList.size() > 0) {
                            if (newboardList.size() > 10) {
                                newboardList = newboardList.subList(0, 10);
                            }
                            for (int i = 0; i < newboardList.size(); i++) {
                                if(newboardList.get(i).getBanner()!=null){
                                    List<String> result = Arrays.asList(newboardList.get(i).getBanner().split(","));
                                    newboardList.get(i).setBannerList(result);
                                }
                            }
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
                return doObjResp(newboardList);
            case 5:

                MemberLevel ml = p(json, MemberLevel.class);
                try {
                    if (ml != null && ml.getUid() != 0 && ml.getLevel() != null) {
                        //根据uid查询该用户的主圈子
                        Member mb = memberService.getMemberById(ml.getUid());
                        if (mb != null && mb.getZhuquanzi() != null) {
                            //如果等级level为副圈主 ="1"，查找该圈子副圈主的个数
                            if (Fqz.equals(ml.getLevel())) {

                                int fuCount = memberService.getMemberByUIdAndLevel(mb.getZhuquanzi(), Fqz);
                                if (fuCount >= 10) {
                                    return faild("副圈主名额已满", false);
                                } else {
                                    //更新用户为副圈主
                                    memberService.updateMemberByUIdAndLevel(ml.getUid(), ml.getLevel());
                                    return doObjRespSuccess("成功");
                                }

                            } else if (UCgly.equals(ml.getLevel())) {
                                //如果等级level为UC管理员 ="2"
                                int fuCount = memberService.getMemberByUIdAndLevel(mb.getZhuquanzi(), ml.getLevel());
                                if (fuCount >= 50) {
                                    return faild("UU管理员名额已满", false);
                                } else {
                                    //更新用户为UC管理员
                                    memberService.updateMemberByUIdAndLevel(ml.getUid(), ml.getLevel());
                                    return doObjRespSuccess("成功");
                                }
                            }else {
                                return faild("数据异常(等级选择错误)", false);
                            }


                        } else {
                            return faild("数据异常（用户没有购买主圈子）~", false);
                        }
                    } else {
                        return faild("参数异常~", false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }

            case 6:
                Follow follow = p(json, Follow.class);
                try {
                    if (follow != null && follow.getUid() != 0 & follow.getBid() != 0) {

                        if ("0".equals(follow.getFollow())) {
                            BoardFollow boardByUid = boardFollowService.getBoardByUid(follow.getUid(), follow.getBid());
                            if (boardByUid != null) {
                                //取消关注
                                boardFollowService.delete(follow.getUid(), follow.getBid());
                                return doObjResp(true);
                            } else {
                                return faild("取消关注失败~", false);
                            }

                        } else if ("1".equals(follow.getFollow())) {
                            //关注
                            BoardFollow newbf = boardFollowService.getBoardByUid(follow.getUid(), follow.getBid());
                            if (newbf == null) {
                                boardFollowService.add(follow.getUid(), follow.getBid(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                return doObjResp(true);
                            } else {
                                return faild("关注失败~", false);
                            }
                        }
                    } else {
                        return faild("参数异常~", false);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
            case 7:
                BoardManage boardManage = p(json, BoardManage.class);
                Page page = new Page();
                Board zhuQuanZiboard = new Board();
                JSONObject jo = new JSONObject();
                //普通用户集合
                List<Member> nm = new ArrayList<>();
                long count = 0;
                try {
                    if (boardManage != null) {
                        Member member = memberService.getMemberById(boardManage.getUid());
                        if (member != null && member.getZhuquanzi() != null) {
                            if (boardManage.getPageStart() != 0 && boardManage.getPageSize() != 0) {
                                //主圈子信息
                                zhuQuanZiboard = boardService.getBoardById(member.getZhuquanzi());
                                //查找该主圈子的普通用户（分页）
                                nm = memberService.getnormalMember(member.getZhuquanzi(), startTime, endTime, (boardManage.getPageStart() - 1) * boardManage.getPageSize(), boardManage.getPageSize());
                                count = memberService.getnormalMemberCount(member.getZhuquanzi());
                                page.setList(nm);
                                page.setPageSize(boardManage.getPageSize());
                                page.setTotalSize(count);
                            }
                        }
                    }
                    jo.put("page", page);
                    jo.put("board", zhuQuanZiboard);
                    return doObjResp(jo);
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
            case 8:
                BoardManage bm = p(json, BoardManage.class);
                Page newpage = new Page();
                Board zb = new Board();
                JSONObject newjo = new JSONObject();
                //管理员用户集合
                List<Member> admin = new ArrayList<>();
                long newcount = 0;
                try {
                    if (bm != null) {
                        Member member = memberService.getMemberById(bm.getUid());
                        if (member != null && member.getZhuquanzi() != null) {
                            if (bm.getPageStart() != 0 && bm.getPageSize() != 0) {
                                //主圈子信息
                                zb = boardService.getBoardById(member.getZhuquanzi());
                                //查找该主圈子的普通用户（分页）
                                admin = memberService.getadminMember(member.getZhuquanzi(), startTime, endTime, (bm.getPageStart() - 1) * bm.getPageSize(), bm.getPageSize());
                                newcount = memberService.getadminMemberCount(member.getZhuquanzi());
                                newpage.setList(admin);
                                newpage.setPageSize(bm.getPageSize());
                                newpage.setTotalSize(newcount);

                            }
                        }
                    }
                    newjo.put("page", newpage);
                    newjo.put("board", zb);
                    return doObjResp(newjo);
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
            case 9:
                BoardDetail boardDetail = new BoardDetail();
                Board boa = new Board();
                BoardFollow nbf = null;
                try {
                    BoardReq req = p(json, BoardReq.class);
                    if (req != null) {
                        boa = boardService.getBoardById(req.bid);
                        if(StringUtils.isNotBlank(boa.getCulturewall())){
                            String emojiStr = URLDecoder.decode(boa.getCulturewall(), "utf-8");
                            boa.setCulturewall(emojiStr);
                        }
                        boardDetail.setBoard(boa);
                        if(boa!=null&&boa.getAdvertisement()!=null){
                            List<String> result = Arrays.asList(boa.getAdvertisement().split(","));
                            boardDetail.setAdvertisement(result);
                        }
                        nbf = boardFollowService.getBoardByUid(req.uid, req.bid);// 查询该用户是否关注该圈子
                        if (nbf != null) {
                            boardDetail.setFollow("1");
                        } else {
                            boardDetail.setFollow("0");
                        }
                       long topic= boardFollowService.getTopicCount(req.bid);
                        boardDetail.setTopic(topic);
                       long followCount =boardFollowService.getFollowCount(req.bid);
                        boardDetail.setFollowCount(followCount);
                    }
                    return doObjResp(boardDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
            case 10:
                BoardManage boardTopic = p(json, BoardManage.class);
                Page topicPage = new Page();

                long topicCount = 0;
                try {
                if (boardTopic != null) {
                    //分页查询10条动态
                  List<Post> postList =  boardFollowService.getAllBoardTopic(boardTopic.getBid(),(boardTopic.getPageStart() - 1) * boardTopic.getPageSize(), boardTopic.getPageSize());
                    List<TrendsData> TrendsDataList = new ArrayList<>();
                  if(postList!=null&&postList.size()>0){
                     for (Post post:postList) {
                         TrendsData trendsData = new TrendsData();
                         if(post.getContent()!=null){
                             post.setContent(URLDecoder.decode(post.getContent(), "utf-8"));
                         }
                         //根据动态查评论
                         List<Post> commentList = postService.getcommentBypid(post.getId());
                         if(commentList!=null&&commentList.size()>0){
                             for (Post po:commentList) {
                                 po.setContent(URLDecoder.decode(po.getContent(), "utf-8"));
                             }
                         }
                         //查找点赞数
                         long praiseCount  = praiseService.getPraiseCount(post.getId());
                         //点赞头像
                         List<String> avatarList = praiseService.getPraiseImage(post.getId());
                         if(post.getCreatetime()!=0){
                             SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                             String date = fm.format(new Date(post.getCreatetime()));
                             post.setCreateDate(date);
                         }
                         if(post.getImage()!=null){
                             List<String> result = Arrays.asList(post.getImage().split(","));
                             post.setImageList(result);
                         }
                         //查询该用户是否点赞
                         TPraise pr = praiseService.getPraise(boardTopic.getUid(),post.getId());
                         if(pr==null){
                             post.setFabulous(0);//没有点赞
                         }else {
                             post.setFabulous(1);//已经点赞
                         }

                         trendsData.setPost(post);
                         trendsData.setCommentList(commentList);
                         trendsData.setPraiseCount(praiseCount);
                         trendsData.setAvatarList(avatarList);
                         TrendsDataList.add(trendsData);

                     }
                 }
                  topicCount =  boardFollowService.getAllBoardTopicCount(boardTopic.getBid());

                    topicPage.setList(TrendsDataList);
                    topicPage.setPageSize(boardTopic.getPageSize());
                    topicPage.setTotalSize(topicCount);

                }
                return doObjResp(topicPage);
            } catch (Exception e) {
                e.printStackTrace();
                    return faild("失败~",false);
            }

            case 11:
                BoardManage newBoardTopic = p(json, BoardManage.class);
                Page newTopicPage = new Page();

                long newtopicCount = 0;
                try {
                    if (newBoardTopic != null) {
                        List<TrendsData> newTrendsDataList = new ArrayList<>();

                        //查找用户关注的所有的圈子id
                       List<Integer> boardIDList = boardFollowService.getboardIDListUId(newBoardTopic.getUid());
                        if(boardIDList!=null&&boardIDList.size()>0){
                            //分页查询10条动态
                            List<Post> postList =  boardFollowService.getNewAllBoardTopic(boardIDList,(newBoardTopic.getPageStart() - 1) * newBoardTopic.getPageSize(), newBoardTopic.getPageSize());

                            if(postList!=null&&postList.size()>0){
                                for (Post post:postList) {
                                    TrendsData newTrendsData = new TrendsData();

                                    if(post.getContent()!=null){
                                        post.setContent(URLDecoder.decode(post.getContent(), "utf-8"));
                                    }
                                    //根据动态查评论
                                    List<Post> newCommentList = postService.getcommentBypid(post.getId());

                                    if(newCommentList!=null&&newCommentList.size()>0){
                                        for (Post po:newCommentList) {
                                            po.setContent(URLDecoder.decode(po.getContent(), "utf-8"));
                                        }
                                    }

                                    //查找点赞数
                                    long newPraiseCount  = praiseService.getPraiseCount(post.getId());
                                    //点赞头像
                                    List<String> newAvatarList = praiseService.getPraiseImage(post.getId());
                                    //查询当前动态当前用户是否点赞
                                    TPraise pr = praiseService.getPraise(newBoardTopic.getUid(),post.getId());
                                    if(pr==null){
                                        post.setFabulous(0);//没有点赞
                                    }else {
                                        post.setFabulous(1);//已经点赞
                                    }
                                    newTrendsData.setPost(post);
                                    newTrendsData.setCommentList(newCommentList);
                                    newTrendsData.setPraiseCount(newPraiseCount);
                                    newTrendsData.setAvatarList(newAvatarList);
                                    newTrendsDataList.add(newTrendsData);
                                    if(post.getCreatetime()!=0){
                                        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                        String date = fm.format(new Date(post.getCreatetime()));
                                        post.setCreateDate(date);
                                    }
                                    if(post.getImage()!=null){
                                        List<String> result = Arrays.asList(post.getImage().split(","));
                                        post.setImageList(result);
                                    }
                                }
                            }
                            newtopicCount =  boardFollowService.getNewAllBoardTopicCount(boardIDList);
                        }

                        newTopicPage.setList(newTrendsDataList);
                        newTopicPage.setPageSize(newBoardTopic.getPageSize());
                        newTopicPage.setTotalSize(newtopicCount);
                    }
                    return doObjResp(newTopicPage);
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
        case 12:

            try {
                return circlesService.getCirclesInfo(json);
            } catch (Exception e) {
                e.printStackTrace();
                return faild("失败~",false);
            }
        case 13:
                List<Board> _boardList = new ArrayList<>();
                BoardReq _newreq = p(json, BoardReq.class);
                try {
                    if (_newreq != null) {
                        _boardList = boardFollowService.getBoardFollowByUId(_newreq.uid);
                        if(_boardList!=null&&_boardList.size()>0){
                            for (Board _bo:_boardList) {
                                if(_bo.getBanner()!=null){
                                    List<String> result = Arrays.asList(_bo.getBanner().split(","));
                                    _bo.setBannerList(result);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return faild("失败~",false);
                }
                return doObjResp(_boardList);
            case 14:
                try{
                    Culturewall culturewall = p(json, Culturewall.class);
                    if (culturewall != null) {
                        String encodeCulturewall = URLEncoder.encode(culturewall.getCulturewall(), "utf-8");
                        culturewall.setCulturewall(encodeCulturewall);
                        circlesService.updateCulturewallByBid(culturewall);
                        return doObjRespSuccess("更新成功");
                    }else {
                        return faild("参数缺失~",false);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return faild("失败~",false);
                }

            case 15:
                try{
                    BoardManage _bm = p(json, BoardManage.class);
                    if (_bm != null) {
                        Member member = memberService.getMember(_bm.getUid(), _bm.getBid());
                        if(member!=null&&member.getLevel().equals("0")){
                            return doObjResp("true");
                        }else {
                            return doObjResp("false");
                        }
                    }else {
                        return faild("参数缺失~",false);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return faild("失败~",false);
                }
        }
        return null;
    }

    /*
      关注与取消关注
 *
 * */
    @RequestMapping(value = "/onAndOff", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject onAndOff(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 6);
    }


    /*
    圈子管理
    * 根据用户id获取主圈子和主圈子下面的所有会员
    * */
//    @RequestMapping(value = "/getZhuQuanZiByUid", method = {RequestMethod.GET,
//            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
//    public JSONObject getZhuQuanZiByUid(@RequestBody(required = false) String json) {
//        return callHttpReqTask(json, 2);
//    }

    /*
    *
    * 根据用户id获取关注过的圈子（收藏）(title含有全部)
    * */
    @RequestMapping(value = "/getQuanZiByUid", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getQuanZiByUid(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 3);
    }

    /*
*
* 根据用户id获取关注过的圈子（收藏）(title没有全部)
* */
    @RequestMapping(value = "/getNewQuanZiByUid", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getNewQuanZiByUid(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 13);
    }


    /*
  *
  * 推荐10条动态最高的未关注的圈子
  * */
    @RequestMapping(value = "/getUnconcerned", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getUnconcerned(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 4);
    }

    /*
*
* 根据uid修改会员等级 参数 {uid=xx,level=xx}
* */
    @RequestMapping(value = "/updateMemberLevel", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject updateMemberLevel(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 5);
    }

    /*
  圈子管理
* 根据用户id获取主圈子和主圈子下面的所有普通会员（分页，每页10条）
* */
    @RequestMapping(value = "/getNormalPage", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getNormalPage(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 7);
    }

    /*
圈子管理
* 根据用户id获取主圈子和主圈子下面的所有管理员（分页，每页10条）
* */
    @RequestMapping(value = "/getAdminPage", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getAdminPage(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 8);
    }



    /**
     * 圈子查询
     * @param json
     * @return
     */
    @RequestMapping(value = "/getCirclesInfo", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getCirclesInfo(@RequestBody(required = false) String json){
        return callHttpReqTask(json,12);
        }
    /*
    点击圈子查看圈子详情
*
* */
    @RequestMapping(value = "/getBoardDetail", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getBoardDetail(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 9);
    }



    /*
    分页查询某个圈子的所有动态和评论和点赞数
*
* */
    @RequestMapping(value = "/getBoardtopic", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getBoardtopic(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 10);
    }


    /*
    分页查询某个用户所关注的圈子的所有动态和评论和点赞数
*
* */
    @RequestMapping(value = "/getAlltopic", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject getAlltopic(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 11);
    }


    /*
    * 根据bid更新文化墙
    *
    * */
    @RequestMapping(value = "/updateCulturewall", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject updateCulturewall(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 14);
    }

    /*
 * 判断是否为该圈子的圈主
 *
 * */
    @RequestMapping(value = "/isQuanZhu", method = {RequestMethod.GET,
            RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    public JSONObject isQuanZhu(@RequestBody(required = false) String json) {
        return callHttpReqTask(json, 15);
    }
}
