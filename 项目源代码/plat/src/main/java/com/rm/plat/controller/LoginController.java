package com.rm.plat.controller;

import com.rm.plat.pojo.User;
import com.rm.plat.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){

        return "Login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        User user = userService.queryUserByName(username);
        if (user!=null && user.getPerms().equals("no")){
            model.addAttribute("msg","您的账号已被封禁");
            return "Login";
        }
        Subject subject = SecurityUtils.getSubject();
        //封装数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            return "redirect:/index";
        }catch (UnknownAccountException ae){
            model.addAttribute("msg","用户名不存在");
            return "Login";
        }catch (IncorrectCredentialsException ie){
            model.addAttribute("msg","密码错误");
            return "Login";
        }
    }

    @RequestMapping("/logout")
    public String logout(Model model){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        Session session = subject.getSession();
        model.addAttribute("loginUser",session);
        return "redirect:";
    }
}
