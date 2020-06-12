package com.rm.plat.mapper;

import com.rm.plat.pojo.TopicComment;
import com.rm.plat.pojo.TopicCommentLiked;
import com.rm.plat.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TopicCommentLikedMapper {

    TopicCommentLiked isLiked(int commentid,int userid);
    List<TopicComment> queryTopicCommentLikedByUser(int userid);
    List<User> queryTopicCommentLikedByComment(int commentid);

    List<TopicCommentLiked> queryTopicCommentLikedListByCommentID(int commentid);

    int addTopicCommentLiked(TopicCommentLiked topicCommentLiked);
    int deleteTopicCommentLiked(int commentid,int userid);

    int cntByComment(int commentid);
}
