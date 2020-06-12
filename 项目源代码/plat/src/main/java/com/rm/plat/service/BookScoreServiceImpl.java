package com.rm.plat.service;

import com.rm.plat.mapper.BookMapper;
import com.rm.plat.mapper.BookScoreMapper;
import com.rm.plat.pojo.Book;
import com.rm.plat.pojo.BookScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookScoreServiceImpl implements BookScoreService {

    @Autowired
    BookScoreMapper bookScoreMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public float getScoreByUserAndBook(int userid, int bookid) {
        BookScore bookScore = bookScoreMapper.getScoreByUserAndBook(userid,bookid);
        if (bookScore==null) {
            return 0;
        }
        else {
            return bookScore.getScore();
        }
    }

    @Override
    public float getAvgScoreByBook(int bookid) {
        List<BookScore> list = bookScoreMapper.queryBookScoreByBook(bookid);
        float sum = 0;
        if (list.size()!=0){
            for (BookScore bookScore : list){
                sum+=bookScore.getScore();
            }
            sum/=list.size();
        }
        return sum;
    }

    @Override
    public int changeBookScore(int userid, int bookid,float value) {
        BookScore bookScore = new BookScore();
        bookScore.setBookid(bookid);
        bookScore.setScore(value);
        bookScore.setUserid(userid);
        if (getScoreByUserAndBook(userid,bookid)==0){
           bookScoreMapper.addBookScore(bookScore);
        }
        else {
           bookScoreMapper.deleteBookScore(userid,bookid);
           bookScoreMapper.addBookScore(bookScore);
        }
        Book book = bookMapper.queryBookByID(bookid);
        book.setTotalscore(getAvgScoreByBook(bookid));
        bookMapper.updateBook(book);
        return 1;
    }

    @Override
    public int deleteBookScore(int userid, int bookid) {
        bookScoreMapper.deleteBookScore(userid,bookid);
        return 1;
    }

    @Override
    public int cntByBook(int bookid) {
        return bookScoreMapper.cntByBook(bookid);
    }


}
