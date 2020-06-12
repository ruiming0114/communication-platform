package com.rm.plat.mapper;

import com.rm.plat.pojo.BookReview;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookReviewMapper {
    BookReview queryBookReviewById(int bookreviewid);
    List<BookReview> queryBookReviewListByBookId(int bookid);
    List<BookReview> queryBookReviewListByUserId(int userid);

    int addBookReview(BookReview bookReview);
    int updateBookReview(BookReview bookReview);
    int deleteBookReview(int bookreviewid);
    int cnt();
    int max();
}
