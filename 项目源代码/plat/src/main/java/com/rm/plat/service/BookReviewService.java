package com.rm.plat.service;

        import com.rm.plat.pojo.BookReview;

        import java.util.List;

public interface BookReviewService {
    BookReview queryBookReviewById(int bookreviewid);
    List<BookReview> queryBookReviewListByBookId(int bookid);
    List<BookReview> queryBookReviewListByUserId(int userid);

    List<BookReview> queryBookReviewListByUserId_Pageable(List<BookReview> list,int page,int size);
    List<BookReview> queryBookReviewListByBookId_Pageable(List<BookReview> list,int page,int size);

    int addBookReview(BookReview bookReview);
    int updateBookReview(BookReview bookReview);
    int deleteBookReview(int bookreviewid);
    int cnt();
    int max();
}
