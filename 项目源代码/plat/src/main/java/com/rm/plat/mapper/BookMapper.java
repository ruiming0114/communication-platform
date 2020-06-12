package com.rm.plat.mapper;

import com.rm.plat.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookMapper {
    List<Book> queryBookByName(String name);

    List<Book> queryBookList();
    List<Book> queryNewBookList();
    List<Book> queryHotBookList();
    List<Book> queryRecommendBookList();

    Book queryBookByID(int bookid);

    int addBook(Book book);
    int updateBook(Book book);
    int deleteBook(int bookid);
    int cnt();
    int max();
}
