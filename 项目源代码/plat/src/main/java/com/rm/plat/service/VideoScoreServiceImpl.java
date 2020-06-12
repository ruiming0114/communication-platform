package com.rm.plat.service;

import com.rm.plat.mapper.VideoMapper;
import com.rm.plat.mapper.VideoScoreMapper;
import com.rm.plat.pojo.Video;
import com.rm.plat.pojo.VideoScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoScoreServiceImpl implements VideoScoreService {

    @Autowired
    VideoScoreMapper videoScoreMapper;

    @Autowired
    VideoMapper videoMapper;

    @Override
    public float getScoreByUserAndVideo(int userid, int videoid) {
        VideoScore videoScore = videoScoreMapper.getScoreByUserAndVideo(userid,videoid);
        if (videoScore==null) {
            return 0;
        }
        else {
            return videoScore.getScore();
        }
    }

    @Override
    public float getAvgScoreByVideo(int videoid) {
        List<VideoScore> list = videoScoreMapper.queryVideoScoreByVideo(videoid);
        float sum = 0;
        if (list.size()!=0){
            for (VideoScore videoScore : list){
                sum+=videoScore.getScore();
            }
            sum/=list.size();
        }
        return sum;
    }

    @Override
    public int changeVideoScore(int userid, int videoid,float value) {
        VideoScore videoScore = new VideoScore();
        videoScore.setVideoid(videoid);
        videoScore.setScore(value);
        videoScore.setUserid(userid);
        if (getScoreByUserAndVideo(userid,videoid)==0){
            videoScoreMapper.addVideoScore(videoScore);
        }
        else {
            videoScoreMapper.deleteVideoScore(userid,videoid);
            videoScoreMapper.addVideoScore(videoScore);
        }
        Video video = videoMapper.queryVideoByID(videoid);
        video.setTotalscore(getAvgScoreByVideo(videoid));
        videoMapper.updateVideo(video);
        return 1;
    }

    @Override
    public int deleteVideoScore(int userid, int videoid) {
        videoScoreMapper.deleteVideoScore(userid,videoid);
        return 1;
    }

    @Override
    public int cntByVideo(int videoid) {
        return videoScoreMapper.cntByVideo(videoid);
    }


}
