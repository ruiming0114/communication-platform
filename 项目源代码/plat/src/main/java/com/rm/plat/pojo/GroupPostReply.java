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
public class GroupPostReply {
    private int grouppostreplyid;
    private int grouppostid;
    private int userid;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp addtime;

    public int getGrouppostreplyid() {
        return grouppostreplyid;
    }

    public void setGrouppostreplyid(int grouppostreplyid) {
        this.grouppostreplyid = grouppostreplyid;
    }

    public int getGrouppostid() {
        return grouppostid;
    }

    public void setGrouppostid(int grouppostid) {
        this.grouppostid = grouppostid;
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
}
