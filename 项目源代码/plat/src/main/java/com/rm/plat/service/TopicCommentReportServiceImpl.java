package com.rm.plat.service;

import com.rm.plat.mapper.TopicCommentReportMapper;
import com.rm.plat.pojo.TopicCommentReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicCommentReportServiceImpl implements TopicCommentReportService{

    @Autowired
    TopicCommentReportMapper topicCommentReportMapper;

    @Override
    public TopicCommentReport queryTopicCommentReportById(int topiccommentreportid) {
        return topicCommentReportMapper.queryTopicCommentReportById(topiccommentreportid);
    }

    @Override
    public List<TopicCommentReport> queryTopicCommentReportList() {
        return topicCommentReportMapper.queryTopicCommentReportList();
    }

    @Override
    public List<TopicCommentReport> queryUndealTopicCommentReportList() {
        return topicCommentReportMapper.queryUndealTopicCommentReportList();
    }

    @Override
    public List<TopicCommentReport> queryTopicCommentReportListByUser(int userid) {
        return topicCommentReportMapper.queryTopicCommentReportListByUser(userid);
    }

    @Override
    public int addTopicCommentReport(TopicCommentReport topicCommentReport) {
        topicCommentReportMapper.addTopicCommentReport(topicCommentReport);
        return 1;
    }

    @Override
    public int updateTopicCommentReport(TopicCommentReport topicCommentReport) {
        topicCommentReportMapper.updateTopicCommentReport(topicCommentReport);
        return 1;
    }

    @Override
    public int deleteTopicCommentReport(int topiccommentreportid) {
        topicCommentReportMapper.deleteTopicCommentReport(topiccommentreportid);
        return 1;
    }

    @Override
    public int cnt() {
        return topicCommentReportMapper.cnt();
    }

    @Override
    public int max() {
        return topicCommentReportMapper.max();
    }
}
