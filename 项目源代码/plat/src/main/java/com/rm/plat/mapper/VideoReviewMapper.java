package com.rm.plat.mapper;

import com.rm.plat.pojo.VideoReview;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VideoReviewMapper {
    VideoReview queryVideoReviewById(int videoreviewid);
    List<VideoReview> queryVideoReviewListByVideoId(int videoid);
    List<VideoReview> queryVideoReviewListByUserId(int userid);

    int addVideoReview(VideoReview videoReview);
    int updateVideoReview(VideoReview videoReview);
    int deleteVideoReview(int videoreviewid);
    int cnt();
    int max();
}
