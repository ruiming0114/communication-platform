package com.rm.plat.controller;

import com.rm.plat.pojo.*;
import com.rm.plat.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.model.IModel;

import javax.jws.WebParam;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TopicController {

    @Autowired
    TopicCommentReportService topicCommentReportService;

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    @Autowired
    TopicCommentService topicCommentService;

    @Autowired
    TopicAttentionService topicAttentionService;

    @Autowired
    TopicCommentReplyService topicCommentReplyService;

    @Autowired
    TopicCommentLikedService  topicCommentLikedService;

    @Autowired
    TopicReportService topicReportService;

    @RequestMapping("/queryTopicList")
    @ResponseBody
    public List<Topic> queryTopicList(){
        List<Topic> list = topicService.queryTopicList();
        for (Topic topic:list){
            System.out.println(topic);
        }
        return list;
    }

    @RequestMapping("/topicindex")
    public String topicindex(Model model){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        if (currentUser!=null) {
            model.addAttribute("currentuserid", currentUser.getUserid());
        }
        else {
            model.addAttribute("currentuserid", -1);
        }
        return "topic/TopicIndex";
    }

    @RequestMapping("/queryTopicCommentByTopic")
    @ResponseBody
    public List<TopicComment> queryTopicCommentByTopic(){
        List<TopicComment> list = topicCommentService.queryTopicCommentByTopic(1);
        for(TopicComment topicComment:list){
            System.out.println(topicComment);
        }
        return list;
    }

    @RequestMapping("/queryTopicReportList")
    @ResponseBody
    public List<TopicReport> queryTopicReportList(){
        return topicReportService.queryTopicReportList();
    }

    @RequestMapping("/queryHotTopicList")
    @ResponseBody
    public List<Topic> queryHotTopicList(){
        return topicService.queryHotTopicList();
    }

    @RequestMapping("/queryNewTopicList")
    @ResponseBody
    public List<Topic> queryNewTopicList(){
        return topicService.queryNewTopicList();
    }

    @RequestMapping("/queryHotTopicList_to_comment")
    @ResponseBody
    public List<TopicComment> queryHotTopicList_to_comment() {
       List<Topic> list = topicService.queryHotTopicList();
       List<TopicComment> res = new ArrayList<>();
       for(Topic topic:list){
           List<TopicComment> temp =topicCommentService.queryTopicCommentByTopic(topic.getTopicid());
          if (temp.size()==0){
              res.add(null);
              res.add(null);
          }
          else if (temp.size()==1){
              res.add(temp.get(0));
              res.add(null);
          }
          else {
              res.add(temp.get(0));
              res.add(temp.get(1));
          }
       }
       return res;
    }

    @RequestMapping("/topic/{topicid}")
    public String toTopic(@PathVariable("topicid") int topicid, Model model){
        model.addAttribute("currenttopic",topicService.queryTopicById(topicid));
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        if (currentUser!=null){
            model.addAttribute("currentuserperms", currentUser.getPerms());
            model.addAttribute("currentuserid",currentUser.getUserid());
            if (topicAttentionService.isAttention(topicid,currentUser.getUserid())){
                model.addAttribute("attention",1);
            }
            else{
                model.addAttribute("attention",0);
            }
        }
        else {
            model.addAttribute("attention",0);
        }
        return "topic/TopicDetail";
    }

    @RequestMapping("/queryTopicCommentListByTopic/{topicid}")
    @ResponseBody
    public List<TopicComment> queryTopicCommentListByTopic(@PathVariable("topicid") int topicid){
        return topicCommentService.queryTopicCommentByTopic(topicid);
    }

    @RequestMapping("/queryTopicCommentListByTopic_UserID_to_Username/{topicid}")
    @ResponseBody
    public  List<User> queryTopicCommentListByTopic_UserID_to_Username(@PathVariable("topicid") int topicid){
        List<TopicComment> list = topicCommentService.queryTopicCommentByTopic(topicid);
        List<User> res = new ArrayList<>();
        for (TopicComment topicComment:list){
            res.add(userService.queryUserByID(topicComment.getUserid()));
        }
        return res;
    }

    @RequestMapping("/toAdd_noLogin")
    public String toLogin(Model model){
        model.addAttribute("loginMsg","您还没有登录，请登录后发表评论");
        return "Login";
    }

    @RequestMapping("/toAdd_noLogin_att")
    public String toLogin_att(Model model){
        model.addAttribute("loginMsg","您还没有登录，请登录后关注话题");
        return "Login";
    }

    @RequestMapping("/AddTopicComment")
    public String addTopicComment(@RequestParam("topicid") int topicid,@RequestParam("userid") int userid,@RequestParam("content") String content,@RequestParam("imgpath1") String imgpath1,@RequestParam("imgpath2") String imgpath2,@RequestParam("imgpath3") String imgpath3,@RequestParam("imgpath4") String imgpath4){
        TopicComment topicComment =new TopicComment();
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        topicComment.setAddtime(timeStamp);
        topicComment.setCommentid(topicCommentService.max()+1);
        topicComment.setContent(content);
        topicComment.setLikenum(0);
        topicComment.setUserid(userid);
        topicComment.setTopicid(topicid);
        topicComment.setImgpath1(imgpath1);
        topicComment.setImgpath2(imgpath2);
        topicComment.setImgpath3(imgpath3);
        topicComment.setImgpath4(imgpath4);
        topicCommentService.addTopicComment(topicComment);
        return "redirect:/topic/"+topicid;
    }

    @RequestMapping("/addTopicAttention")
    public String addTopicAttention(@RequestParam("userid") int userid,@RequestParam("topicid") int topicid){
        TopicAttention topicAttention=new TopicAttention();
        topicAttention.setTopicid(topicid);
        topicAttention.setUserid(userid);
        topicAttentionService.addTopicAttention(topicAttention);
        return "redirect:/topic/"+topicid;
    }

    @RequestMapping("/deleteTopicAttention")
    public String deleteTopicAttention(@RequestParam("userid") int userid,@RequestParam("topicid") int topicid){
        topicAttentionService.deleteTopicAttention(topicid,userid);
        return "redirect:/topic/"+topicid;
    }

    @RequestMapping("/addTopicCommentLike")
    public String addTopicCommentLike(@RequestParam("commentid") int commentid,@RequestParam("userid") int userid){
        TopicCommentLiked topicCommentLiked = new TopicCommentLiked();
        topicCommentLiked.setCommentid(commentid);
        topicCommentLiked.setUserid(userid);
        topicCommentLikedService.addTopicCommentLiked(topicCommentLiked);
        TopicComment topicComment = topicCommentService.queryTopicCommentById(commentid);
        topicComment.setLikenum(topicCommentLikedService.cntByComment(commentid));
        topicCommentService.updateTopicComment(topicComment);
        return "redirect:/topiccomment/"+commentid;
    }

    @RequestMapping("/deleteTopicCommentLike")
    public String deleteTopicCommentLike(@RequestParam("commentid") int commentid,@RequestParam("userid") int userid){
        topicCommentLikedService.deleteTopicCommentLiked(commentid,userid);
        TopicComment topicComment = topicCommentService.queryTopicCommentById(commentid);
        topicComment.setLikenum(topicCommentLikedService.cntByComment(commentid));
        topicCommentService.updateTopicComment(topicComment);
        return "redirect:/topiccomment/"+commentid;
    }

    @RequestMapping("/topiccomment/{commentid}")
    public String toComment(@PathVariable("commentid") int commentid,Model model){
        model.addAttribute("currentcomment",topicCommentService.queryTopicCommentById(commentid));
        model.addAttribute("commentusername",userService.queryUserByID(topicCommentService.queryTopicCommentById(commentid).getUserid()).getUsername());
        model.addAttribute("replylist",topicCommentReplyService.queryTopicCommentReplyByComment(commentid));
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        if (currentUser!=null) {
            model.addAttribute("currentuserperms", currentUser.getPerms());
            model.addAttribute("currentuserid", currentUser.getUserid());
            if (topicCommentLikedService.isLiked(commentid,currentUser.getUserid())){
                model.addAttribute("liked",1);
            }
            else{
                model.addAttribute("liked",0);
            }
        }
        else {
            model.addAttribute("currentuserid", -1);
        }
        return "topic/TContentComment";
    }

    @RequestMapping("/addTopicCommentReply")
    public String addTopicCommentReply(@RequestParam("commentid") int commentid,@RequestParam("userid") int userid,@RequestParam("content") String content){
        TopicCommentReply topicCommentReply= new TopicCommentReply();
        topicCommentReply.setCommentid(commentid);
        topicCommentReply.setUserid(userid);
        topicCommentReply.setCommentreplyid(topicCommentReplyService.max()+1);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        topicCommentReply.setAddtime(timeStamp);
        topicCommentReply.setContent(content);
        topicCommentReplyService.addTopicCommentReply(topicCommentReply);
        return "redirect:/topiccomment/"+commentid;
    }

    @RequestMapping("/queryTopicCommentReplyList/{commentid}")
    @ResponseBody
    public List<TopicCommentReply> queryTopicCommentReplyList(@PathVariable("commentid") int commentid){
        return topicCommentReplyService.queryTopicCommentReplyByComment(commentid);
    }

    @RequestMapping("/queryTopicCommentReplyList_Userid_to_Username/{commentid}")
    @ResponseBody
    public List<User> queryTopicCommentReplyList_Userid_to_Username(@PathVariable("commentid") int commentid){
        List<TopicCommentReply> list = topicCommentReplyService.queryTopicCommentReplyByComment(commentid);
        List<User> res = new ArrayList<>();
        for(TopicCommentReply topicCommentReply: list){
            res.add(userService.queryUserByID(topicCommentReply.getUserid()));
        }
        return res;
    }

    @RequestMapping("/toAddTopicReport/{topicid}")
    public String toAddTopicReport(@PathVariable("topicid") int topicid,Model model){
        model.addAttribute("topicid",topicid);
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("userid",currentUser.getUserid());
        return "topic/AddReport";
    }

    @RequestMapping("/AddTopicReport")
    public String AddTopicReport(@RequestParam("topicid") int topicid,@RequestParam("userid") int userid,@RequestParam("content") String content,@RequestParam("title") String title){
        TopicReport topicReport= new TopicReport();
        topicReport.setContent(content);
        topicReport.setTitle(title);
        topicReport.setTopicid(topicid);
        topicReport.setUserid(userid);
        topicReport.setTopicreportid(topicReportService.max()+1);
        topicReport.setDeal(0);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        topicReport.setAddtime(timeStamp);
        topicReportService.addTopicReport(topicReport);
        return "redirect:/topic/"+topicid;
    }

    @RequestMapping("/toAddTopicCommentReport/{commentid}")
    public String toAddTopicCommentReport(@PathVariable("commentid") int commentid, Model model){
        model.addAttribute("commentid",commentid);
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("userid",currentUser.getUserid());
        return "topic/AddCommentReport";
    }

    @RequestMapping("/AddTopicCommentReport")
    public String AddTopicCommentReport(@RequestParam("commentid") int commentid,@RequestParam("userid") int userid,@RequestParam("content") String content,@RequestParam("title") String title){
       TopicCommentReport topicCommentReport =new TopicCommentReport();
       topicCommentReport.setCommentid(commentid);
       topicCommentReport.setUserid(userid);
       topicCommentReport.setTopiccommentreportid(topicCommentReportService.max()+1);
       topicCommentReport.setContent(content);
       topicCommentReport.setTitle(title);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
       topicCommentReport.setAddtime(timeStamp);
       topicCommentReport.setDeal(0);
       topicCommentReportService.addTopicCommentReport(topicCommentReport);
        return "redirect:/topiccomment/"+commentid;
    }

    @RequestMapping("/deleteTopicComment")
    public String deleteTopicComment(@RequestParam("commentid") int commentid){
        int topicid=topicCommentService.queryTopicCommentById(commentid).getTopicid();
        topicCommentService.deleteTopicComment(commentid);
        return "redirect:/topic/"+topicid;
    }

    @RequestMapping("/deleteTopicCommentReply")
    public String deleteTopicCommentReply(@RequestParam("commentreplyid") int commentreplyid){
        TopicCommentReply topicCommentReply = topicCommentReplyService.queryTopicCommentReplyById(commentreplyid);
        topicCommentReplyService.deleteTopicCommentReply(commentreplyid);
        return "redirect:/topiccomment/"+topicCommentReply.getCommentid();
    }

    @RequestMapping("/deleteTopic")
    public String deleteTopic(@RequestParam("topicid") int topicid){
        topicService.deleteTopic(topicid);
        return "redirect:/topicindex";
    }

    @RequestMapping("/deleteTopic_Admin")
    public String deleteTopic_Admin(@RequestParam("topicid") int topicid){
        topicService.deleteTopic(topicid);
        return "redirect:/toAdminister_Topic";
    }

    @RequestMapping("/toAddTopic")
    public String toAddTopic(){
        return "topic/CreateTopic";
    }

    @RequestMapping("/toAddTopic_Nologin")
    public String toAddTopic_No(Model model){
        model.addAttribute("loginMsg","您还没有登录，请登录后创建话题");
        return "Login";
    }

    @RequestMapping("/AddTopic")
    public String AddTopic(@RequestParam("title") String title,@RequestParam("intro") String intro,@RequestParam("classify") String classify){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        Topic topic = new Topic();
        topic.setClassify(classify);
        topic.setTitle(title);
        topic.setIntro(intro);
        topic.setTopicid(topicService.max()+1);
        topic.setUserid(currentUser.getUserid());
        topicService.addTopic(topic);
        return "redirect:/topicindex";
    }

    @RequestMapping("/toCheckTopic/{name}")
    public String toCheck(@PathVariable("name") String name,Model model){
        model.addAttribute("name",name);
        return "topic/TopicSearch";
    }

    @RequestMapping("/checkTopicList/{name}")
    @ResponseBody
    public List<Topic> CheckTopic(@PathVariable("name") String name){
        return topicService.queryTopicByName(name);
    }

    @RequestMapping("/queryTopicListByClass/{name}")
    @ResponseBody
    public List<Topic> queryTopicListByClass(@PathVariable("name") String name){
        return topicService.queryTopicByClassify(name);
    }

    @RequestMapping("/queryMyAttentionTopicList/{userid}")
    @ResponseBody
    public List<Topic> queryMyAttentionTopicList(@PathVariable("userid") int userid){
        return topicAttentionService.queryAttentionListByUser(userid);
    }

    @RequestMapping("/queryMyCreateTopicList/{userid}")
    @ResponseBody
    public List<Topic> queryMyCreateTopicList(@PathVariable("userid") int userid){
        return topicService.queryTopicByUser(userid);
    }

    @RequestMapping("/queryMyCommentTopicList/{userid}")
    @ResponseBody
    public List<Topic> queryMyCommentTopicList(@PathVariable("userid") int userid){
        return topicService.queryMyCommentTopic(userid);
    }

    @RequestMapping("/toMyTopic")
    public String toMyTopic(Model model){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("currentuserid",currentUser.getUserid());
        model.addAttribute("total_size",topicService.queryTopicByUser(currentUser.getUserid()).size());
        return "topic/MyTopic";
    }

    @RequestMapping("/queryMyTopicListInPage/{userid}/{page}/{size}")
    @ResponseBody
    List<Topic> queryMyTopicListInPage(@PathVariable("userid")int userid,@PathVariable("page") int page,@PathVariable("size") int size){
        return topicService.queryTopicByUserInPage(userid,page,size);
    }

    @RequestMapping("/toAdminister_Topic")
    public String toAdmin(Model model){
        model.addAttribute("total_size",topicService.queryTopicList().size());
        return "topic/Administer_Topic";
    }

    @RequestMapping("/queryTopicListInPage/{page}/{size}")
    @ResponseBody
    List<Topic> queryTopicListInPage(@PathVariable("page") int page,@PathVariable("size") int size){
        return topicService.queryTopicListInPage(page,size);
    }

}
