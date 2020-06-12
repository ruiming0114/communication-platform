package com.rm.plat.service;

import com.rm.plat.mapper.GroupPostReplyMapper;
import com.rm.plat.pojo.GroupPostReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupPostReplyServiceImpl implements GroupPostReplyService{

    @Autowired
    GroupPostReplyMapper groupPostReplyMapper;

    @Override
    public GroupPostReply queryGroupPostReplyById(int grouppostreplyid) {
        return groupPostReplyMapper.queryGroupPostReplyById(grouppostreplyid);
    }

    @Override
    public List<GroupPostReply> queryGroupPostReplyByGroupPost(int grouppostid) {
        return groupPostReplyMapper.queryGroupPostReplyByGroupPost(grouppostid);
    }

    @Override
    public List<GroupPostReply> queryGroupPostReplyByUser(int userid) {
        return groupPostReplyMapper.queryGroupPostReplyByUser(userid);
    }

    @Override
    public int addGroupPostReply(GroupPostReply groupPostReply) {
        groupPostReplyMapper.addGroupPostReply(groupPostReply);
        return 1;
    }

    @Override
    public int updateGroupPostReply(GroupPostReply groupPostReply) {
        groupPostReplyMapper.updateGroupPostReply(groupPostReply);
        return 1;
    }

    @Override
    public int deleteGroupPostReply(int grouppostreplyid) {
        groupPostReplyMapper.deleteGroupPostReply(grouppostreplyid);
        return 1;
    }

    @Override
    public int cnt() {
        return groupPostReplyMapper.cnt();
    }

    @Override
    public int max() {
        return groupPostReplyMapper.max();
    }

    @Override
    public int cntByGroupPost(int grouppostid) {
        return groupPostReplyMapper.cntByGroupPost(grouppostid);
    }
}
