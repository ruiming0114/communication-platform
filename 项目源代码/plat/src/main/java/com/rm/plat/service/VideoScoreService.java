package com.rm.plat.service;

import com.rm.plat.pojo.VideoScore;

public interface VideoScoreService {
    float getScoreByUserAndVideo(int userid,int videoid);
    float getAvgScoreByVideo(int videoid);

    int changeVideoScore(int userid,int videoid,float value);
    int deleteVideoScore(int userid,int videoid);

    int cntByVideo(int videoid);
}
