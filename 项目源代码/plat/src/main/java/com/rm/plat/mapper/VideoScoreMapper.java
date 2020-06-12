package com.rm.plat.mapper;


import com.rm.plat.pojo.VideoScore;
import com.rm.plat.pojo.VideoScore;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VideoScoreMapper {
    VideoScore getScoreByUserAndVideo(int userid, int videoid);
    List<VideoScore> queryVideoScoreByVideo(int videoid);

    int addVideoScore(VideoScore videoScore);
    int updateVideoScore(VideoScore videoScore);
    int deleteVideoScore(int userid,int videoid);

    int cntByVideo(int videoid);
}
