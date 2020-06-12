package com.rm.plat.service;

import com.rm.plat.pojo.BookReviewReport;

import java.util.List;

public interface BookReviewReportService {

    BookReviewReport queryBookReviewReportById(int breportid);
    List<BookReviewReport> queryBookReviewReportList();
    List<BookReviewReport> queryUndealBookReviewReportList();

    int addBookReviewReport(BookReviewReport bookReviewReport);
    int updateBookReviewReport(BookReviewReport bookReviewReport);
    int deleteBookReviewReport(int bookReviewReport);

    int cnt();
    int max();

}
