package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Subject {
    private int sid;
    private int cno;
    private int stype;
    private String scontent;
    private String sa;
    private String sb;
    private String sc;
    private String sd;
    private String skey;
    @Transient
    private Course course;
    @Transient
    public Course getCourse() {
        return course;
    }
    @Transient
    public void setCourse(Course course) {
        this.course = course;
    }

    @Id
    @Column(name = "sid", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "cno", nullable = false)
    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    @Basic
    @Column(name = "stype", nullable = false)
    public int getStype() {
        return stype;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }

    @Basic
    @Column(name = "scontent", nullable = false, length = 255)
    public String getScontent() {
        return scontent;
    }

    public void setScontent(String scontent) {
        this.scontent = scontent;
    }

    @Basic
    @Column(name = "sa", nullable = false, length = 255)
    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    @Basic
    @Column(name = "sb", nullable = false, length = 255)
    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    @Basic
    @Column(name = "sc", nullable = false, length = 255)
    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    @Basic
    @Column(name = "sd", nullable = false, length = 255)
    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    @Basic
    @Column(name = "skey", nullable = false, length = 255)
    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return sid == subject.sid &&
                cno == subject.cno &&
                stype == subject.stype &&
                Objects.equals(scontent, subject.scontent) &&
                Objects.equals(sa, subject.sa) &&
                Objects.equals(sb, subject.sb) &&
                Objects.equals(sc, subject.sc) &&
                Objects.equals(sd, subject.sd) &&
                Objects.equals(skey, subject.skey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, cno, stype, scontent, sa, sb, sc, sd, skey);
    }
}
