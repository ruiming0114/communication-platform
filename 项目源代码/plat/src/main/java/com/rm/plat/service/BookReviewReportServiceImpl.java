package com.rm.plat.service;

import com.rm.plat.mapper.BookReviewReportMapper;
import com.rm.plat.pojo.BookReviewLiked;
import com.rm.plat.pojo.BookReviewReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookReviewReportServiceImpl implements BookReviewReportService{

    @Autowired
    BookReviewReportMapper bookReviewReportMapper;

    @Override
    public BookReviewReport queryBookReviewReportById(int breportid) {
        return bookReviewReportMapper.queryBookReviewReportById(breportid);
    }

    @Override
    public List<BookReviewReport> queryBookReviewReportList() {
        return bookReviewReportMapper.queryBookReviewReportList();
    }

    @Override
    public List<BookReviewReport> queryUndealBookReviewReportList() {
        return bookReviewReportMapper.queryUndealBookReviewReportList();
    }

    @Override
    public int addBookReviewReport(BookReviewReport bookReviewReport) {
        bookReviewReportMapper.addBookReviewReport(bookReviewReport);
        return 1;
    }

    @Override
    public int updateBookReviewReport(BookReviewReport bookReviewReport) {
        bookReviewReportMapper.updateBookReviewReport(bookReviewReport);
        return 1;
    }

    @Override
    public int deleteBookReviewReport(int breportid) {
        bookReviewReportMapper.deleteBookReviewReport(breportid);
        return 1;
    }

    @Override
    public int cnt() {
        return bookReviewReportMapper.cnt();
    }

    @Override
    public int max() {
        return bookReviewReportMapper.max();
    }
}
