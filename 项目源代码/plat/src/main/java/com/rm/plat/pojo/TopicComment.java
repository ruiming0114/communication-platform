package com.rm.plat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicComment {

    private int commentid;
    private int topicid;
    private  int userid;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp addtime;
    private int likenum;

    private String imgpath1;
    private String imgpath2;
    private String imgpath3;
    private String imgpath4;

    public String getImgpath4() {
        return imgpath4;
    }

    public void setImgpath4(String imgpath4) {
        this.imgpath4 = imgpath4;
    }

    public String getImgpath1() {
        return imgpath1;
    }

    public void setImgpath1(String imgpath1) {
        this.imgpath1 = imgpath1;
    }

    public String getImgpath2() {
        return imgpath2;
    }

    public void setImgpath2(String imgpath2) {
        this.imgpath2 = imgpath2;
    }

    public String getImgpath3() {
        return imgpath3;
    }

    public void setImgpath3(String imgpath3) {
        this.imgpath3 = imgpath3;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public int getTopicid() {
        return topicid;
    }

    public void setTopicid(int topicid) {
        this.topicid = topicid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Timestamp getAddtime() {
        return addtime;
    }

    public void setAddtime(Timestamp addtime) {
        this.addtime = addtime;
    }

    public int getLikenum() {
        return likenum;
    }

    public void setLikenum(int likenum) {
        this.likenum = likenum;
    }
}
