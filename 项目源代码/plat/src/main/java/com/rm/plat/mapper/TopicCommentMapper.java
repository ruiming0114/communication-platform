package com.rm.plat.mapper;

import com.rm.plat.pojo.Topic;
import com.rm.plat.pojo.TopicComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicCommentMapper {

    TopicComment queryTopicCommentById(int commentid);
    List<TopicComment> queryTopicCommentByTopic(int topicid);
    List<TopicComment> queryTopicCommentByUser(int userid);

    int addTopicComment(TopicComment topicComment);
    int updateTopicComment(TopicComment topicComment);
    int deleteTopicComment(int commentid);

    int cnt();
    int max();
    int cntByTopic(int topicid);
}
