package com.rm.plat.service;

import com.rm.plat.pojo.Video;
import com.rm.plat.pojo.Video;

import java.util.List;

public interface VideoService {
    List<Video> queryVideoByName(String name);
    List<Video> queryVideoByNameInPage(String name,int page,int size);

    List<Video> queryMyVideo(int userid);
    List<Video> queryVideoList();
    List<Video> queryVideoListInPage(int page,int size);
    Video queryVideoByID(int videoid);
    List<Video> queryRecommendVideoList5();
    List<Video> queryHotVideoList();
    List<Video> queryRecommendVideoList();
    List<Video> queryNewVideoList();

    int addVideo(Video video);
    int updateVideo(Video video);
    int deleteVideo(int videoid);
    int cnt();
    int max();
}
