package com.rm.plat.service;

import com.rm.plat.pojo.Topic;
import com.rm.plat.pojo.TopicAttention;
import com.rm.plat.pojo.User;

import java.util.List;

public interface TopicAttentionService {

    boolean isAttention(int topicid,int userid);
    List<User> queryAttentionListByTopic(int topicid);
    List<Topic> queryAttentionListByUser(int userid);

    int addTopicAttention(TopicAttention topicAttention);
    int deleteTopicAttention(int topicid,int userid);
}
