package com.rm.plat.controller;

import com.rm.plat.pojo.*;
import com.rm.plat.service.*;

import org.apache.shiro.SecurityUtils;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;

    @Autowired
    BookReviewLikedService bookReviewLikedService;

    @Autowired
    BookReviewService bookReviewService;

    @Autowired
    BookReviewReportService bookReviewReportService;

    @Autowired
    BookScoreService bookScoreService;

    @RequestMapping("/bookindex")
    public String bookindex(Model model){

        return "book/BookIndex";
    }

    @RequestMapping("/book/{bookid}")
    public String gotoBook( @PathVariable("bookid") int bookid , Model model){
        Book book=bookService.queryBookByID(bookid);
        model.addAttribute("currentbook",book);
        model.addAttribute("value",book.getTotalscore());
        model.addAttribute("total_size",bookReviewService.queryBookReviewListByBookId(bookid).size());
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        if (currentUser!=null){
            model.addAttribute("currentuser",currentUser);
            model.addAttribute("userbookscore",bookScoreService.getScoreByUserAndBook(currentUser.getUserid(),bookid));
        }
        else {
            model.addAttribute("userbookscore",0);
        }
        return "book/Book";
    }

    @RequestMapping("/bookreview/{bookreviewid}")
    public String gotoBookReview(@PathVariable("bookreviewid") int bookreviewid, Model model){
        BookReview bookReview = bookReviewService.queryBookReviewById(bookreviewid);
        model.addAttribute("currentreview",bookReview);
        model.addAttribute("reviewuser",userService.queryUserByID(bookReview.getUserid()));
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("currentuser",currentUser);
        if (currentUser!=null){
            model.addAttribute("like",bookReviewLikedService.getBookReviewLikedByUserAndReview(currentUser.getUserid(),bookreviewid));
        }
        return "book/Bookreview";
    }

    @RequestMapping("/queryBookList")
    @ResponseBody
    public List<Book> queryBookList(){
        return bookService.queryBookList();
    }

    @RequestMapping("/queryBookListInPage/{page}/{size}")
    @ResponseBody
    public List<Book> queryBookListInPage(@PathVariable("page") int page,@PathVariable("size") int size){
        return bookService.queryBookListInPage(page,size);
    }

    @RequestMapping("/queryHotBookList")
    @ResponseBody
    public List<Book> queryHotBookList(){
        return bookService.queryHotBookList();
    }

    @RequestMapping("/queryRecommendBookList")
    @ResponseBody
    public List<Book> queryRecommendBookList(){
        return bookService.queryRecommendBookList();
    }

    @RequestMapping("/queryBookReviewListByBook/{bookid}/{page}/{size}")
    @ResponseBody
    public List<BookReview> queryBookReviewListByBook(@PathVariable("bookid") int bookid, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        List<BookReview> list = bookReviewService.queryBookReviewListByBookId(bookid);
        return bookReviewService.queryBookReviewListByBookId_Pageable(list,page,size);
    }

    @RequestMapping("/queryBookReviewListByBook_UserId_to_Username/{bookid}/{page}/{size}")
    @ResponseBody
    public List<User> queryBookReviewListByBook_UserId_to_Username(@PathVariable("bookid") int bookid, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        List<BookReview> list1=bookReviewService.queryBookReviewListByBookId(bookid);
        List<BookReview> list2=bookReviewService.queryBookReviewListByBookId_Pageable(list1,page,size);
        List<User> list = new ArrayList<>();
        for (BookReview bookReview:list2){
            User user = userService.queryUserByID(bookReview.getUserid());
            list.add(user);
        }
        return list;
    }

    @RequestMapping("/toAddBookReview/{bookid}")
    public String toAddBookReview(@PathVariable("bookid") int bookid,Model model){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            model.addAttribute("bookid",bookid);
            return "book/AddBookReview";
        }
        else {
            model.addAttribute("loginMsg","您还没有登录，请登录后发表评论");
            return "Login";
        }
    }

    @RequestMapping("/AddBookReview")
    public String AddBookReview(@RequestParam("userid") int userid,@RequestParam("bookid") int bookid,@RequestParam("title") String title,@RequestParam("content") String content){
        BookReview bookReview = new BookReview();
        bookReview.setBookid(bookid);
        bookReview.setUserid(userid);
        bookReview.setAgainstnum(0);
        bookReview.setLikenum(0);
        bookReview.setBookreviewid(bookReviewService.max()+1);
        bookReview.setTitle(title);
        bookReview.setContent(content);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        bookReview.setAddtime(timeStamp);
        bookReviewService.addBookReview(bookReview);
        return "redirect:/book/"+bookid;
    }

    @RequestMapping("/toAdminister_Book")
    public String toAdminister_Book(Model model){
        List<Book> list = bookService.queryBookList();
        model.addAttribute("total_size",list.size());
        return "book/Administer_Book";
    }

    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "book/AddBook";
    }

    @RequestMapping("/AddBook")
    public String AddBook(@RequestParam("bookname") String bookname,@RequestParam("author") String author,@RequestParam("publisher") String publisher,@RequestParam("isbn") String ISBN,@RequestParam("intro") String intro,@RequestParam("bookimgpath") String bookimgpath){
        Book book = new Book();
        book.setBookid(bookService.max()+1);
        book.setBookname(bookname);
        book.setAuthor(author);
        book.setISBN(ISBN);
        book.setTotalscore(0);
        book.setBookintro(intro);
        book.setPublisher(publisher);
        book.setBookimgpath(bookimgpath);
        bookService.addBook(book);
        return "redirect:/toAdminister_Book";
    }

    @RequestMapping("/toEditBook/{bookid}")
    public String toEditBook(@PathVariable("bookid") int bookid, Model model){
        model.addAttribute("currentbook",bookService.queryBookByID(bookid));
        return "book/EditBook";
    }

    @RequestMapping("/EditBook")
    public String EditBook(@RequestParam("bookid") int bookid,@RequestParam("bookname") String bookname,@RequestParam("author") String author,@RequestParam("publisher") String publisher,@RequestParam("isbn") String ISBN,@RequestParam("intro") String intro,@RequestParam("bookimgpath") String bookimgpath){
        Book book = bookService.queryBookByID(bookid);
        book.setBookname(bookname);
        book.setAuthor(author);
        book.setISBN(ISBN);
        book.setBookintro(intro);
        book.setPublisher(publisher);
        book.setBookimgpath(bookimgpath);
        bookService.updateBook(book);
        return "redirect:/toAdminister_Book";
    }

    @RequestMapping("/DeleteBook/{bookid}")
    public String DeleteBook(@PathVariable("bookid") int bookid){
       bookService.deleteBook(bookid);
       return  "redirect:/toAdminister_Book";
    }

    @RequestMapping("/toAdminister_BookReview/{bookid}")
    public String toAdminister_BookReview(@PathVariable("bookid") int bookid,Model model){
        model.addAttribute("currentbook",bookService.queryBookByID(bookid));
        model.addAttribute("total_size",bookReviewService.queryBookReviewListByBookId(bookid).size());
        return "book/Administer_BookReview";
    }

    @RequestMapping("/toEditBookReview/{bookreviewid}")
    public String toEditBookReview(@PathVariable("bookreviewid") int bookreviewid,Model model){
        model.addAttribute("currentreview",bookReviewService.queryBookReviewById(bookreviewid));
        return "book/EditBookReview";
    }

    @RequestMapping("/EditBookReview")
    public String EditBookReview(@RequestParam("bookreviewid") int bookreviewid,@RequestParam("title") String title,@RequestParam("content") String content){
        BookReview bookReview = bookReviewService.queryBookReviewById(bookreviewid);
        bookReview.setTitle(title);
        bookReview.setContent(content);
        bookReviewService.updateBookReview(bookReview);
        return "redirect:/book/"+bookReview.getBookid();
    }

    @RequestMapping("/DeleteBookReview/{bookreviewid}")
    public String DeleteBookReview(@PathVariable("bookreviewid") int bookreviewid){
        BookReview bookReview = bookReviewService.queryBookReviewById(bookreviewid);
        bookReviewService.deleteBookReview(bookreviewid);
        return "redirect:/book/"+bookReview.getBookid();
    }

    @RequestMapping("/checkBook/{word}")
    public String checkBook(@PathVariable("word") String word,Model model){
        model.addAttribute("word",word);
        model.addAttribute("total_size",bookService.queryBookByName(word).size());
        return "book/CheckBook";
    }

    @RequestMapping("/checkBookInPage/{word}/{page}/{size}")
    @ResponseBody
    public List<Book> checkBookInPage(@PathVariable("word") String word,@PathVariable("page") int page,@PathVariable("size") int size){
        return bookService.queryBookByNameInPage(word,page,size);
    }

    @RequestMapping("/toMybookreview")
    public String toMybookreview(Model model){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("total_size",bookReviewService.queryBookReviewListByUserId(currentUser.getUserid()).size());
        model.addAttribute("currentuser",currentUser.getUserid());
        return "book/MyBookReview";
    }

    @RequestMapping("/queryBookReviewListByUser/{userid}/{page}/{size}")
    @ResponseBody
    public List<BookReview> queryBookReviewListByUser(@PathVariable("userid") int userid, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        List<BookReview> list = bookReviewService.queryBookReviewListByUserId(userid);
        return bookReviewService.queryBookReviewListByUserId_Pageable(list,page,size);
    }

    @RequestMapping("/changeBookScore/{userid}/{bookid}/{value}")
    public String changeBookScore(@PathVariable("userid") int userid,@PathVariable("bookid") int bookid,@PathVariable("value") float value){
        bookScoreService.changeBookScore(userid,bookid,value);
        return "redirect:/book/"+bookid;
    }

    @RequestMapping("/toAddBookReviewReport/{bookreviewid}")
    public String toAddBookReviewReport(@PathVariable("bookreviewid") int bookreviewid,Model model){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("userid",currentUser.getUserid());
        model.addAttribute("bookreviewid",bookreviewid);
        return "book/AddReport";
    }

    @RequestMapping("/addBookReviewReport")
    public String addBookReviewReport(@RequestParam("userid") int userid,@RequestParam("bookreviewid") int bookreviewid,@RequestParam("title") String title,@RequestParam("content") String content){
        BookReviewReport bookReviewReport= new BookReviewReport();
        bookReviewReport.setBreportid(bookReviewReportService.max()+1);
        bookReviewReport.setBookreviewid(bookreviewid);
        bookReviewReport.setUserid(userid);
        bookReviewReport.setTitle(title);
        bookReviewReport.setContent(content);
        bookReviewReport.setDeal(0);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        bookReviewReport.setAddtime(timeStamp);
        bookReviewReportService.addBookReviewReport(bookReviewReport);
        return  "redirect:/bookreview/"+bookreviewid;
    }

    @RequestMapping("/addBookReviewLiked/{userid}/{bookreviewid}")
    public String addBookReviewLiked(@PathVariable("userid") int userid,@PathVariable("bookreviewid") int bookreviewid){
        BookReviewLiked bookReviewLiked=new BookReviewLiked();
        bookReviewLiked.setBookreviewid(bookreviewid);
        bookReviewLiked.setUserid(userid);
        bookReviewLiked.setStatus(1);
        bookReviewLikedService.addBookReviewLiked(bookReviewLiked);
        BookReview bookReview = bookReviewService.queryBookReviewById(bookreviewid);
        bookReview.setLikenum(bookReviewLikedService.getLikeStatus(bookreviewid));
        bookReviewService.updateBookReview(bookReview);
        return "redirect:/bookreview/"+bookreviewid;
    }

    @RequestMapping("/deleteBookReviewLiked/{userid}/{bookreviewid}")
    public String deleteBookReviewLiked(@PathVariable("userid") int userid,@PathVariable("bookreviewid") int bookreviewid) {
        bookReviewLikedService.deleteBookReviewLiked(userid, bookreviewid);
        BookReview bookReview = bookReviewService.queryBookReviewById(bookreviewid);
        bookReview.setLikenum(bookReviewLikedService.getLikeStatus(bookreviewid));
        bookReview.setAgainstnum(bookReviewLikedService.getAgainstStatus(bookreviewid));
        bookReviewService.updateBookReview(bookReview);
        return "redirect:/bookreview/" + bookreviewid;
    }

    @RequestMapping("/addBookReviewAgainst/{userid}/{bookreviewid}")
    public String addBookReviewAgainst(@PathVariable("userid") int userid,@PathVariable("bookreviewid") int bookreviewid){
        BookReviewLiked bookReviewLiked=new BookReviewLiked();
        bookReviewLiked.setBookreviewid(bookreviewid);
        bookReviewLiked.setUserid(userid);
        bookReviewLiked.setStatus(-1);
        bookReviewLikedService.addBookReviewLiked(bookReviewLiked);
        BookReview bookReview = bookReviewService.queryBookReviewById(bookreviewid);
        bookReview.setAgainstnum(bookReviewLikedService.getAgainstStatus(bookreviewid));
        bookReviewService.updateBookReview(bookReview);
        return "redirect:/bookreview/"+bookreviewid;
    }

    @RequestMapping("/changeBookReviewLiked/{userid}/{bookreviewid}")
    public String changeBookReviewAgainst(@PathVariable("userid") int userid,@PathVariable("bookreviewid") int bookreviewid){
        int status=bookReviewLikedService.getBookReviewLikedByUserAndReview(userid,bookreviewid).getStatus();
        status= -status;
        bookReviewLikedService.deleteBookReviewLiked(userid, bookreviewid);
        BookReviewLiked bookReviewLiked=new BookReviewLiked();
        bookReviewLiked.setBookreviewid(bookreviewid);
        bookReviewLiked.setUserid(userid);
        bookReviewLiked.setStatus(status);
        bookReviewLikedService.addBookReviewLiked(bookReviewLiked);
        BookReview bookReview = bookReviewService.queryBookReviewById(bookreviewid);
        bookReview.setLikenum(bookReviewLikedService.getLikeStatus(bookreviewid));
        bookReview.setAgainstnum(bookReviewLikedService.getAgainstStatus(bookreviewid));
        bookReviewService.updateBookReview(bookReview);
        return "redirect:/bookreview/"+bookreviewid;
    }

}
