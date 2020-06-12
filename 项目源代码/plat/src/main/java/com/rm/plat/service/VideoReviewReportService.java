package com.rm.plat.service;

import com.rm.plat.pojo.VideoReviewReport;

import java.util.List;

public interface VideoReviewReportService {

    VideoReviewReport queryVideoReviewReportById(int vreportid);
    List<VideoReviewReport> queryVideoReviewReportList();
    List<VideoReviewReport> queryUndealVideoReviewReportList();

    int addVideoReviewReport(VideoReviewReport videoReviewReport);
    int updateVideoReviewReport(VideoReviewReport videoReviewReport);
    int deleteVideoReviewReport(int videoReviewReport);

    int cnt();
    int max();
}