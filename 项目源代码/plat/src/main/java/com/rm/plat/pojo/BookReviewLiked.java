package com.rm.plat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReviewLiked {
    private int userid;
    private int bookreviewid;
    private int status;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getBookreviewid() {
        return bookreviewid;
    }

    public void setBookreviewid(int bookreviewid) {
        this.bookreviewid = bookreviewid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
