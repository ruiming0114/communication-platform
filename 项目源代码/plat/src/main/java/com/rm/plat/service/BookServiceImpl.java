package com.rm.plat.service;

import com.rm.plat.mapper.*;
import com.rm.plat.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookMapper bookMapper;

    @Autowired
    BookReviewMapper bookReviewMapper;

    @Autowired
    BookReviewLikedMapper bookReviewLikedMapper;

    @Autowired
    BookReviewReportMapper bookReviewReportMapper;

    @Autowired
    BookScoreMapper bookScoreMapper;

    @Autowired
    BookReviewService bookReviewService;

    @Override
    public List<Book> queryBookByName(String name) {
        return bookMapper.queryBookByName(name);
    }

    @Override
    public List<Book> queryBookList() {
        return bookMapper.queryBookList();
    }

    @Override
    public Book queryBookByID(int bookid) {
        return bookMapper.queryBookByID(bookid);
    }

    @Override
    public int addBook(Book book) {
        bookMapper.addBook(book);
        return 1;
    }

    @Override
    public int updateBook(Book book) {
        bookMapper.updateBook(book);
        return 1;
    }

    @Override
    public List<Book> queryMyBook(int userid) {
        List<BookReview> list = bookReviewService.queryBookReviewListByUserId(userid);
        List<Book> res = new ArrayList<>();
        for (BookReview bookReview:list){
            res.add(queryBookByID(bookReview.getBookid()));
        }
        List<Book> res2 = new ArrayList<>();
        for (Book book : res) {
            if (!res2.contains(book)) {
                res2.add(book);
            }
        }
        if (res2.size()>5){
            res2=res2.subList(0,5);
        }
        return res2;
    }

    @Override
    public int deleteBook(int bookid) {
        List<BookReview> list = bookReviewMapper.queryBookReviewListByBookId(bookid);
        for (BookReview bookReview:list){
            List<BookReviewLiked> l2 = bookReviewLikedMapper.queryBookReviewLikedByBookReview(bookReview.getBookreviewid());
            for (BookReviewLiked bookReviewLiked:l2){
                bookReviewLikedMapper.deleteBookReviewLiked(bookReviewLiked.getUserid(),bookReviewLiked.getBookreviewid());
            }
            List<BookReviewReport> l3 = bookReviewReportMapper.queryBookReviewReportListByBookReview(bookReview.getBookreviewid());
            for (BookReviewReport bookReviewReport:l3) {
                if (bookReviewReport.getDeal()!=1) {
                    bookReviewReportMapper.deleteBookReviewReport(bookReviewReport.getBreportid());
                }
            }
            bookReviewMapper.deleteBookReview(bookReview.getBookreviewid());
        }
        List<BookScore> list1 = bookScoreMapper.queryBookScoreByBook(bookid);
        for (BookScore bookScore:list1){
            bookScoreMapper.deleteBookScore(bookScore.getUserid(),bookScore.getBookid());
        }
        bookMapper.deleteBook(bookid);
        return 1;
    }

    @Override
    public int cnt() {
        return bookMapper.cnt();
    }

    @Override
    public int max() {
        return bookMapper.max();
    }

    @Override
    public List<Book> queryHotBookList() {
        return bookMapper.queryHotBookList();
    }

    @Override
    public List<Book> queryRecommendBookList() {
        return bookMapper.queryRecommendBookList();
    }

    @Override
    public List<Book> queryBookListInPage(int page, int size) {
        List<Book> res = new ArrayList<>();
        List<Book> list = bookMapper.queryBookList();
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public List<Book> queryBookByNameInPage(String name,int page, int size) {
        List<Book> res = new ArrayList<>();
        List<Book> list = bookMapper.queryBookByName(name);
        for (int i=(page-1)*size;(i<page*size&&i<list.size());i++){
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public List<Book> queryNewBookList() {
        return bookMapper.queryNewBookList();
    }

    @Override
    public List<Book> queryRecommendBookList5() {
        List<Book> res = bookMapper.queryRecommendBookList();
        if (res.size()>5){
            return res.subList(0,5);
        }
        else if (res.size()==5){
            return res;
        }
        else {
            while (res.size()<5){
                Book book = null;
                res.add(book);
            }
            return res;
        }

    }
}
