package com.rm.plat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    private int videoid;
    private String videoname;
    private String videointro;
    private float totalscore;
    private String director;
    private String videoimgpath;
    private String country;
    private String writer;
    private String actor;

    public int getVideoid() {
        return videoid;
    }

    public void setVideoid(int videoid) {
        this.videoid = videoid;
    }

    public String getVideoname() {
        return videoname;
    }

    public void setVideoname(String videoname) {
        this.videoname = videoname;
    }

    public String getVideointro() {
        return videointro;
    }

    public void setVideointro(String videointro) {
        this.videointro = videointro;
    }

    public float getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(float totalscore) {
        this.totalscore = totalscore;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getVideoimgpath() {
        return videoimgpath;
    }

    public void setVideoimgpath(String videoimgpath) {
        this.videoimgpath = videoimgpath;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
