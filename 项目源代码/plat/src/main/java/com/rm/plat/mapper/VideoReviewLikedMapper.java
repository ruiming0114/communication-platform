package com.rm.plat.mapper;


import com.rm.plat.pojo.VideoReviewLiked;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VideoReviewLikedMapper {

    VideoReviewLiked getVideoReviewLikedByUserAndReview(int userid, int videoreviewid);
    int getLikeStatus(int videoreviewid);
    int getAgainstStatus(int videoreviewid);
    List<VideoReviewLiked> queryVideoReviewLikedByVideoReview(int videoreviewid);

    int addVideoReviewLiked(VideoReviewLiked videoReviewLiked);
    int updateVideoReviewLiked(VideoReviewLiked videoReviewLiked);
    int deleteVideoReviewLiked(int userid,int videoreviewid);
}