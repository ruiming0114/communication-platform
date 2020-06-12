package com.rm.plat.service;

import com.rm.plat.pojo.BookScore;

public interface BookScoreService {

    float getScoreByUserAndBook(int userid,int bookid);
    float getAvgScoreByBook(int bookid);

    int changeBookScore(int userid,int bookid,float value);
    int deleteBookScore(int userid,int bookid);

    int cntByBook(int bookid);
}
