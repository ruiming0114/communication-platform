package com.rm.plat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {
//
//    @RequestMapping({"/","index"})
//    public String index(){
//        return "index";
//    }
//
//    @RequestMapping("login")
//    public String login(){
//        return "login";
//    }

    @RequestMapping("/ok/{id}")
    public String index(@PathVariable("id")int id){
        return "view/"+id;
    }

    @RequestMapping("/manage")
    public String manage(){
        return "manage";
    }
}
