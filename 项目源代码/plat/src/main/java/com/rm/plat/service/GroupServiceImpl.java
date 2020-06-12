package com.rm.plat.service;


import com.rm.plat.mapper.GroupMapper;
import com.rm.plat.mapper.GroupPostMapper;
import com.rm.plat.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    GroupMapper groupMapper;

    @Autowired
    GroupPostService groupPostService;

    @Autowired
    GroupMemberService groupMemberService;

    @Autowired
    GroupAdminApplyService groupAdminApplyService;

    @Override
    public List<Group> queryRecommendGroup() {
        List list =  groupMapper.queryRecommendGroup();
        List list1 = groupMapper.queryGroupList();
        int i=0;
        while (list.size()<8 && i<list1.size()){
            if (!list.contains(list1.get(i))){
                list.add(list1.get(i));
            }
            i++;
        }
        return list;
    }

    @Override
    public List<Group> queryGroupByNameInPage(String name,int page, int size) {
        List<Group> res = new ArrayList<>();
        List<Group> list =groupMapper.queryGroupByName(name);
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public Group queryGroupById(int groupid) {
        return groupMapper.queryGroupById(groupid);
    }

    @Override
    public List<Group> queryGroupByName(String groupname) {
        return groupMapper.queryGroupByName(groupname);
    }

    @Override
    public List<Group> queryGroupList() {
        return groupMapper.queryGroupList();
    }

    @Override
    public int addGroup(Group group) {
        groupMapper.addGroup(group);
        return 1;
    }

    @Override
    public int updateGroup(Group group) {
        groupMapper.updateGroup(group);
        return 1;
    }

    @Override
    public int deleteGroup(int groupid) {
        List<GroupPost> list = groupPostService.queryGroupPostListByGroup(groupid);
        for(GroupPost groupPost:list){
            groupPostService.deleteGroupPost(groupPost.getGrouppostid());
        }
        List<GroupMember> list1 = groupMemberService.queryGroupMemberListByGroup(groupid);
        for(GroupMember groupMember:list1){
            groupMemberService.deleteGroupMember(groupMember.getGroupid(),groupMember.getUserid());
        }
        List<GroupAdminApply> list2 =groupAdminApplyService.queryGroupAdminApplyByGroup(groupid);
        for (GroupAdminApply groupAdminApply:list2){
            groupAdminApplyService.deleteGroupAdminApply(groupAdminApply);
        }

        groupMapper.deleteGroup(groupid);
        return 1;
    }

    @Override
    public List<Group> queryNewGroup() {
        return groupMapper.queryNewGroup();
    }

    @Override
    public List<Group> queryRecommendGroup5() {
        List<Group> list = groupMapper.queryRecommendGroup();
        while(list.size()<=5){
            list.add(null);
        }
        return list.subList(0,5);
    }

    @Override
    public List<Group> queryGroupListInPage(int page, int size) {
        List<Group> res = new ArrayList<>();
        List<Group> list =groupMapper.queryGroupList();
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public List<Group> queryRecommendGroupIn5() {
        return groupMapper.queryRecommendGroupIn5();
    }

    @Override
    public int cnt() {
        return groupMapper.cnt();
    }

    @Override
    public int max() {
        return groupMapper.max();
    }
}
