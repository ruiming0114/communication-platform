package com.rm.plat.mapper;

import com.rm.plat.pojo.TopicCommentReply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicCommentReplyMapper {

    TopicCommentReply queryTopicCommentReplyById(int commentreplyid);
    List<TopicCommentReply> queryTopicCommentReplyByComment(int commentid);

    int addTopicCommentReply(TopicCommentReply topicCommentReply);
    int updateTopicCommentReply(TopicCommentReply topicCommentReply);
    int deleteTopicCommentReply(int commentreplyid);

    int cnt();
    int max();
}
