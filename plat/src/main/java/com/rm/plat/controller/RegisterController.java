package com.rm.plat.controller;

import com.rm.plat.pojo.User;
import com.rm.plat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/register")
    public String register(String username, String password, Model model){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserid(userService.cnt()+1);
        user.setPerms("user");
        user.setSex(0);
        user.setBirthday(null);
        user.setEmail(null);
        user.setUserimgpath(null);
        user.setSignature(null);
        user.setSecurityA("1");
        user.setSecurityQ("1");
        userService.addUser(user);
        model.addAttribute("msg","注册成功");
        return "redirect:";
    }


}
