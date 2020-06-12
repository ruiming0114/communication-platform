package com.rm.plat.service;

import com.rm.plat.pojo.TopicComment;

import java.util.List;

public interface TopicCommentService {
    TopicComment queryTopicCommentById(int commentid);
    List<TopicComment> queryTopicCommentByTopic(int topicid);
    List<TopicComment> queryTopicCommentByUser(int userid);

    int addTopicComment(TopicComment topicComment);
    int updateTopicComment(TopicComment topicComment);
    int deleteTopicComment(int commentid);

    int cnt();
    int max();
    int cntByTopic(int topicid);
}
