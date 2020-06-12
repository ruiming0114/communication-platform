package com.rm.plat.controller;


import com.rm.plat.pojo.*;
import com.rm.plat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReportController {

    @Autowired
    BookReviewReportService bookReviewReportService;

    @Autowired
    BookReviewService bookReviewService;

    @Autowired
    VideoReviewReportService videoReviewReportService;

    @Autowired
    VideoReviewService videoReviewService;

    @Autowired
    TopicService topicService;

    @Autowired
    TopicCommentService topicCommentService;

    @Autowired
    TopicReportService topicReportService;

    @Autowired
    TopicCommentReportService topicCommentReportService;


    @RequestMapping("/toAdminister_Report")
    public String toAdminister_Report(){
        return "Administer_Report";
    }

    @RequestMapping("/queryBookReviewReportList")
    @ResponseBody
    public List<BookReviewReport> queryBookReviewReportList(){
        return bookReviewReportService.queryBookReviewReportList();
    }

    @RequestMapping("/passBookReviewReport/{breportid}")
    public String passB(@PathVariable("breportid") int breportid){
        BookReviewReport bookReviewReport = bookReviewReportService.queryBookReviewReportById(breportid);
        bookReviewReport.setDeal(1);
        bookReviewReportService.updateBookReviewReport(bookReviewReport);
        bookReviewService.deleteBookReview(bookReviewReport.getBookreviewid());
        return "redirect:/toAdminister_Report";
    }

    @RequestMapping("/failBookReviewReport/{breportid}")
    public String failB(@PathVariable("breportid") int breportid){
        BookReviewReport bookReviewReport = bookReviewReportService.queryBookReviewReportById(breportid);
        bookReviewReport.setDeal(2);
        bookReviewReportService.updateBookReviewReport(bookReviewReport);
        return "redirect:/toAdminister_Report";
    }

    @RequestMapping("/queryVideoReviewReportList")
    @ResponseBody
    public List<VideoReviewReport> queryVideoReviewReportList(){
        return videoReviewReportService.queryVideoReviewReportList();
    }

    @RequestMapping("/passVideoReviewReport/{vreportid}")
    public String passV(@PathVariable("vreportid") int vreportid){
        VideoReviewReport videoReviewReport = videoReviewReportService.queryVideoReviewReportById(vreportid);
        videoReviewReport.setDeal(1);
        videoReviewReportService.updateVideoReviewReport(videoReviewReport);
        videoReviewService.deleteVideoReview(videoReviewReport.getVideoreviewid());
        return "redirect:/toAdminister_Report";
    }

    @RequestMapping("/failVideoReviewReport/{vreportid}")
    public String failV(@PathVariable("vreportid") int vreportid){
        VideoReviewReport videoReviewReport = videoReviewReportService.queryVideoReviewReportById(vreportid);
        videoReviewReport.setDeal(2);
        videoReviewReportService.updateVideoReviewReport(videoReviewReport);
        return "redirect:/toAdminister_Report";
    }

    @RequestMapping("/passTopicReport/{topicreportid}")
    public String passT(@PathVariable("topicreportid") int topicreportid){
        TopicReport topicReport = topicReportService.queryTopicReportById(topicreportid);
        topicReport.setDeal(1);
        topicReportService.updateTopicReport(topicReport);
        topicService.deleteTopic(topicReport.getTopicid());
        return "redirect:/toAdminister_Report";
    }

    @RequestMapping("/failTopicReport/{topicreportid}")
    public String failT(@PathVariable("topicreportid") int topicreportid){
        TopicReport topicReport = topicReportService.queryTopicReportById(topicreportid);
        topicReport.setDeal(2);
        topicReportService.updateTopicReport(topicReport);
        return "redirect:/toAdminister_Report";
    }

    @RequestMapping("/passTopicCommentReport/{topiccommentreportid}")
    public String passTC(@PathVariable("topiccommentreportid") int topiccommentreportid){
        TopicCommentReport topicCommentReport = topicCommentReportService.queryTopicCommentReportById(topiccommentreportid);
        topicCommentReport.setDeal(1);
        topicCommentReportService.updateTopicCommentReport(topicCommentReport);
        topicCommentService.deleteTopicComment(topicCommentReport.getCommentid());
        return "redirect:/toAdminister_Report";
    }

    @RequestMapping("/failTopicCommentReport/{topiccommentreportid}")
    public String failTC(@PathVariable("topiccommentreportid") int topiccommentreportid){
        TopicCommentReport topicCommentReport = topicCommentReportService.queryTopicCommentReportById(topiccommentreportid);
        topicCommentReport.setDeal(2);
        topicCommentReportService.updateTopicCommentReport(topicCommentReport);
        return "redirect:/toAdminister_Report";
    }

    @RequestMapping("/queryTopicCommentReportList")
    @ResponseBody
    public List<TopicCommentReport>  queryTopicCommentReportList(){
        return topicCommentReportService.queryTopicCommentReportList();
    }

}
