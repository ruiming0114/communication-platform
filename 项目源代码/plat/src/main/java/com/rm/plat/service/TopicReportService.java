package com.rm.plat.service;

import com.rm.plat.pojo.TopicReport;

import java.util.List;

public interface TopicReportService {
    TopicReport queryTopicReportById(int topicreportid);
    List<TopicReport> queryTopicReportList();
    List<TopicReport> queryUndealTopicReportList();
    List<TopicReport> queryTopicReportListByUser(int userid);

    int addTopicReport(TopicReport topicReport);
    int updateTopicReport(TopicReport topicReport);
    int deleteTopicReport(int topicreportid);

    int cnt();
    int max();
}
