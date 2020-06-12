package com.rm.plat.service;

import com.rm.plat.mapper.TopicCommentReplyMapper;
import com.rm.plat.pojo.TopicCommentReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicCommentReplyServiceImpl implements TopicCommentReplyService{

    @Autowired
    TopicCommentReplyMapper topicCommentReplyMapper;

    @Override
    public TopicCommentReply queryTopicCommentReplyById(int commentreplyid) {
        return topicCommentReplyMapper.queryTopicCommentReplyById(commentreplyid);
    }

    @Override
    public List<TopicCommentReply> queryTopicCommentReplyByComment(int commentid) {
        return topicCommentReplyMapper.queryTopicCommentReplyByComment(commentid);
    }

    @Override
    public int addTopicCommentReply(TopicCommentReply topicCommentReply) {
        topicCommentReplyMapper.addTopicCommentReply(topicCommentReply);
        return 1;
    }

    @Override
    public int updateTopicCommentReply(TopicCommentReply topicCommentReply) {
        topicCommentReplyMapper.updateTopicCommentReply(topicCommentReply);
        return 1;
    }

    @Override
    public int deleteTopicCommentReply(int commentreplyid) {
        topicCommentReplyMapper.deleteTopicCommentReply(commentreplyid);
        return 1;
    }

    @Override
    public int cnt() {
        return topicCommentReplyMapper.cnt();
    }

    @Override
    public int max() {
        return topicCommentReplyMapper.max();
    }
}
