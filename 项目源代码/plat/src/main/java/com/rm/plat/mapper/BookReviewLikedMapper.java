package com.rm.plat.mapper;


import com.rm.plat.pojo.BookReview;
import com.rm.plat.pojo.BookReviewLiked;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookReviewLikedMapper {

    BookReviewLiked getBookReviewLikedByUserAndReview(int userid, int bookreviewid);
    int getLikeStatus(int bookreviewid);
    int getAgainstStatus(int bookreviewid);
    List<BookReviewLiked> queryBookReviewLikedByBookReview(int bookreviewid);

    int addBookReviewLiked(BookReviewLiked bookReviewLiked);
    int updateBookReviewLiked(BookReviewLiked bookReviewLiked);
    int deleteBookReviewLiked(int userid,int bookreviewid);
}
