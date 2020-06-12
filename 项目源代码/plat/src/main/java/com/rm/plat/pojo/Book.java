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
    private float totalscore;
    private String author;
    private String bookimgpath;
    private String publisher;
    private String ISBN;

    public int getBookid() {
        return bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public String getBookintro() {
        return bookintro;
    }


    public String getAuthor() {
        return author;
    }

    public String getBookimgpath() {
        return bookimgpath;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void setBookintro(String bookintro) {
        this.bookintro = bookintro;
    }

    public float getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(float totalscore) {
        this.totalscore = totalscore;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBookimgpath(String bookimgpath) {
        this.bookimgpath = bookimgpath;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
}
