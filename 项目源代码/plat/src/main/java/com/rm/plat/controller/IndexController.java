package com.rm.plat.controller;

import com.rm.plat.pojo.User;
import com.rm.plat.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;
    
    @Autowired
    VideoService videoService;

    @Autowired
    TopicService topicService;

    @Autowired
    GroupService groupService;

    @RequestMapping({"/","index"})
    public String index(Model model){
        model.addAttribute("newbooklist",bookService.queryNewBookList());
        model.addAttribute("hotbooklist",bookService.queryHotBookList());
        model.addAttribute("recommendbooklist",bookService.queryRecommendBookList());

        model.addAttribute("recommendbook1",bookService.queryRecommendBookList5().get(0));
        model.addAttribute("recommendbook2",bookService.queryRecommendBookList5().get(1));
        model.addAttribute("recommendbook3",bookService.queryRecommendBookList5().get(2));
        model.addAttribute("recommendbook4",bookService.queryRecommendBookList5().get(3));
        model.addAttribute("recommendbook5",bookService.queryRecommendBookList5().get(4));

        model.addAttribute("newvideolist",videoService.queryNewVideoList());
        model.addAttribute("hotvideolist",videoService.queryHotVideoList());
        model.addAttribute("recommendvideolist",videoService.queryRecommendVideoList());

        model.addAttribute("recommendvideo1",videoService.queryRecommendVideoList5().get(0));
        model.addAttribute("recommendvideo2",videoService.queryRecommendVideoList5().get(1));
        model.addAttribute("recommendvideo3",videoService.queryRecommendVideoList5().get(2));
        model.addAttribute("recommendvideo4",videoService.queryRecommendVideoList5().get(3));
        model.addAttribute("recommendvideo5",videoService.queryRecommendVideoList5().get(4));

        model.addAttribute("hottopiclist",topicService.queryHotTopicList());
        model.addAttribute("newtopiclist",topicService.queryNewTopicList());
        model.addAttribute("recommendtopic1",topicService.queryHotTopicList5().get(0));
        model.addAttribute("recommendtopic2",topicService.queryHotTopicList5().get(1));
        model.addAttribute("recommendtopic3",topicService.queryHotTopicList5().get(2));
        model.addAttribute("recommendtopic4",topicService.queryHotTopicList5().get(3));
        model.addAttribute("recommendtopic5",topicService.queryHotTopicList5().get(4));

        model.addAttribute("recommendgroup1",groupService.queryRecommendGroup5().get(0));
        model.addAttribute("recommendgroup2",groupService.queryRecommendGroup5().get(1));
        model.addAttribute("recommendgroup3",groupService.queryRecommendGroup5().get(2));
        model.addAttribute("recommendgroup4",groupService.queryRecommendGroup5().get(3));
        model.addAttribute("recommendgroup5",groupService.queryRecommendGroup5().get(4));
        model.addAttribute("recommendgrouplist",groupService.queryRecommendGroupIn5());
        model.addAttribute("newgrouplist",groupService.queryNewGroup());
        return "Index";
    }

    @RequestMapping("/AboutUs")
    public String aboutUs(){
        return "AboutUs";
    }

    @RequestMapping("/toAlterInfo")
    public String toAlterInfo(){
        return "AlterInfo";
    }

    @RequestMapping("/toAlterPassword")
    public String toAlterPassword(){return "alterPassword";}

    @RequestMapping("/alterInfo")
    public String alterInfo(String email , String sex, String birthday, String signature, String securityQ, String securityA, String username, Model model){
        User user =userService.queryUserByName(username);
        user.setEmail(email);
        try {
            user.setSex(Integer.parseInt(sex));
        }catch (Exception e){
            user.setSex(0);
        }
        try {
            user.setBirthday(java.sql.Date.valueOf(birthday));
        }catch (Exception e){
            user.setBirthday(null);
        }
        user.setSignature(signature);
        user.setSecurityQ(securityQ);
        user.setSecurityA(securityA);
        userService.updateUser(user);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        model.addAttribute("loginUser",session);
        return "Login";
    }

    @RequestMapping("/AlterPassword")
    public String AlterPassword(HttpServletResponse response,@RequestParam("password") String password,@RequestParam("username") String username,@RequestParam("oldpwd") String oldpwd,@RequestParam("pwd2") String pwd2,Model model) throws IOException {
        User user = userService.queryUserByName(username);
        if (!oldpwd.equals(user.getPassword())) {
            model.addAttribute("AlterPwdMsg", "输入旧密码错误，请重新输入！");
            return "alterPassword";
        } else if (password.length() < 6 || password.length() > 12) {
            model.addAttribute("AlterPwdMsg", "新密码为6至12个字符，请重新输入！");
            return "alterPassword";
        } else if (!password.equals(pwd2)) {
            model.addAttribute("AlterPwdMsg", "两次密码输入不一致，请检查并重新输入!");
            return "alterPassword";
        } else {
            user.setPassword(password);
            userService.updateUser(user);
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            Session session = subject.getSession();
            model.addAttribute("loginUser", session);
            model.addAttribute("AlterPwdMsg1", "修改密码成功，请重新登录！");
            return "Login";
        }
    }

    @RequestMapping("/toCheckAll/{name}")
    public String toCheckAll(@PathVariable("name") String name,Model model){
        model.addAttribute("name",name);
        return "CheckAll";
    }



}
