package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Studentsubject {
    private int ssid;
    private int seid;
    private int userid;
    private int eid;
    private int sid;
    private String studentkey;
    @Transient
    private Subject subject;
    @Transient
    public Subject getSubject() {
        return subject;
    }
    @Transient
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Id
    @Column(name = "ssid", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public int getSsid() {
        return ssid;
    }

    public void setSsid(int ssid) {
        this.ssid = ssid;
    }

    @Basic
    @Column(name = "seid", nullable = false)
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
    @Column(name = "eid", nullable = false)
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    @Basic
    @Column(name = "sid", nullable = false)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "studentkey", nullable = false, length = 10)
    public String getStudentkey() {
        return studentkey;
    }

    public void setStudentkey(String studentkey) {
        this.studentkey = studentkey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studentsubject that = (Studentsubject) o;
        return ssid == that.ssid &&
                seid == that.seid &&
                userid == that.userid &&
                eid == that.eid &&
                sid == that.sid &&
                Objects.equals(studentkey, that.studentkey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ssid, seid, userid, eid, sid, studentkey);
    }
}
