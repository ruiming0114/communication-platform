package com.rm.plat.mapper;


import com.rm.plat.pojo.Topic;
import com.rm.plat.pojo.TopicAttention;
import com.rm.plat.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicAttentionMapper {

     TopicAttention isAttention(int topicid,int userid);
     List<User> queryAttentionListByTopic(int topicid);
     List<Topic> queryAttentionListByUser(int userid);

     List<TopicAttention> queryAttentionListByTopicid(int topicid);

     int addTopicAttention(TopicAttention topicAttention);
     int deleteTopicAttention(int topicid,int userid);

}
