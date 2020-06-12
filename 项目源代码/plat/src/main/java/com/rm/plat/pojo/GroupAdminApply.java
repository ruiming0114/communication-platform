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
public class GroupAdminApply {

    private int applyid;
    private int groupid;
    private int userid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp addtime;
    private String application;
    private int deal;
    // 0未处理 1通过 2 拒绝


    public int getApplyid() {
        return applyid;
    }

    public void setApplyid(int applyid) {
        this.applyid = applyid;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Timestamp getAddtime() {
        return addtime;
    }

    public void setAddtime(Timestamp addtime) {
        this.addtime = addtime;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public int getDeal() {
        return deal;
    }

    public void setDeal(int deal) {
        this.deal = deal;
    }
}
