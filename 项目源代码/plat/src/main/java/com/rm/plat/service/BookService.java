package com.rm.plat.service;

import com.rm.plat.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> queryBookByName(String name);
    List<Book> queryBookByNameInPage(String name,int page,int size);

    List<Book> queryMyBook(int userid);
    List<Book> queryBookList();
    List<Book> queryBookListInPage(int page,int size);
    Book queryBookByID(int bookid);
    List<Book> queryRecommendBookList5();
    List<Book> queryHotBookList();
    List<Book> queryRecommendBookList();
    List<Book> queryNewBookList();

    int addBook(Book book);
    int updateBook(Book book);
    int deleteBook(int bookid);
    int cnt();
    int max();
}
