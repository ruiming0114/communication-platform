package com.rm.plat.service;

import com.rm.plat.mapper.GroupPostMapper;
import com.rm.plat.mapper.GroupPostReplyMapper;
import com.rm.plat.pojo.Group;
import com.rm.plat.pojo.GroupPost;
import com.rm.plat.pojo.GroupPostReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupPostServiceImpl implements GroupPostService {

    @Autowired
    GroupPostMapper groupPostMapper;

    @Autowired
    GroupPostReplyMapper groupPostReplyMapper;


    @Override
    public GroupPost queryGroupPostById(int grouppostid) {
        return groupPostMapper.queryGroupPostById(grouppostid);
    }

    @Override
    public List<GroupPost> queryRecommendPost() {
        return groupPostMapper.queryRecommendPost();
    }

    @Override
    public List<GroupPost> queryGroupPostListByGroup(int groupid) {
        return groupPostMapper.queryGroupPostListByGroup(groupid);
    }

    @Override
    public List<GroupPost> queryGroupPostListByUser(int userid) {
        return groupPostMapper.queryGroupPostListByUser(userid);
    }

    @Override
    public int addGroupPost(GroupPost groupPost) {
        groupPostMapper.addGroupPost(groupPost);
        return 1;
    }

    @Override
    public int updateGroupPost(GroupPost groupPost) {
        groupPostMapper.updateGroupPost(groupPost);
        return 1;
    }

    @Override
    public int deleteGroupPost(int grouppostid) {
        List<GroupPostReply> list = groupPostReplyMapper.queryGroupPostReplyByGroupPost(grouppostid);
        for (GroupPostReply groupPostReply:list){
            groupPostReplyMapper.deleteGroupPostReply(groupPostReply.getGrouppostreplyid());
        }
        groupPostMapper.deleteGroupPost(grouppostid);
        return 1;
    }

    @Override
    public List<GroupPost> queryGroupPostListByUserInPage(int userid, int page, int size) {
        List<GroupPost> res = new ArrayList<>();
        List<GroupPost> list =groupPostMapper.queryGroupPostListByUser(userid);
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public int cnt() {
        return groupPostMapper.cnt();
    }

    @Override
    public int max() {
        return groupPostMapper.max();
    }
}
