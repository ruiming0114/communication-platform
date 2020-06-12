package com.rm.plat.controller;

import com.rm.plat.mapper.UserMapper;
import com.rm.plat.pojo.*;
import com.rm.plat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UserController {

    @Autowired
    BookService bookService;

    @Autowired
    BookReviewService bookReviewService;

    @Autowired
    VideoService videoService;

    @Autowired
    TopicService topicService;

    @Autowired
    GroupService groupService;

    @Autowired
    GroupMemberService groupMemberService;


    @Autowired
    private UserService userService;

    @RequestMapping("/queryUserList")
    @ResponseBody
    public List<User> queryUserList(){
        List<User> userList = userService.queryUserList();
        for (User user:userList){
            System.out.println(user);
        }
        return userList;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("username") String username){
        User user = userService.queryUserByName(username);
        if (user!=null){
            userService.deleteUser(user.getUserid());
            return "temp/ok";
        }
        else {
            return "temp/no";
        }
    }

    @RequestMapping("/toMyPersonalIndex/{userid}")
    public String toMyPersonalIndex(Model model,@PathVariable("userid") int userid){
        model.addAttribute("mybooklist",bookService.queryMyBook(userid));
        model.addAttribute("myvideolist",videoService.queryMyVideo(userid));
        model.addAttribute("mytopiclist",topicService.queryMyCommentTopic3(userid));
        model.addAttribute("mygrouplist",groupMemberService.queryGroupListByUser(userid).subList(0,Math.min(6,groupMemberService.queryGroupListByUser(userid).size())));
        return "PersonalIndex";
    }

    @RequestMapping("/toAdminister_User")
    public String toAdminister_User(Model model){
        model.addAttribute("total_size",userService.queryUserList().size()-1);
        return "Administer_User";
    }

    @RequestMapping("/queryUserListInPage/{page}/{size}")
    @ResponseBody
    public List<User> queryUserListInPage(@PathVariable("page") int page,@PathVariable("size") int size){
        return userService.queryUserListInPage(page,size);
    }

    @RequestMapping("/delete_user_admin")
    public String delete(@RequestParam("userid") int userid){
        User user = userService.queryUserByID(userid);
        user.setPerms("no");
        userService.updateUser(user);
        return "redirect:/toAdminister_User";
    }

}
