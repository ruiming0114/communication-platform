package com.rm.plat.controller;

import com.rm.plat.pojo.User;
import com.rm.plat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "Register";
    }

    @RequestMapping("/register")
    public String register(String username, String password, String sex, String birthday,String userimgpath,String signature,String email,String securityQ,String securityA){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setUserid(userService.max()+1);
        user.setPerms("user");
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
        user.setEmail(email);
        user.setUserimgpath(userimgpath);
        user.setSignature(signature);
        user.setSecurityA(securityA);
        user.setSecurityQ(securityQ);
        userService.addUser(user);
        return "redirect:";
    }

    @RequestMapping("/checkUser")
    @ResponseBody
    public void checkUser(HttpServletRequest request,HttpServletResponse response,@RequestParam("username") String username) throws IOException {
        PrintWriter out = response.getWriter();
        if (userService.queryUserByName(username) == null){
            out.write("true");
        }
        else{
            out.write("false");
        }
        out.close();
    }

}
