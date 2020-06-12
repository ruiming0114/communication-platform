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
public class VideoController {

    @Autowired
    UserService userService;

    @Autowired
    VideoService videoService;

    @Autowired
    VideoReviewLikedService videoReviewLikedService;

    @Autowired
    VideoReviewService videoReviewService;

    @Autowired
    VideoReviewReportService videoReviewReportService;

    @Autowired
    VideoScoreService videoScoreService;

    @RequestMapping("/videoindex")
    public String videoindex(Model model){

        return "video/VideoIndex";
    }

    @RequestMapping("/video/{videoid}")
    public String gotoVideo( @PathVariable("videoid") int videoid , Model model){
        Video video=videoService.queryVideoByID(videoid);
        model.addAttribute("currentvideo",video);
        model.addAttribute("value",video.getTotalscore());
        model.addAttribute("total_size",videoReviewService.queryVideoReviewListByVideoId(videoid).size());
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        if (currentUser!=null){
            model.addAttribute("currentuser",currentUser);
            model.addAttribute("uservideoscore",videoScoreService.getScoreByUserAndVideo(currentUser.getUserid(),videoid));
        }
        else {
            model.addAttribute("uservideoscore",0);
        }
        return "video/Video";
    }

    @RequestMapping("/videoreview/{videoreviewid}")
    public String gotoVideoReview(@PathVariable("videoreviewid") int videoreviewid, Model model){
        VideoReview videoReview = videoReviewService.queryVideoReviewById(videoreviewid);
        model.addAttribute("currentreview",videoReview);
        model.addAttribute("reviewuser",userService.queryUserByID(videoReview.getUserid()));
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("currentuser",currentUser);
        if (currentUser!=null){
            model.addAttribute("like",videoReviewLikedService.getVideoReviewLikedByUserAndReview(currentUser.getUserid(),videoreviewid));
        }
        return "video/Videoreview";
    }

    @RequestMapping("/queryVideoList")
    @ResponseBody
    public List<Video> queryVideoList(){
        return videoService.queryVideoList();
    }

    @RequestMapping("/queryVideoListInPage/{page}/{size}")
    @ResponseBody
    public List<Video> queryVideoListInPage(@PathVariable("page") int page,@PathVariable("size") int size){
        return videoService.queryVideoListInPage(page,size);
    }

    @RequestMapping("/queryHotVideoList")
    @ResponseBody
    public List<Video> queryHotVideoList(){
        return videoService.queryHotVideoList();
    }

    @RequestMapping("/queryRecommendVideoList")
    @ResponseBody
    public List<Video> queryRecommendVideoList(){
        return videoService.queryRecommendVideoList();
    }

    @RequestMapping("/queryVideoReviewListByVideo/{videoid}/{page}/{size}")
    @ResponseBody
    public List<VideoReview> queryVideoReviewListByVideo(@PathVariable("videoid") int videoid, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        List<VideoReview> list = videoReviewService.queryVideoReviewListByVideoId(videoid);
        return videoReviewService.queryVideoReviewListByVideoId_Pageable(list,page,size);
    }

    @RequestMapping("/queryVideoReviewListByVideo_UserId_to_Username/{videoid}/{page}/{size}")
    @ResponseBody
    public List<User> queryVideoReviewListByVideo_UserId_to_Username(@PathVariable("videoid") int videoid, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        List<VideoReview> list1=videoReviewService.queryVideoReviewListByVideoId(videoid);
        List<VideoReview> list2=videoReviewService.queryVideoReviewListByVideoId_Pageable(list1,page,size);
        List<User> list = new ArrayList<>();
        for (VideoReview videoReview:list2){
            User user = userService.queryUserByID(videoReview.getUserid());
            list.add(user);
        }
        return list;
    }

