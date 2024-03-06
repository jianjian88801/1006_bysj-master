package cn.com.scitc.graduationproject.model;

import java.sql.Timestamp;

public class StudentScore {
    private Integer seid;
    private Integer userid;
    private Integer username;
    private String truename;
    private String pname;
    private Integer zscore;
    private Integer score;
    private Timestamp tjtime;

    public Integer getSeid() {
        return seid;
    }

    public void setSeid(Integer seid) {
        this.seid = seid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getUsername() {
        return username;
    }

    public void setUsername(Integer username) {
        this.username = username;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getZscore() {
        return zscore;
    }

    public void setZscore(Integer zscore) {
        this.zscore = zscore;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Timestamp getTjtime() {
        return tjtime;
    }

    public void setTjtime(Timestamp tjtime) {
        this.tjtime = tjtime;
    }

    @Override
    public String toString() {
        return "StudentScore{" +
                "seid=" + seid +
                ", userid=" + userid +
                ", username=" + username +
                ", truename='" + truename + '\'' +
                ", pname='" + pname + '\'' +
                ", zscore=" + zscore +
                ", score=" + score +
                ", tjtime=" + tjtime +
                '}';
    }
}
