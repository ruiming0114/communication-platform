package com.rm.plat.service;


import com.rm.plat.mapper.TopicCommentLikedMapper;
import com.rm.plat.pojo.TopicComment;
import com.rm.plat.pojo.TopicCommentLiked;
import com.rm.plat.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicCommentLikedServiceImpl implements TopicCommentLikedService {

    @Autowired
    TopicCommentLikedMapper topicCommentLikedMapper;

    @Override
    public boolean isLiked(int commentid, int userid) {
        return topicCommentLikedMapper.isLiked(commentid,userid)!=null;
    }

    @Override
    public List<TopicComment> queryTopicCommentLikedByUser(int userid) {
        return topicCommentLikedMapper.queryTopicCommentLikedByUser(userid);
    }

    @Override
    public List<User> queryTopicCommentLikedByComment(int commentid) {
        return topicCommentLikedMapper.queryTopicCommentLikedByComment(commentid);
    }

    @Override
    public int addTopicCommentLiked(TopicCommentLiked topicCommentLiked) {
        topicCommentLikedMapper.addTopicCommentLiked(topicCommentLiked);
        return 1;
    }

    @Override
    public int deleteTopicCommentLiked(int commentid, int userid) {
        topicCommentLikedMapper.deleteTopicCommentLiked(commentid,userid);
        return 1;
    }

    @Override
    public int cntByComment(int commentid) {
        return topicCommentLikedMapper.cntByComment(commentid);
    }

}
