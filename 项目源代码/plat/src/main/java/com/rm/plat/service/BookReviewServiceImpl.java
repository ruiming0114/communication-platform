package com.rm.plat.service;

import com.rm.plat.mapper.BookReviewLikedMapper;
import com.rm.plat.mapper.BookReviewMapper;
import com.rm.plat.mapper.BookReviewReportMapper;
import com.rm.plat.pojo.BookReview;
import com.rm.plat.pojo.BookReviewLiked;
import com.rm.plat.pojo.BookReviewReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookReviewServiceImpl implements BookReviewService {
    @Autowired
    BookReviewMapper bookReviewMapper;

    @Autowired
    BookReviewLikedMapper bookReviewLikedMapper;

    @Autowired
    BookReviewReportMapper bookReviewReportMapper;

    @Override
    public BookReview queryBookReviewById(int bookreviewid) {
        return bookReviewMapper.queryBookReviewById(bookreviewid);
    }

    @Override
    public List<BookReview> queryBookReviewListByBookId(int bookid) {
        return bookReviewMapper.queryBookReviewListByBookId(bookid);
    }

    @Override
    public List<BookReview> queryBookReviewListByUserId(int userid) {
        return bookReviewMapper.queryBookReviewListByUserId(userid);
    }

    @Override
    public int addBookReview(BookReview bookReview) {
        bookReviewMapper.addBookReview(bookReview);
        return 1;
    }

    @Override
    public int updateBookReview(BookReview bookReview) {
        bookReviewMapper.updateBookReview(bookReview);
        return 1;
    }

    @Override
    public int deleteBookReview(int bookreviewid) {
        BookReview bookReview = bookReviewMapper.queryBookReviewById(bookreviewid);
        List<BookReviewLiked> list1= bookReviewLikedMapper.queryBookReviewLikedByBookReview(bookReview.getBookreviewid());
        for (BookReviewLiked bookReviewLiked:list1){
            bookReviewLikedMapper.deleteBookReviewLiked(bookReviewLiked.getUserid(),bookReviewLiked.getBookreviewid());
        }
        List<BookReviewReport> list2=bookReviewReportMapper.queryBookReviewReportListByBookReview(bookreviewid);
        for (BookReviewReport bookReviewReport:list2){
            if (bookReviewReport.getDeal()!=1) {
                bookReviewReportMapper.deleteBookReviewReport(bookReviewReport.getBreportid());
            }
        }
        bookReviewMapper.deleteBookReview(bookreviewid);
        return 1;
    }

    @Override
    public int cnt() {
        return bookReviewMapper.cnt();
    }

    @Override
    public int max() {
        return bookReviewMapper.max();
    }

    @Override
    public List<BookReview> queryBookReviewListByBookId_Pageable(List<BookReview> list, int page, int size) {
        List<BookReview> res = new ArrayList<>();
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public List<BookReview> queryBookReviewListByUserId_Pageable(List<BookReview> list, int page, int size) {
        List<BookReview> res = new ArrayList<>();
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }
}
