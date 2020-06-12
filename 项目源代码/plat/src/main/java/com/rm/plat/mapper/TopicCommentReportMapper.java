package com.rm.plat.mapper;

import com.rm.plat.pojo.TopicCommentReport;
import com.rm.plat.pojo.TopicReport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicCommentReportMapper {
    TopicCommentReport queryTopicCommentReportById(int topiccommentreportid);
    List<TopicCommentReport> queryTopicCommentReportList();
    List<TopicCommentReport> queryUndealTopicCommentReportList();
    List<TopicCommentReport> queryTopicCommentReportListByUser(int userid);
    List<TopicCommentReport> queryTopicCommentReportListByComment(int commentid);

    int addTopicCommentReport(TopicCommentReport topicCommentReport);
    int updateTopicCommentReport(TopicCommentReport topicCommentReport);
    int deleteTopicCommentReport(int topiccommentreportid);

    int cnt();
    int max();
}
