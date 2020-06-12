package com.rm.plat.service;

import com.rm.plat.pojo.Book;
import com.rm.plat.pojo.Topic;

import java.util.List;

public interface TopicService {

    Topic queryTopicById(int topicid);
    Topic queryTopicByTitle(String title);
    List<Topic> queryTopicByClassify(String classify);
    List<Topic> queryTopicByUser(int userid);
    List<Topic> queryTopicList();
    List<Topic> queryHotTopicList();
    List<Topic> queryHotTopicList5();
    List<Topic> queryNewTopicList();

    List<Topic> queryTopicListInPage(int page, int size);
    List<Topic> queryTopicByUserInPage(int userid, int page, int size);
    List<Topic> queryMyCommentTopic(int userid);
    List<Topic> queryMyCommentTopic3(int userid);
    List<Topic> queryTopicByName(String name);

    int addTopic(Topic topic);
    int updateTopic(Topic topic);
    int deleteTopic(int topicid);

    int cnt();
    int max();
}
