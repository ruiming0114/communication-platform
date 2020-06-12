package com.rm.plat.service;

import com.rm.plat.pojo.VideoReviewLiked;
import com.rm.plat.pojo.VideoReviewLiked;

import java.util.List;

public interface VideoReviewLikedService {
    VideoReviewLiked getVideoReviewLikedByUserAndReview(int userid, int videoreviewid);
    int getLikeStatus(int videoreviewid);
    int getAgainstStatus(int videoreviewid);
    List<VideoReviewLiked> queryVideoReviewLikedByVideoReview(int videoreviewid);

    int addVideoReviewLiked(VideoReviewLiked videoReviewLiked);
    int updateVideoReviewLiked(VideoReviewLiked videoReviewLiked);
    int deleteVideoReviewLiked(int userid,int videoreviewid);


}
