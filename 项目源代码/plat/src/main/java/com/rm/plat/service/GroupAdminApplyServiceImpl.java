package com.rm.plat.service;

import com.rm.plat.mapper.GroupAdminApplyMapper;
import com.rm.plat.pojo.GroupAdminApply;
import com.rm.plat.pojo.GroupPostReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupAdminApplyServiceImpl implements GroupAdminApplyService {

    @Autowired
    GroupAdminApplyMapper adminApplyMapper;

    @Override
    public GroupAdminApply queryGroupAdminApplyById(int applyid) {
        return adminApplyMapper.queryGroupAdminApplyById(applyid);
    }

    @Override
    public List<GroupAdminApply> queryGroupAdminApplyByGroup(int groupid) {
        return adminApplyMapper.queryGroupAdminApplyByGroup(groupid);
    }

    @Override
    public List<GroupAdminApply> queryUndealGroupAdminApplyByGroup(int groupid) {
        return adminApplyMapper.queryUndealGroupAdminApplyByGroup(groupid);
    }

    @Override
    public List<GroupAdminApply> queryGroupAdminApplyByUser(int userid) {
        return adminApplyMapper.queryGroupAdminApplyByUser(userid);
    }

    @Override
    public int addGroupAdminApply(GroupAdminApply groupAdminApply) {
        adminApplyMapper.addGroupAdminApply(groupAdminApply);
        return 1;
    }

    @Override
    public int updateGroupAdminApply(GroupAdminApply groupAdminApply) {
        adminApplyMapper.updateGroupAdminApply(groupAdminApply);
        return 1;
    }

    @Override
    public int deleteGroupAdminApply(GroupAdminApply groupAdminApply) {
        adminApplyMapper.deleteGroupAdminApply(groupAdminApply);
        return 1;
    }

    @Override
    public int cnt() {
        return adminApplyMapper.cnt();
    }

    @Override
    public int max() {
        return adminApplyMapper.max();
    }
}
