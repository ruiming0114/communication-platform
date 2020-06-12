package com.rm.plat.service;

import com.rm.plat.mapper.*;
import com.rm.plat.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoMapper videoMapper;

    @Autowired
    VideoReviewMapper videoReviewMapper;

    @Autowired
    VideoReviewLikedMapper videoReviewLikedMapper;

    @Autowired
    VideoReviewReportMapper videoReviewReportMapper;

    @Autowired
    VideoScoreMapper videoScoreMapper;
    
    @Autowired
    VideoReviewService videoReviewService;

    @Override
    public List<Video> queryVideoByName(String name) {
        return videoMapper.queryVideoByName(name);
    }

    @Override
    public List<Video> queryVideoList() {
        return videoMapper.queryVideoList();
    }

    @Override
    public Video queryVideoByID(int videoid) {
        return videoMapper.queryVideoByID(videoid);
    }

    @Override
    public int addVideo(Video video) {
        videoMapper.addVideo(video);
        return 1;
    }

    @Override
    public int updateVideo(Video video) {
        videoMapper.updateVideo(video);
        return 1;
    }

    @Override
    public int deleteVideo(int videoid) {
        List<VideoReview> list = videoReviewMapper.queryVideoReviewListByVideoId(videoid);
        for (VideoReview videoReview:list){
            List<VideoReviewLiked> l2 = videoReviewLikedMapper.queryVideoReviewLikedByVideoReview(videoReview.getVideoreviewid());
            for (VideoReviewLiked videoReviewLiked:l2){
                videoReviewLikedMapper.deleteVideoReviewLiked(videoReviewLiked.getUserid(),videoReviewLiked.getVideoreviewid());
            }
            List<VideoReviewReport> l3 = videoReviewReportMapper.queryVideoReviewReportListByVideoReview(videoReview.getVideoreviewid());
            for (VideoReviewReport videoReviewReport:l3) {
                if (videoReviewReport.getDeal()!=1) {
                    videoReviewReportMapper.deleteVideoReviewReport(videoReviewReport.getVreportid());
                }
            }
            videoReviewMapper.deleteVideoReview(videoReview.getVideoreviewid());
        }
        List<VideoScore> list1 = videoScoreMapper.queryVideoScoreByVideo(videoid);
        for (VideoScore videoScore:list1){
            videoScoreMapper.deleteVideoScore(videoScore.getUserid(),videoScore.getVideoid());
        }
        videoMapper.deleteVideo(videoid);
        return 1;
    }

    @Override
    public int cnt() {
        return videoMapper.cnt();
    }

    @Override
    public int max() {
        return videoMapper.max();
    }

    @Override
    public List<Video> queryHotVideoList() {
        return videoMapper.queryHotVideoList();
    }

    @Override
    public List<Video> queryRecommendVideoList() {
        return videoMapper.queryRecommendVideoList();
    }

    @Override
    public List<Video> queryVideoListInPage(int page, int size) {
        List<Video> res = new ArrayList<>();
        List<Video> list = videoMapper.queryVideoList();
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public List<Video> queryVideoByNameInPage(String name,int page, int size) {
        List<Video> res = new ArrayList<>();
        List<Video> list = videoMapper.queryVideoByName(name);
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public List<Video> queryNewVideoList() {
        return videoMapper.queryNewVideoList();
    }

    @Override
    public List<Video> queryRecommendVideoList5() {
        List<Video> res = videoMapper.queryRecommendVideoList();
        if (res.size()>5){
            return res.subList(0,5);
        }
        else if (res.size()==5){
            return res;
        }
        else {
            while (res.size()<5){
                Video video = null;
                res.add(video);
            }
            return res;
        }

    }

    @Override
    public List<Video> queryMyVideo(int userid) {
        List<VideoReview> list = videoReviewService.queryVideoReviewListByUserId(userid);
        List<Video> res = new ArrayList<>();
        for (VideoReview videoReview:list){
            res.add(queryVideoByID(videoReview.getVideoid()));
        }
        List<Video> res2 = new ArrayList<>();
        for (Video video : res) {
            if (!res2.contains(video)) {
                res2.add(video);
            }
        }
        if (res2.size()>5){
            res2=res2.subList(0,5);
        }
        return res2;
    }
}
