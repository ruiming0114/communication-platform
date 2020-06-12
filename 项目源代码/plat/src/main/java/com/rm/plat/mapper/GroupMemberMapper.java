package com.rm.plat.mapper;

import com.rm.plat.pojo.Group;
import com.rm.plat.pojo.GroupMember;
import com.rm.plat.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupMemberMapper {

    List<GroupMember> queryGroupMemberListByGroup(int groupid);
    List<Group> queryGroupListByUser(int userid);
    int getAuthority(int groupid,int userid);

    GroupMember queryGroupMember (int groupid,int userid);

    int addGroupMember(GroupMember groupMember);
    int updateGroupMember(GroupMember groupMember);
    int deleteGroupMember(int groupid,int userid);

    int cntMember(int groupid);
}
