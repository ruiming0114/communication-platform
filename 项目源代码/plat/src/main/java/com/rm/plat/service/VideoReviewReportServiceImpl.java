package com.rm.plat.service;

import com.rm.plat.mapper.VideoReviewReportMapper;
import com.rm.plat.pojo.VideoReviewLiked;
import com.rm.plat.pojo.VideoReviewReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoReviewReportServiceImpl implements VideoReviewReportService{

    @Autowired
    VideoReviewReportMapper videoReviewReportMapper;

    @Override
    public VideoReviewReport queryVideoReviewReportById(int vreportid) {
        return videoReviewReportMapper.queryVideoReviewReportById(vreportid);
    }

    @Override
    public List<VideoReviewReport> queryVideoReviewReportList() {
        return videoReviewReportMapper.queryVideoReviewReportList();
    }

    @Override
    public List<VideoReviewReport> queryUndealVideoReviewReportList() {
        return videoReviewReportMapper.queryUndealVideoReviewReportList();
    }

    @Override
    public int addVideoReviewReport(VideoReviewReport videoReviewReport) {
        videoReviewReportMapper.addVideoReviewReport(videoReviewReport);
        return 1;
    }

    @Override
    public int updateVideoReviewReport(VideoReviewReport videoReviewReport) {
        videoReviewReportMapper.updateVideoReviewReport(videoReviewReport);
        return 1;
    }

    @Override
    public int deleteVideoReviewReport(int vreportid) {
        videoReviewReportMapper.deleteVideoReviewReport(vreportid);
        return 1;
    }

    @Override
    public int cnt() {
        return videoReviewReportMapper.cnt();
    }

    @Override
    public int max() {
        return videoReviewReportMapper.max();
    }
}
