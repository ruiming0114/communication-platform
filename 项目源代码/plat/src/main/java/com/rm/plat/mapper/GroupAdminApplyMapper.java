package com.rm.plat.mapper;

import com.rm.plat.pojo.GroupAdminApply;
import com.rm.plat.pojo.GroupPostReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GroupAdminApplyMapper {

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
