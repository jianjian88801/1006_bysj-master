package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Studentexam {
    private int seid;
    private int userid;
    private int classid;
    private int eid;
    private String pname;
    private int zscore;
    private int score;
    private Timestamp tjtime;
    //连表
    @Transient
    private Users users;
    @Transient
    public Users getUsers() {
        return users;
    }

    @Transient
    public void setUsers(Users users) {
        this.users = users;
    }

    @Id
    @Column(name = "seid", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public int getSeid() {
        return seid;
    }

    public void setSeid(int seid) {
        this.seid = seid;
    }

    @Basic
    @Column(name = "userid", nullable = false)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "classid", nullable = false)
    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    @Basic
    @Column(name = "eid", nullable = false)
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "pname", nullable = false, length = 11)
    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Basic
    @Column(name = "zscore", nullable = false)
    public int getZscore() {
        return zscore;
    }

    public void setZscore(int zscore) {
        this.zscore = zscore;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "tjtime", nullable = false)
    public Timestamp getTjtime() {
        return tjtime;
    }

    public void setTjtime(Timestamp tjtime) {
        this.tjtime = tjtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studentexam that = (Studentexam) o;
        return seid == that.seid &&
                userid == that.userid &&
                classid == that.classid &&
                eid == that.eid &&
                zscore == that.zscore &&
                score == that.score &&
                Objects.equals(pname, that.pname) &&
                Objects.equals(tjtime, that.tjtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seid, userid, classid, eid, pname, zscore, score, tjtime);
    }
}
