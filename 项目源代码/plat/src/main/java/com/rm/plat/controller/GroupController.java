package com.rm.plat.controller;

import com.rm.plat.pojo.*;
import com.rm.plat.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListResourceBundle;

@Controller
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    GroupMemberService groupMemberService;

    @Autowired
    GroupPostService groupPostService;

    @Autowired
    GroupPostReplyService groupPostReplyService;

    @Autowired
    GroupAdminApplyService groupAdminApplyService;

    @Autowired
    UserService userService;

    @RequestMapping("/queryRecommendGroupPost")
    @ResponseBody
    List<GroupPost> queryRecommendGroupPost(){
        return groupPostService.queryRecommendPost();
    }

    @RequestMapping("/grouppost/{grouppostid}")
    public String toGroupPost(@PathVariable("grouppostid") int grouppostid, Model model){
        model.addAttribute("currentPost",groupPostService.queryGroupPostById(grouppostid));
        if (groupPostService.queryGroupPostById(grouppostid).isTop()){
            model.addAttribute("top",1);
        }
        else {
            model.addAttribute("top",0);
        }
        if (groupPostService.queryGroupPostById(grouppostid).isEssence()){
            model.addAttribute("essence",1);
        }
        else {
            model.addAttribute("essence",0);
        }
        model.addAttribute("postuser",userService.queryUserByID(groupPostService.queryGroupPostById(grouppostid).getUserid()).getUsername());
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        if (currentUser!=null){
            model.addAttribute("currentuserid",currentUser.getUserid());
            model.addAttribute("currentuserperms",currentUser.getPerms());
            model.addAttribute("currentuserauthority",groupMemberService.getAuthority(groupPostService.queryGroupPostById(grouppostid).getGroupid(),currentUser.getUserid()));
        }
        else {
            model.addAttribute("currentuserid",-1);
            model.addAttribute("currentuserperms",-1);
            model.addAttribute("currentuserauthority",-1);
        }
        return "group/GroupPost";
    }

    @RequestMapping("/queryRecommendGroup")
    @ResponseBody
    List<Group> queryRecommendGroup(){
        return groupService.queryRecommendGroup();
    }

    @RequestMapping("/group/{groupid}")
    public String toGroup(@PathVariable("groupid") int groupid,Model model){
        model.addAttribute("currentgroup",groupService.queryGroupById(groupid));
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        if (currentUser!=null) {
            model.addAttribute("currentuserid", currentUser.getUserid());
            model.addAttribute("isjoin",groupMemberService.getAuthority(groupid,currentUser.getUserid()));
        }
        else {
            model.addAttribute("currentuserid", -1);
            model.addAttribute("isjoin",-1);
        }
        return "group/Group";
    }

    @RequestMapping("/queryGroupPost/{groupid}")
    @ResponseBody
    List<GroupPost> queryGroupPost(@PathVariable("groupid") int groupid){
        return groupPostService.queryGroupPostListByGroup(groupid);
    }

    @RequestMapping("/queryGroupPost_UserID_to_Username/{groupid}")
    @ResponseBody
    List<User> queryGroupPost_UserID_to_Username(@PathVariable("groupid") int groupid){
        List<GroupPost> list = groupPostService.queryGroupPostListByGroup(groupid);
        List<User> res = new ArrayList<>();
        for(GroupPost groupPost :list){
            res.add(userService.queryUserByID(groupPost.getUserid()));
        }
        return res;
    }

    @RequestMapping("/nologin_joingroup")
    public String nologin_joingroup(Model model){
        model.addAttribute("loginMsg","您还没有登录，请登录后再加入小组");
        return "Login";
    }

    @RequestMapping("/nologin_add")
    public String nologin_add(Model model){
        model.addAttribute("loginMsg","您还没有登录，请登录后再发帖");
        return "Login";
    }

    @RequestMapping("/nologin_addGroup")
    public String nologin_addgroup(Model model){
        model.addAttribute("loginMsg","您还没有登录，请登录后再创建小组");
        return "Login";
    }

    @RequestMapping("/queryGroupPost_getReplyNum/{groupid}")
    @ResponseBody
    List<Integer> queryGroupPost_getReplyNum(@PathVariable("groupid") int groupid){
        List<GroupPost> list = groupPostService.queryGroupPostListByGroup(groupid);
        List<Integer> res = new ArrayList<>();
        for(GroupPost groupPost :list){
            res.add(groupPostReplyService.cntByGroupPost(groupPost.getGrouppostid()));
        }
        return res;
    }

    @RequestMapping("/toAddGroupPost/{groupid}")
    public String toAddGroupPost(@PathVariable("groupid") int groupid,Model model){
        model.addAttribute("currentgroupid",groupid);
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("userid",currentUser.getUserid());

        return "group/AddGroupPost";
    }

    @RequestMapping("/toJoinGroup")
    public String toJoinGroup(@RequestParam("userid") int userid,@RequestParam("groupid") int groupid){
        GroupMember groupMember= new GroupMember();
        groupMember.setUserid(userid);
        groupMember.setGroupid(groupid);
        groupMember.setAuthority(0);
        groupMemberService.addGroupMember(groupMember);
        return "redirect:/group/"+groupid;
    }


    @RequestMapping("/toOutGroup")
    public String toOutGroup(@RequestParam("userid") int userid,@RequestParam("groupid") int groupid){
        groupMemberService.deleteGroupMember(groupid,userid);
        return "redirect:/group/"+groupid;
    }

    @RequestMapping("/AddGroupPost")
    public String AddGroupPost(@RequestParam("groupid") int groupid,@RequestParam("userid") int userid,@RequestParam("title") String title,@RequestParam("content") String content){
        GroupPost groupPost = new GroupPost();
        groupPost.setGrouppostid(groupPostService.max()+1);
        groupPost.setGroupid(groupid);
        groupPost.setTitle(title);
        groupPost.setContent(content);
        groupPost.setUserid(userid);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        groupPost.setAddtime(timeStamp);
        groupPost.setTop(false);
        groupPost.setEssence(false);
        groupPostService.addGroupPost(groupPost);
        return "redirect:/group/"+groupid;
    }

    @RequestMapping("/queryGroupPostReply/{grouppostid}")
    @ResponseBody
    public List<GroupPostReply>  queryGroupPostReply(@PathVariable("grouppostid") int grouppostid){
        return groupPostReplyService.queryGroupPostReplyByGroupPost(grouppostid);
    }

    @RequestMapping("/queryGroupPostReply_Userid_to_Username/{grouppostid}")
    @ResponseBody
    public List<User>  queryGroupPostReply_Userid_to_Username(@PathVariable("grouppostid") int grouppostid){
        List<GroupPostReply> list = groupPostReplyService.queryGroupPostReplyByGroupPost(grouppostid);
        List<User> res = new ArrayList<>();
        for(GroupPostReply groupPostReply:list){
            res.add(userService.queryUserByID(groupPostReply.getUserid()));
        }
        return res;
    }

    @RequestMapping("/AddGroupPostReply")
    public String AddGroupPostReply(@RequestParam("grouppostid") int grouppostid,@RequestParam("userid") int userid,@RequestParam("content") String content){
        GroupPostReply groupPostReply = new GroupPostReply();
        groupPostReply.setUserid(userid);
        groupPostReply.setGrouppostid(grouppostid);
        groupPostReply.setContent(content);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        groupPostReply.setAddtime(timeStamp);
        groupPostReply.setGrouppostreplyid(groupPostReplyService.max()+1);
        groupPostReplyService.addGroupPostReply(groupPostReply);
        return "redirect:/grouppost/"+grouppostid;
    }

    @RequestMapping("/DeleteGroupPostReply")
    public String DeleteGroupPostReply(@RequestParam("grouppostreplyid") int grouppostreplyid){
        int grouppostid=groupPostReplyService.queryGroupPostReplyById(grouppostreplyid).getGrouppostid();
        groupPostReplyService.deleteGroupPostReply(grouppostreplyid);
        return "redirect:/grouppost/"+grouppostid;
    }

    @RequestMapping("/DeleteGroupPost")
    public String DeleteGroupPost(@RequestParam("grouppostid") int grouppostid){
        int groupid=groupPostService.queryGroupPostById(grouppostid).getGroupid();
        groupPostService.deleteGroupPost(grouppostid);
        return "redirect:/group/"+groupid;
    }

    @RequestMapping("/toGroupMember/{groupid}")
    public String toGroupMember(@PathVariable("groupid") int groupid,Model model){
        model.addAttribute("currentgroup",groupService.queryGroupById(groupid));
        model.addAttribute("groupleader",groupMemberService.queryGroupLeaderByGroup(groupid));
        model.addAttribute("groupadmin",groupMemberService.queryGroupAdministerByGroup(groupid));
        model.addAttribute("groupmember",groupMemberService.queryGroupMemberByGroup(groupid));
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        if (currentUser==null){
            model.addAttribute("currentuserid",-1);
            model.addAttribute("currentuserauthority",-1);
        }
        else{
            model.addAttribute("currentuserid",currentUser.getUserid());
            model.addAttribute("currentuserauthority",groupMemberService.getAuthority(groupid,currentUser.getUserid()));
        }
        return "group/GroupMember";
    }

    @RequestMapping("/toApplyGroupAdmin/{groupid}" )
    public String toApplyGroupAdmin(@PathVariable("groupid") int groupid,Model model){
        model.addAttribute("groupid",groupid);
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("userid",currentUser.getUserid());
        return "group/GroupAdminApply";
    }

    @RequestMapping("/AddGroupAdminApply")
    public String AddGroupAdminApply(@RequestParam("userid") int userid,@RequestParam("groupid") int groupid,@RequestParam("content") String content){
        GroupAdminApply groupAdminApply= new GroupAdminApply();
        groupAdminApply.setApplyid(groupAdminApplyService.max()+1);
        groupAdminApply.setDeal(0);
        groupAdminApply.setUserid(userid);
        groupAdminApply.setGroupid(groupid);
        groupAdminApply.setApplication(content);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        groupAdminApply.setAddtime(timeStamp);
        groupAdminApplyService.addGroupAdminApply(groupAdminApply);
        return "redirect:/group/"+groupid;

    }

    @RequestMapping("/toAdminGroupMember/{groupid}")
    public String toAdminGroupMember(@PathVariable("groupid") int groupid, Model model){
        model.addAttribute("groupid",groupid);
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("userid",currentUser.getUserid());
        return "group/GroupAdmin";
    }

    @RequestMapping("/queryGroupAdminApplyList/{groupid}")
    @ResponseBody
    public List<GroupAdminApply> queryGroupAdminApplyList(@PathVariable("groupid") int groupid){
        return groupAdminApplyService.queryUndealGroupAdminApplyByGroup(groupid);
    }

    @RequestMapping("/queryGroupAdminApplyList_Userid_to_Username/{groupid}")
    @ResponseBody
    public List<User> queryGroupAdminApplyList_Userid_to_Username(@PathVariable("groupid") int groupid){
        List<GroupAdminApply> list = groupAdminApplyService.queryUndealGroupAdminApplyByGroup(groupid);
        List<User> res =  new ArrayList<>();
        for(GroupAdminApply groupAdminApply:list){
            res.add(userService.queryUserByID(groupAdminApply.getUserid()));
        }
        return res;
    }

    @RequestMapping("/passGroupAdminApply")
    public String passGroupAdminApply(@RequestParam("applyid") int applyid){
        GroupAdminApply groupAdminApply = groupAdminApplyService.queryGroupAdminApplyById(applyid);
        groupAdminApply.setDeal(1);
        groupAdminApplyService.updateGroupAdminApply(groupAdminApply);
        GroupMember groupMember = groupMemberService.queryGroupMember(groupAdminApply.getUserid(),groupAdminApply.getGroupid());
        groupMember.setAuthority(1);
        groupMemberService.updateGroupMember(groupMember);
        return "redirect:/toAdminGroupMember/"+groupAdminApply.getGroupid();
    }

    @RequestMapping("/failGroupAdminApply")
    public String failGroupAdminApply(@RequestParam("applyid") int applyid){
        GroupAdminApply groupAdminApply = groupAdminApplyService.queryGroupAdminApplyById(applyid);
        groupAdminApply.setDeal(2);
        groupAdminApplyService.updateGroupAdminApply(groupAdminApply);
        return "redirect:/toAdminGroupMember/"+groupAdminApply.getGroupid();
    }

    @RequestMapping("/queryGroupAdmin/{groupid}")
    @ResponseBody
    public List<User> queryGroupAdmin(@PathVariable("groupid") int groupid){
        return groupMemberService.queryGroupAdministerByGroup(groupid);
    }

    @RequestMapping("/queryGroupMember/{groupid}")
    @ResponseBody
    public List<User> queryGroupMember(@PathVariable("groupid") int groupid){
        return groupMemberService.queryGroupMemberByGroup(groupid);
    }

    @RequestMapping("/deleteGroupAdmin")
    public String deleteGroupAdmin(@RequestParam("groupid") int groupid,@RequestParam("userid") int userid) {
        GroupMember groupMember = groupMemberService.queryGroupMember(userid, groupid);
        groupMember.setAuthority(0);
        groupMemberService.updateGroupMember(groupMember);
        return "redirect:/toAdminGroupMember/" + groupid;
    }


    @RequestMapping("/deleteGroupMember")
    public String deleteGroupMember(@RequestParam("groupid") int groupid,@RequestParam("userid") int userid) {
      groupMemberService.deleteGroupMember(groupid,userid);
        return "redirect:/toAdminGroupMember/" + groupid;
    }

    @RequestMapping("/deleteGroup")
    public String deleteGroup(@RequestParam("groupid") int groupid) {
        groupService.deleteGroup(groupid);
        return "group/GroupIndex";
    }

    @RequestMapping("/deleteGroup_Admin")
    public String deleteGroup_Admin(@RequestParam("groupid") int groupid) {
        groupService.deleteGroup(groupid);
        return "redirect:/toAdminister_Group";
    }

    @RequestMapping("/AddTopPost")
    public String AddTopPost(@RequestParam("grouppostid") int grouppostid){
        GroupPost groupPost = groupPostService.queryGroupPostById(grouppostid);
        groupPost.setTop(true);
        groupPostService.updateGroupPost(groupPost);
        return "redirect:/grouppost/"+grouppostid;
    }

    @RequestMapping("/DeleteTopPost")
    public String DeleteTopPost(@RequestParam("grouppostid") int grouppostid){
        GroupPost groupPost = groupPostService.queryGroupPostById(grouppostid);
        groupPost.setTop(false);
        groupPostService.updateGroupPost(groupPost);
        return "redirect:/grouppost/"+grouppostid;
    }

    @RequestMapping("/AddEssencePost")
    public String AddEssencePost(@RequestParam("grouppostid") int grouppostid){
        GroupPost groupPost = groupPostService.queryGroupPostById(grouppostid);
        groupPost.setEssence(true);
        groupPostService.updateGroupPost(groupPost);
        return "redirect:/grouppost/"+grouppostid;
    }

    @RequestMapping("/DeleteEssencePost")
    public String DeleteEssencePost(@RequestParam("grouppostid") int grouppostid){
        GroupPost groupPost = groupPostService.queryGroupPostById(grouppostid);
        groupPost.setEssence(false);
        groupPostService.updateGroupPost(groupPost);
        return "redirect:/grouppost/"+grouppostid;
    }

    @RequestMapping("/toAddGroup")
    public String toAddGroup(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("userid", currentUser.getUserid());
        return "group/CreateGroup";
    }

    @RequestMapping("/AddGroup")
    public String AddGroup(@RequestParam("userid") int userid,@RequestParam("groupname") String groupname,@RequestParam("category") String category,@RequestParam("intro") String intro){
        Group group = new Group();
        group.setIntro(intro);
        group.setLeaderid(userid);
        group.setCategory(category);
        group.setGroupname(groupname);
        int id=groupService.max()+1;
        group.setGroupid(id);
        groupService.addGroup(group);
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupid(id);
        groupMember.setUserid(userid);
        groupMember.setAuthority(2);
        groupMemberService.addGroupMember(groupMember);
        return "redirect:/group/"+id;
    }

    @RequestMapping("/toCheckGroup/{name}")
    public String checkGroup(@PathVariable("name") String name,Model model){
        model.addAttribute("name",name);
        model.addAttribute("total_size",groupService.queryGroupByName(name).size());
        return "group/CheckGroup";
    }

    @RequestMapping("/queryGroupByName/{name}/{page}/{size}")
    @ResponseBody
    public List<Group> queryGroupByName(@PathVariable("name") String name,@PathVariable("page") int page,@PathVariable("size") int size){
        return groupService.queryGroupByNameInPage(name,page,size);
    }

    @RequestMapping("/toMyGroup")
    public String toMyGroup(Model model){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("total_size",groupPostService.queryGroupPostListByUser(currentUser.getUserid()).size());
        return "group/MyGroup";
    }

    @RequestMapping("/queryMyGroupPost/{page}/{size}")
    @ResponseBody
    public List<GroupPost> queryMyGroupPost(@PathVariable("page") int page,@PathVariable("size") int size){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        return groupPostService.queryGroupPostListByUserInPage(currentUser.getUserid(),page,size);
    }

    @RequestMapping("/queryMyGroupPost_GroupID_to_GroupName/{page}/{size}")
    @ResponseBody
    public List<Group> queryMyGroupPost_GroupID_to_GroupName(@PathVariable("page") int page,@PathVariable("size") int size){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        List<GroupPost> list =  groupPostService.queryGroupPostListByUserInPage(currentUser.getUserid(),page,size);
        List<Group> res= new ArrayList<>();
        for(GroupPost groupPost:list){
            res.add(groupService.queryGroupById(groupPost.getGroupid()));
        }
        return res;
    }

    @RequestMapping("/queryMyGroup")
    @ResponseBody
    public List<Group> queryMyGroup(){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        return groupMemberService.queryGroupListByUser(currentUser.getUserid());
    }

    @RequestMapping("/toAdminister_Group")
    public String toAdminister_Group(Model model){
        model.addAttribute("total_size",groupService.queryGroupList().size());
        return "group/Administer_Group";
    }

    @RequestMapping("/queryGroupListInPage/{page}/{size}")
    @ResponseBody
    public List<Group> queryGroupListInPage(@PathVariable("page") int page,@PathVariable("size") int size){
        return groupService.queryGroupListInPage(page,size);
    }
}
