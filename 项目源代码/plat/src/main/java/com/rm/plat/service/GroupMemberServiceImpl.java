package com.rm.plat.service;

import com.rm.plat.mapper.GroupMemberMapper;
import com.rm.plat.pojo.Group;
import com.rm.plat.pojo.GroupMember;
import com.rm.plat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupMemberServiceImpl implements GroupMemberService {

    @Autowired
    GroupMemberMapper groupMemberMapper;

    @Autowired
    UserService userService;

    @Autowired
    GroupService groupService;

    @Override
    public List<GroupMember> queryGroupMemberListByGroup(int groupid) {
        return groupMemberMapper.queryGroupMemberListByGroup(groupid);
    }

    @Override
    public List<Group> queryGroupListByUser(int userid) {
        return groupMemberMapper.queryGroupListByUser(userid);
    }

    @Override
    public int getAuthority(int groupid, int userid) {
        return groupMemberMapper.getAuthority(groupid,userid);
    }

    @Override
    public int addGroupMember(GroupMember groupMember) {
        groupMemberMapper.addGroupMember(groupMember);
        return 1;
    }

    @Override
    public int updateGroupMember(GroupMember groupMember) {
        groupMemberMapper.updateGroupMember(groupMember);
        return 1 ;
    }

    @Override
    public int deleteGroupMember(int groupid, int userid) {
        groupMemberMapper.deleteGroupMember(groupid,userid);
        return 1;
    }

    @Override
    public int cntMember(int groupid) {
        return groupMemberMapper.cntMember(groupid);
    }

    @Override
    public User queryGroupLeaderByGroup(int groupid) {
        return userService.queryUserByID(groupService.queryGroupById(groupid).getLeaderid());
    }

    @Override
    public List<User> queryGroupAdministerByGroup(int groupid) {
       List<GroupMember> list = groupMemberMapper.queryGroupMemberListByGroup(groupid);
       List<User> res = new ArrayList<>();
       for(GroupMember groupMember:list) {
           if (groupMember.getAuthority()==1){
               res.add(userService.queryUserByID(groupMember.getUserid()));
           }
       }
       return res;
    }

    @Override
    public List<User> queryGroupMemberByGroup(int groupid) {
        List<GroupMember> list = groupMemberMapper.queryGroupMemberListByGroup(groupid);
        List<User> res = new ArrayList<>();
        for(GroupMember groupMember:list) {
            if (groupMember.getAuthority()==0){
                res.add(userService.queryUserByID(groupMember.getUserid()));
            }
        }
        return res;
    }

    @Override
    public GroupMember queryGroupMember(int userid, int groupid) {
        return groupMemberMapper.queryGroupMember(groupid,userid);
    }
}
