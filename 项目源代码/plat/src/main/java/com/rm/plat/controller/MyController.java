package com.rm.plat.controller;

import com.rm.plat.service.BookService;
import com.rm.plat.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @Autowired
    BookService bookService;

    @Autowired
    VideoService videoService;


    @RequestMapping("/noauth")
    public String unauthorized( Model model){
        model.addAttribute("authorizeMsg","您的权限不够，只有管理员才可以进入，请重新进入个人主页展示内容！");
        return "PersonalIndex";
    }
}
