package com.rm.plat.service;

import com.rm.plat.pojo.BookReviewLiked;

import java.util.List;

public interface BookReviewLikedService {
    BookReviewLiked getBookReviewLikedByUserAndReview(int userid,int bookreviewid);
    int getLikeStatus(int bookreviewid);
    int getAgainstStatus(int bookreviewid);
    List<BookReviewLiked> queryBookReviewLikedByBookReview(int bookreviewid);

    int addBookReviewLiked(BookReviewLiked bookReviewLiked);
    int updateBookReviewLiked(BookReviewLiked bookReviewLiked);
    int deleteBookReviewLiked(int userid,int bookreviewid);

}
