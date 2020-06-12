package com.rm.plat.mapper;

import com.rm.plat.pojo.VideoReviewReport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VideoReviewReportMapper {

    VideoReviewReport queryVideoReviewReportById(int vreportid);
    List<VideoReviewReport> queryVideoReviewReportList();
    List<VideoReviewReport> queryUndealVideoReviewReportList();
    List<VideoReviewReport> queryVideoReviewReportListByVideoReview(int videoreviewid);

    int addVideoReviewReport(VideoReviewReport videoReviewReport);
    int updateVideoReviewReport(VideoReviewReport videoReviewReport);
    int deleteVideoReviewReport(int videoReviewReport);

    int cnt();
    int max();
}
