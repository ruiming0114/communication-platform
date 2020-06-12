package com.rm.plat.service;

import com.rm.plat.pojo.GroupPostReply;

import java.util.List;

public interface GroupPostReplyService {
    GroupPostReply queryGroupPostReplyById(int grouppostreplyid);
    List<GroupPostReply> queryGroupPostReplyByGroupPost(int grouppostid);
    List<GroupPostReply> queryGroupPostReplyByUser(int userid);

    int addGroupPostReply(GroupPostReply groupPostReply);
    int updateGroupPostReply(GroupPostReply groupPostReply);
    int deleteGroupPostReply(int grouppostreplyid);

    int cnt();
    int max();
    int cntByGroupPost(int grouppostid);
}
