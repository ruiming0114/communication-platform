package com.rm.plat.service;

import com.rm.plat.pojo.TopicCommentReport;

import java.util.List;

public interface TopicCommentReportService {

    TopicCommentReport queryTopicCommentReportById(int topiccommentreportid);
    List<TopicCommentReport> queryTopicCommentReportList();
    List<TopicCommentReport> queryUndealTopicCommentReportList();
    List<TopicCommentReport> queryTopicCommentReportListByUser(int userid);

    int addTopicCommentReport(TopicCommentReport topicCommentReport);
    int updateTopicCommentReport(TopicCommentReport topicCommentReport);
    int deleteTopicCommentReport(int topiccommentreportid);

    int cnt();
    int max();
}
