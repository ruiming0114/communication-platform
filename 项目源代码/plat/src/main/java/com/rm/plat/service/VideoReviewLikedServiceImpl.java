package com.rm.plat.service;

import com.rm.plat.mapper.VideoReviewLikedMapper;
import com.rm.plat.pojo.VideoReviewLiked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoReviewLikedServiceImpl implements VideoReviewLikedService{

    @Autowired
    VideoReviewLikedMapper videoReviewLikedMapper;

    @Override
    public VideoReviewLiked getVideoReviewLikedByUserAndReview(int userid, int videoreviewid) {
        return videoReviewLikedMapper.getVideoReviewLikedByUserAndReview(userid,videoreviewid);
    }

    @Override
    public int getLikeStatus(int videoreviewid) {
        return videoReviewLikedMapper.getLikeStatus(videoreviewid);
    }

    @Override
    public int getAgainstStatus(int videoreviewid) {
        return videoReviewLikedMapper.getAgainstStatus(videoreviewid);
    }

    @Override
    public int addVideoReviewLiked(VideoReviewLiked videoReviewLiked) {
        videoReviewLikedMapper.addVideoReviewLiked(videoReviewLiked);
        return 1;
    }

    @Override
    public int updateVideoReviewLiked(VideoReviewLiked videoReviewLiked) {
        videoReviewLikedMapper.updateVideoReviewLiked(videoReviewLiked);
        return 1;
    }

    @Override
    public int deleteVideoReviewLiked(int userid, int videoreviewid) {
        videoReviewLikedMapper.deleteVideoReviewLiked(userid,videoreviewid);
        return 1;
    }

    @Override
    public List<VideoReviewLiked> queryVideoReviewLikedByVideoReview(int videoreviewid) {
        return videoReviewLikedMapper.queryVideoReviewLikedByVideoReview(videoreviewid);
    }
}
