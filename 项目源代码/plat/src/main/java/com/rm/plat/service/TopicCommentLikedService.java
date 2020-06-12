package com.rm.plat.service;

import com.rm.plat.pojo.TopicComment;
import com.rm.plat.pojo.TopicCommentLiked;
import com.rm.plat.pojo.User;

import java.util.List;

public interface TopicCommentLikedService {
    boolean isLiked(int commentid, int userid);
    List<TopicComment> queryTopicCommentLikedByUser(int userid);
    List<User> queryTopicCommentLikedByComment(int commentid);


    int addTopicCommentLiked(TopicCommentLiked topicCommentLiked);
    int deleteTopicCommentLiked(int commentid,int userid);

    int cntByComment(int commentid);
}
