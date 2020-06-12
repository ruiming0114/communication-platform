package com.rm.plat.service;

import com.rm.plat.pojo.Group;
import com.rm.plat.pojo.GroupMember;
import com.rm.plat.pojo.User;

import java.util.List;

public interface GroupMemberService {
    List<GroupMember> queryGroupMemberListByGroup(int groupid);
    List<Group> queryGroupListByUser(int userid);
    int getAuthority(int groupid,int userid);

    User queryGroupLeaderByGroup(int groupid);
    List<User> queryGroupAdministerByGroup(int groupid);
    List<User> queryGroupMemberByGroup(int groupid);

    GroupMember queryGroupMember(int userid,int groupid);

    int addGroupMember(GroupMember groupMember);
    int updateGroupMember(GroupMember groupMember);
    int deleteGroupMember(int groupid,int userid);

    int cntMember(int groupid);
}
