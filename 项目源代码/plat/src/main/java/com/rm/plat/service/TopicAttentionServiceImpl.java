package com.rm.plat.service;

import com.rm.plat.mapper.TopicAttentionMapper;
import com.rm.plat.pojo.Topic;
import com.rm.plat.pojo.TopicAttention;
import com.rm.plat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicAttentionServiceImpl  implements TopicAttentionService{

    @Autowired
    TopicAttentionMapper topicAttentionMapper;

    @Override
    public boolean isAttention(int topicid, int userid) {

        return topicAttentionMapper.isAttention(topicid, userid) != null;
    }

    @Override
    public List<User> queryAttentionListByTopic(int topicid) {
        return topicAttentionMapper.queryAttentionListByTopic(topicid);
    }

    @Override
    public List<Topic> queryAttentionListByUser(int userid) {
        return topicAttentionMapper.queryAttentionListByUser(userid);
    }

    @Override
    public int addTopicAttention(TopicAttention topicAttention) {
        topicAttentionMapper.addTopicAttention(topicAttention);
        return 1;
    }

    @Override
    public int deleteTopicAttention(int topicid, int userid) {
        topicAttentionMapper.deleteTopicAttention(topicid,userid);
        return 1;
    }
}
