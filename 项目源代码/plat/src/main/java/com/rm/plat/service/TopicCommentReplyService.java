package com.rm.plat.service;

import com.rm.plat.pojo.TopicCommentReply;

import java.util.List;

public interface TopicCommentReplyService {

    TopicCommentReply queryTopicCommentReplyById(int commentreplyid);
    List<TopicCommentReply> queryTopicCommentReplyByComment(int commentid);

    int addTopicCommentReply(TopicCommentReply topicCommentReply);
    int updateTopicCommentReply(TopicCommentReply topicCommentReply);
    int deleteTopicCommentReply(int commentreplyid);

    int cnt();
    int max();

}
