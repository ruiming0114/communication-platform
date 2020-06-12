package com.rm.plat.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoReviewReport {
    private int vreportid;
    private int videoreviewid;
    private int userid;
    private String title;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp addtime;

    private int deal;

    //0未处理 1通过 2 拒绝

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVreportid() {
        return vreportid;
    }

    public void setVreportid(int vreportid) {
        this.vreportid = vreportid;
    }

    public int getVideoreviewid() {
        return videoreviewid;
    }

    public void setVideoreviewid(int videoreviewid) {
        this.videoreviewid = videoreviewid;
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

    public int getDeal() {
        return deal;
    }

    public void setDeal(int deal) {
        this.deal = deal;
    }
}
