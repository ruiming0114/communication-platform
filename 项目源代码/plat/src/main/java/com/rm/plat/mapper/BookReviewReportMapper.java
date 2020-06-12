package com.rm.plat.mapper;

import com.rm.plat.pojo.BookReviewReport;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookReviewReportMapper {

    BookReviewReport queryBookReviewReportById(int breportid);
    List<BookReviewReport> queryBookReviewReportList();
    List<BookReviewReport> queryUndealBookReviewReportList();
    List<BookReviewReport> queryBookReviewReportListByBookReview(int bookreviewid);

    int addBookReviewReport(BookReviewReport bookReviewReport);
    int updateBookReviewReport(BookReviewReport bookReviewReport);
    int deleteBookReviewReport(int bookReviewReport);

    int cnt();
    int max();
}
