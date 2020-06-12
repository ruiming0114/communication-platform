package com.rm.plat.service;

import com.rm.plat.pojo.VideoReview;

import java.util.List;

public interface VideoReviewService {
    VideoReview queryVideoReviewById(int videoreviewid);
    List<VideoReview> queryVideoReviewListByVideoId(int videoid);
    List<VideoReview> queryVideoReviewListByUserId(int userid);

    List<VideoReview> queryVideoReviewListByUserId_Pageable(List<VideoReview> list,int page,int size);
    List<VideoReview> queryVideoReviewListByVideoId_Pageable(List<VideoReview> list,int page,int size);

    int addVideoReview(VideoReview videoReview);
    int updateVideoReview(VideoReview videoReview);
    int deleteVideoReview(int videoreviewid);
    int cnt();
    int max();
}
