package com.rm.plat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoReviewLiked {
    private int userid;
    private int videoreviewid;
    private int status;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getVideoreviewid() {
        return videoreviewid;
    }

    public void setVideoreviewid(int videoreviewid) {
        this.videoreviewid = videoreviewid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
