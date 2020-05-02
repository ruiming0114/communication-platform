package com.rm.plat.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int bookid;
    private String bookname;
    private String bookintro;
    private float bookscore;
    private String author;
    private String bookimgpath;
    private String publisher;
    private String ISBN;
}
