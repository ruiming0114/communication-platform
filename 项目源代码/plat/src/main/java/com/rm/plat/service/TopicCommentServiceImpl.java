package com.rm.plat.service;

import com.rm.plat.mapper.TopicCommentLikedMapper;
import com.rm.plat.mapper.TopicCommentMapper;
import com.rm.plat.mapper.TopicCommentReplyMapper;
import com.rm.plat.mapper.TopicCommentReportMapper;
import com.rm.plat.pojo.TopicComment;
import com.rm.plat.pojo.TopicCommentLiked;
import com.rm.plat.pojo.TopicCommentReply;
import com.rm.plat.pojo.TopicCommentReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicCommentServiceImpl implements TopicCommentService{

    @Autowired
    TopicCommentMapper topicCommentMapper;

    @Autowired
    TopicCommentReplyMapper topicCommentReplyMapper;

    @Autowired
    TopicCommentLikedMapper topicCommentLikedMapper;

    @Autowired
    TopicCommentReportMapper topicCommentReportMapper;

    @Override
    public TopicComment queryTopicCommentById(int commentid) {
        return topicCommentMapper.queryTopicCommentById(commentid);
    }

    @Override
    public List<TopicComment> queryTopicCommentByTopic(int topicid) {
        return topicCommentMapper.queryTopicCommentByTopic(topicid);
    }

    @Override
    public List<TopicComment> queryTopicCommentByUser(int userid) {
        return topicCommentMapper.queryTopicCommentByUser(userid);
    }

    @Override
    public int addTopicComment(TopicComment topicComment) {
        topicCommentMapper.addTopicComment(topicComment);
        return 1;
    }

    @Override
    public int updateTopicComment(TopicComment topicComment) {
        topicCommentMapper.updateTopicComment(topicComment);
        return 1;
    }

    @Override
    public int deleteTopicComment(int commentid) {
        List<TopicCommentReply> list = topicCommentReplyMapper.queryTopicCommentReplyByComment(commentid);
        for (TopicCommentReply topicCommentReply:list){
            topicCommentReplyMapper.deleteTopicCommentReply(topicCommentReply.getCommentreplyid());
        }
        List<TopicCommentLiked> list2 = topicCommentLikedMapper.queryTopicCommentLikedListByCommentID(commentid);
        for (TopicCommentLiked topicCommentLiked:list2){
            topicCommentLikedMapper.deleteTopicCommentLiked(topicCommentLiked.getCommentid(),topicCommentLiked.getUserid());
        }
        List<TopicCommentReport> list3 = topicCommentReportMapper.queryTopicCommentReportListByComment(commentid);
        for (TopicCommentReport topicCommentReport:list3){
            if (topicCommentReport.getDeal()!=1){
                topicCommentReportMapper.deleteTopicCommentReport(topicCommentReport.getTopiccommentreportid());
            }
        }
        topicCommentMapper.deleteTopicComment(commentid);
        return 1;
    }

    @Override
    public int cnt() {
        return topicCommentMapper.cnt();
    }

    @Override
    public int max() {
        return topicCommentMapper.max();
    }

    @Override
    public int cntByTopic(int topicid) {
        return topicCommentMapper.cntByTopic(topicid);
    }
}
