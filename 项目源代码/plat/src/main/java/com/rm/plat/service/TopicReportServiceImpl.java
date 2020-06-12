package com.rm.plat.service;

import com.rm.plat.mapper.TopicReportMapper;
import com.rm.plat.pojo.TopicReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicReportServiceImpl implements TopicReportService{

    @Autowired
    TopicReportMapper topicReportMapper;

    @Override
    public TopicReport queryTopicReportById(int topicreportid) {
        return topicReportMapper.queryTopicReportById(topicreportid);
    }

    @Override
    public List<TopicReport> queryTopicReportList() {
        return topicReportMapper.queryTopicReportList();
    }

    @Override
    public List<TopicReport> queryUndealTopicReportList() {
        return topicReportMapper.queryUndealTopicReportList();
    }

    @Override
    public List<TopicReport> queryTopicReportListByUser(int userid) {
        return topicReportMapper.queryTopicReportListByUser(userid);
    }

    @Override
    public int addTopicReport(TopicReport topicReport) {
        topicReportMapper.addTopicReport(topicReport);
        return 1;
    }

    @Override
    public int updateTopicReport(TopicReport topicReport) {
        topicReportMapper.updateTopicReport(topicReport);
        return 1;
    }

    @Override
    public int deleteTopicReport(int topicreportid) {
        topicReportMapper.deleteTopicReport(topicreportid);
        return 1;
    }

    @Override
    public int cnt() {
        return topicReportMapper.cnt();
    }

    @Override
    public int max() {
        return topicReportMapper.max();
    }
}