    @RequestMapping("/toAddVideoReview/{videoid}")
    public String toAddVideoReview(@PathVariable("videoid") int videoid,Model model){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            model.addAttribute("videoid",videoid);
            return "video/AddVideoReview";
        }
        else {
            model.addAttribute("loginMsg","您还没有登录，请登录后发表评论");
            return "Login";
        }

    }

    @RequestMapping("/AddVideoReview")
    public String AddVideoReview(@RequestParam("userid") int userid,@RequestParam("videoid") int videoid,@RequestParam("title") String title,@RequestParam("content") String content){
        VideoReview videoReview = new VideoReview();
        videoReview.setVideoid(videoid);
        videoReview.setUserid(userid);
        videoReview.setAgainstnum(0);
        videoReview.setLikenum(0);
        videoReview.setVideoreviewid(videoReviewService.max()+1);
        videoReview.setTitle(title);
        videoReview.setContent(content);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        videoReview.setAddtime(timeStamp);
        videoReviewService.addVideoReview(videoReview);
        return "redirect:/video/"+videoid;
    }

    @RequestMapping("/toAdminister_Video")
    public String toAdminister_Video(Model model){
        List<Video> list = videoService.queryVideoList();
        model.addAttribute("total_size",list.size());
        return "video/Administer_Video";
    }

    @RequestMapping("/toAddVideo")
    public String toAddVideo(){
        return "video/AddVideo";
    }

    @RequestMapping("/AddVideo")
    public String AddVideo(@RequestParam("videoname") String videoname,@RequestParam("director") String director,@RequestParam("country") String country,@RequestParam("intro") String intro,@RequestParam("videoimgpath") String videoimgpath,@RequestParam("writer") String writer,@RequestParam("actor") String actor){
        Video video = new Video();
        video.setVideoid(videoService.max()+1);
        video.setVideoname(videoname);
        video.setDirector(director);
        video.setTotalscore(0);
        video.setVideointro(intro);
        video.setCountry(country);
        video.setVideoimgpath(videoimgpath);
        video.setWriter(writer);
        video.setActor(actor);
        videoService.addVideo(video);
        return "redirect:/toAdminister_Video";
    }

    @RequestMapping("/toEditVideo/{videoid}")
    public String toEditVideo(@PathVariable("videoid") int videoid, Model model){
        model.addAttribute("currentvideo",videoService.queryVideoByID(videoid));
        return "video/EditVideo";
    }

    @RequestMapping("/EditVideo")
    public String EditVideo(@RequestParam("videoid") int videoid,@RequestParam("videoname") String videoname,@RequestParam("director") String director,@RequestParam("country") String country,@RequestParam("intro") String intro,@RequestParam("videoimgpath") String videoimgpath,@RequestParam("writer") String writer,@RequestParam("actor") String actor){
        Video video = videoService.queryVideoByID(videoid);
        video.setVideoname(videoname);
        video.setDirector(director);
        video.setVideointro(intro);
        video.setCountry(country);
        video.setVideoimgpath(videoimgpath);
        video.setActor(actor);
        video.setWriter(writer);
        videoService.updateVideo(video);
        return "redirect:/toAdminister_Video";
    }

    @RequestMapping("/DeleteVideo/{videoid}")
    public String DeleteVideo(@PathVariable("videoid") int videoid){
        videoService.deleteVideo(videoid);
        return  "redirect:/toAdminister_Video";
    }

    @RequestMapping("/toAdminister_VideoReview/{videoid}")
    public String toAdminister_VideoReview(@PathVariable("videoid") int videoid,Model model){
        model.addAttribute("currentvideo",videoService.queryVideoByID(videoid));
        model.addAttribute("total_size",videoReviewService.queryVideoReviewListByVideoId(videoid).size());
        return "video/Administer_VideoReview";
    }

    @RequestMapping("/toEditVideoReview/{videoreviewid}")
    public String toEditVideoReview(@PathVariable("videoreviewid") int videoreviewid,Model model){
        model.addAttribute("currentreview",videoReviewService.queryVideoReviewById(videoreviewid));
        return "video/EditVideoReview";
    }

    @RequestMapping("/EditVideoReview")
    public String EditVideoReview(@RequestParam("videoreviewid") int videoreviewid,@RequestParam("title") String title,@RequestParam("content") String content){
        VideoReview videoReview = videoReviewService.queryVideoReviewById(videoreviewid);
        videoReview.setTitle(title);
        videoReview.setContent(content);
        videoReviewService.updateVideoReview(videoReview);
        return "redirect:/video/"+videoReview.getVideoid();
    }

    @RequestMapping("/DeleteVideoReview/{videoreviewid}")
    public String DeleteVideoReview(@PathVariable("videoreviewid") int videoreviewid){
        VideoReview videoReview = videoReviewService.queryVideoReviewById(videoreviewid);
        videoReviewService.deleteVideoReview(videoreviewid);
        return "redirect:/video/"+videoReview.getVideoid();
    }

    @RequestMapping("/checkVideo/{word}")
    public String checkVideo(@PathVariable("word") String word,Model model){
        model.addAttribute("word",word);
        model.addAttribute("total_size",videoService.queryVideoByName(word).size());
        return "video/CheckVideo";
    }

    @RequestMapping("/checkVideoInPage/{word}/{page}/{size}")
    @ResponseBody
    public List<Video> checkVideoInPage(@PathVariable("word") String word,@PathVariable("page") int page,@PathVariable("size") int size){
        return videoService.queryVideoByNameInPage(word,page,size);
    }

    @RequestMapping("/toMyvideoreview")
    public String toMyvideoreview(Model model){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("total_size",videoReviewService.queryVideoReviewListByUserId(currentUser.getUserid()).size());
        model.addAttribute("currentuser",currentUser.getUserid());
        return "video/MyVideoReview";
    }

    @RequestMapping("/queryVideoReviewListByUser/{userid}/{page}/{size}")
    @ResponseBody
    public List<VideoReview> queryVideoReviewListByUser(@PathVariable("userid") int userid, @PathVariable("page") Integer page, @PathVariable("size") Integer size){
        List<VideoReview> list = videoReviewService.queryVideoReviewListByUserId(userid);
        return videoReviewService.queryVideoReviewListByUserId_Pageable(list,page,size);
    }

    @RequestMapping("/changeVideoScore/{userid}/{videoid}/{value}")
    public String changeVideoScore(@PathVariable("userid") int userid,@PathVariable("videoid") int videoid,@PathVariable("value") float value){
        videoScoreService.changeVideoScore(userid,videoid,value);
        return "redirect:/video/"+videoid;
    }

    @RequestMapping("/toAddVideoReviewReport/{videoreviewid}")
    public String toAddVideoReviewReport(@PathVariable("videoreviewid") int videoreviewid,Model model){
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        model.addAttribute("userid",currentUser.getUserid());
        model.addAttribute("videoreviewid",videoreviewid);
        return "video/AddReport";
    }

    @RequestMapping("/addVideoReviewReport")
    public String addVideoReviewReport(@RequestParam("userid") int userid,@RequestParam("videoreviewid") int videoreviewid,@RequestParam("title") String title,@RequestParam("content") String content){
        VideoReviewReport videoReviewReport= new VideoReviewReport();
        videoReviewReport.setVreportid(videoReviewReportService.max()+1);
        videoReviewReport.setVideoreviewid(videoreviewid);
        videoReviewReport.setUserid(userid);
        videoReviewReport.setTitle(title);
        videoReviewReport.setContent(content);
        videoReviewReport.setDeal(0);
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        videoReviewReport.setAddtime(timeStamp);
        videoReviewReportService.addVideoReviewReport(videoReviewReport);
        return  "redirect:/videoreview/"+videoreviewid;
    }

    @RequestMapping("/addVideoReviewLiked/{userid}/{videoreviewid}")
    public String addVideoReviewLiked(@PathVariable("userid") int userid,@PathVariable("videoreviewid") int videoreviewid){
        VideoReviewLiked videoReviewLiked=new VideoReviewLiked();
        videoReviewLiked.setVideoreviewid(videoreviewid);
        videoReviewLiked.setUserid(userid);
        videoReviewLiked.setStatus(1);
        videoReviewLikedService.addVideoReviewLiked(videoReviewLiked);
        VideoReview videoReview = videoReviewService.queryVideoReviewById(videoreviewid);
        videoReview.setLikenum(videoReviewLikedService.getLikeStatus(videoreviewid));
        videoReviewService.updateVideoReview(videoReview);
        return "redirect:/videoreview/"+videoreviewid;
    }

    @RequestMapping("/deleteVideoReviewLiked/{userid}/{videoreviewid}")
    public String deleteVideoReviewLiked(@PathVariable("userid") int userid,@PathVariable("videoreviewid") int videoreviewid) {
        videoReviewLikedService.deleteVideoReviewLiked(userid, videoreviewid);
        VideoReview videoReview = videoReviewService.queryVideoReviewById(videoreviewid);
        videoReview.setLikenum(videoReviewLikedService.getLikeStatus(videoreviewid));
        videoReview.setAgainstnum(videoReviewLikedService.getAgainstStatus(videoreviewid));
        videoReviewService.updateVideoReview(videoReview);
        return "redirect:/videoreview/" + videoreviewid;
    }

    @RequestMapping("/addVideoReviewAgainst/{userid}/{videoreviewid}")
    public String addVideoReviewAgainst(@PathVariable("userid") int userid,@PathVariable("videoreviewid") int videoreviewid){
        VideoReviewLiked videoReviewLiked=new VideoReviewLiked();
        videoReviewLiked.setVideoreviewid(videoreviewid);
        videoReviewLiked.setUserid(userid);
        videoReviewLiked.setStatus(-1);
        videoReviewLikedService.addVideoReviewLiked(videoReviewLiked);
        VideoReview videoReview = videoReviewService.queryVideoReviewById(videoreviewid);
        videoReview.setAgainstnum(videoReviewLikedService.getAgainstStatus(videoreviewid));
        videoReviewService.updateVideoReview(videoReview);
        return "redirect:/videoreview/"+videoreviewid;
    }

    @RequestMapping("/changeVideoReviewLiked/{userid}/{videoreviewid}")
    public String changeVideoReviewAgainst(@PathVariable("userid") int userid,@PathVariable("videoreviewid") int videoreviewid){
        int status=videoReviewLikedService.getVideoReviewLikedByUserAndReview(userid,videoreviewid).getStatus();
        status= -status;
        videoReviewLikedService.deleteVideoReviewLiked(userid, videoreviewid);
        VideoReviewLiked videoReviewLiked=new VideoReviewLiked();
        videoReviewLiked.setVideoreviewid(videoreviewid);
        videoReviewLiked.setUserid(userid);
        videoReviewLiked.setStatus(status);
        videoReviewLikedService.addVideoReviewLiked(videoReviewLiked);
        VideoReview videoReview = videoReviewService.queryVideoReviewById(videoreviewid);
        videoReview.setLikenum(videoReviewLikedService.getLikeStatus(videoreviewid));
        videoReview.setAgainstnum(videoReviewLikedService.getAgainstStatus(videoreviewid));
        videoReviewService.updateVideoReview(videoReview);
        return "redirect:/videoreview/"+videoreviewid;
    }

}
