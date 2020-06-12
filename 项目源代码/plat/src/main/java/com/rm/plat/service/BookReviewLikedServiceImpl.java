package com.rm.plat.service;

import com.rm.plat.mapper.BookReviewLikedMapper;
import com.rm.plat.pojo.BookReviewLiked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReviewLikedServiceImpl implements BookReviewLikedService{

    @Autowired
    BookReviewLikedMapper bookReviewLikedMapper;

    @Override
    public BookReviewLiked getBookReviewLikedByUserAndReview(int userid, int bookreviewid) {
        return bookReviewLikedMapper.getBookReviewLikedByUserAndReview(userid,bookreviewid);
    }

    @Override
    public int getLikeStatus(int bookreviewid) {
        return bookReviewLikedMapper.getLikeStatus(bookreviewid);
    }

    @Override
    public int getAgainstStatus(int bookreviewid) {
        return bookReviewLikedMapper.getAgainstStatus(bookreviewid);
    }

    @Override
    public int addBookReviewLiked(BookReviewLiked bookReviewLiked) {
        bookReviewLikedMapper.addBookReviewLiked(bookReviewLiked);
        return 1;
    }

    @Override
    public int updateBookReviewLiked(BookReviewLiked bookReviewLiked) {
        bookReviewLikedMapper.updateBookReviewLiked(bookReviewLiked);
        return 1;
    }

    @Override
    public int deleteBookReviewLiked(int userid, int bookreviewid) {
        bookReviewLikedMapper.deleteBookReviewLiked(userid,bookreviewid);
        return 1;
    }

    @Override
    public List<BookReviewLiked> queryBookReviewLikedByBookReview(int bookreviewid) {
        return bookReviewLikedMapper.queryBookReviewLikedByBookReview(bookreviewid);
    }
}
