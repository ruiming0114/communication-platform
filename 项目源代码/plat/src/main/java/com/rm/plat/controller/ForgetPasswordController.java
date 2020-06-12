package com.rm.plat.controller;

import com.rm.plat.pojo.User;
import com.rm.plat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgetPasswordController {

    @Autowired
    UserService userService;

    @RequestMapping("/toForgetPassword")
    public String toForgetPassword(){
        return "ForgetPassword";
    }

    @RequestMapping("/ForgetPassword")
    public String ForgetPassword( @RequestParam("username") String username, @RequestParam("email") String email, Model model) {
        User user = userService.queryUserByName(username);
        if (user==null){
            model.addAttribute("ForgetPwdMsg0","用户名不存在，请重新输入");
            return "ForgetPassword";
        }
        else if (!user.getEmail().equals(email)){
            model.addAttribute("ForgetPwdMsg0","安全邮箱错误，请重新输入");
            return "ForgetPassword";
        }
        else {
            model.addAttribute("ForgetPwdMsg1",user.getSecurityQ());
            model.addAttribute("ForgetPwdUser",user.getUsername());
            return "ForgetPassword2";
        }
    }

    @RequestMapping("/ForgetPassword2")
    public String ForgetPassword2( @RequestParam("securityA") String securityA,@RequestParam("username") String username, Model model) {
        User user = userService.queryUserByName(username);
        if (user.getSecurityA().equals(securityA)){
            model.addAttribute("ForgetPwdUser",user.getUsername());
            return "ForgetPassword3";
        }
        else {
            model.addAttribute("ForgetPwdMsg2","安全问题回答错误，请重新输入");
            model.addAttribute("ForgetPwdMsg1",user.getSecurityQ());
            model.addAttribute("ForgetPwdUser",user.getUsername());
            return "ForgetPassword2";
        }
    }

    @RequestMapping("/ForgetPassword3")
    public String ForgetPassword3( @RequestParam("password") String password,@RequestParam("username") String username) {
        User user = userService.queryUserByName(username);
        user.setPassword(password);
        userService.updateUser(user);
        return "Index";
    }

}
