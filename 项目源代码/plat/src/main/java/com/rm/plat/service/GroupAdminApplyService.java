package com.rm.plat.service;

import com.rm.plat.pojo.GroupAdminApply;
import com.rm.plat.pojo.GroupPostReply;

import java.util.List;

public interface GroupAdminApplyService {
    GroupAdminApply queryGroupAdminApplyById(int applyid);
    List<GroupAdminApply> queryGroupAdminApplyByGroup(int groupid);
    List<GroupAdminApply> queryUndealGroupAdminApplyByGroup(int groupid);
    List<GroupAdminApply> queryGroupAdminApplyByUser(int userid);

    int addGroupAdminApply( GroupAdminApply groupAdminApply);
    int updateGroupAdminApply(GroupAdminApply groupAdminApply);
    int deleteGroupAdminApply(GroupAdminApply groupAdminApply);

    int cnt();
    int max();
}
