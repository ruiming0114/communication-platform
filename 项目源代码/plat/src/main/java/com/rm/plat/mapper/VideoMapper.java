package com.rm.plat.mapper;

import com.rm.plat.pojo.Video;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoMapper {
    List<Video> queryVideoByName(String name);

    List<Video> queryVideoList();
    List<Video> queryNewVideoList();
    List<Video> queryHotVideoList();
    List<Video> queryRecommendVideoList();

    Video queryVideoByID(int videoid);

    int addVideo(Video video);
    int updateVideo(Video video);
    int deleteVideo(int videoid);
    int cnt();
    int max();
}