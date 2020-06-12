package com.rm.plat.mapper;


import com.rm.plat.pojo.BookScore;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookScoreMapper {
    BookScore getScoreByUserAndBook(int userid,int bookid);
    List<BookScore> queryBookScoreByBook(int bookid);

    int addBookScore(BookScore bookScore);
    int updateBookScore(BookScore bookScore);
    int deleteBookScore(int userid,int bookid);

    int cntByBook(int bookid);
}
