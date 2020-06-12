package com.rm.plat.mapper;

import com.rm.plat.pojo.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicMapper {

    Topic queryTopicById(int topicid);
    Topic queryTopicByTitle(String title);
    List<Topic> queryTopicByClassify(String classify);
    List<Topic> queryTopicByUser(int userid);
    List<Topic> queryTopicList();

    List<Topic> queryNewTopicList();
    List<Topic> queryHotTopicList();

    List<Topic> queryTopicByName(String name);

    int addTopic(Topic topic);
    int updateTopic(Topic topic);
    int deleteTopic(int topicid);

    int cnt();
    int max();
}
