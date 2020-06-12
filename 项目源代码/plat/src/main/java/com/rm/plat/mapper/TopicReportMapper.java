package com.rm.plat.mapper;

import com.rm.plat.pojo.TopicReport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicReportMapper {

    TopicReport queryTopicReportById(int topicreportid);
    List<TopicReport> queryTopicReportList();
    List<TopicReport> queryUndealTopicReportList();
    List<TopicReport> queryTopicReportListByUser(int userid);

    List<TopicReport> queryTopicReportByTopic(int topicid);

    int addTopicReport(TopicReport topicReport);
    int updateTopicReport(TopicReport topicReport);
    int deleteTopicReport(int topicreportid);

    int cnt();
    int max();

}
