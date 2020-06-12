package com.rm.plat.service;

import com.rm.plat.mapper.VideoReviewLikedMapper;
import com.rm.plat.mapper.VideoReviewMapper;
import com.rm.plat.mapper.VideoReviewReportMapper;
import com.rm.plat.pojo.VideoReview;
import com.rm.plat.pojo.VideoReviewLiked;
import com.rm.plat.pojo.VideoReviewReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoReviewServiceImpl implements VideoReviewService {
    @Autowired
    VideoReviewMapper videoReviewMapper;

    @Autowired
    VideoReviewLikedMapper videoReviewLikedMapper;

    @Autowired
    VideoReviewReportMapper videoReviewReportMapper;

    @Override
    public VideoReview queryVideoReviewById(int videoreviewid) {
        return videoReviewMapper.queryVideoReviewById(videoreviewid);
    }

    @Override
    public List<VideoReview> queryVideoReviewListByVideoId(int videoid) {
        return videoReviewMapper.queryVideoReviewListByVideoId(videoid);
    }

    @Override
    public List<VideoReview> queryVideoReviewListByUserId(int userid) {
        return videoReviewMapper.queryVideoReviewListByUserId(userid);
    }

    @Override
    public int addVideoReview(VideoReview videoReview) {
        videoReviewMapper.addVideoReview(videoReview);
        return 1;
    }

    @Override
    public int updateVideoReview(VideoReview videoReview) {
        videoReviewMapper.updateVideoReview(videoReview);
        return 1;
    }

    @Override
    public int deleteVideoReview(int videoreviewid) {
        VideoReview videoReview = videoReviewMapper.queryVideoReviewById(videoreviewid);
        List<VideoReviewLiked> list1= videoReviewLikedMapper.queryVideoReviewLikedByVideoReview(videoReview.getVideoreviewid());
        for (VideoReviewLiked videoReviewLiked:list1){
            videoReviewLikedMapper.deleteVideoReviewLiked(videoReviewLiked.getUserid(),videoReviewLiked.getVideoreviewid());
        }
        List<VideoReviewReport> list2=videoReviewReportMapper.queryVideoReviewReportListByVideoReview(videoreviewid);
        for (VideoReviewReport videoReviewReport:list2){
            if (videoReviewReport.getDeal()!=1) {
                videoReviewReportMapper.deleteVideoReviewReport(videoReviewReport.getVreportid());
            }
        }
        videoReviewMapper.deleteVideoReview(videoreviewid);
        return 1;
    }

    @Override
    public int cnt() {
        return videoReviewMapper.cnt();
    }

    @Override
    public int max() {
        return videoReviewMapper.max();
    }

    @Override
    public List<VideoReview> queryVideoReviewListByVideoId_Pageable(List<VideoReview> list, int page, int size) {
        List<VideoReview> res = new ArrayList<>();
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public List<VideoReview> queryVideoReviewListByUserId_Pageable(List<VideoReview> list, int page, int size) {
        List<VideoReview> res = new ArrayList<>();
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }
}

