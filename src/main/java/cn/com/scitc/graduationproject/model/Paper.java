package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Paper {
    private int pid;
    private int eid;
    private int sid;
    private int cno;
    private int stype;
    private String scontent;
    private String sa;
    private String sb;
    private String sc;
    private String sd;
    private String skey;

    @Id
    @Column(name = "pid", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
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
        Paper paper = (Paper) o;
        return pid == paper.pid &&
                eid == paper.eid &&
                sid == paper.sid &&
                cno == paper.cno &&
                stype == paper.stype &&
                Objects.equals(scontent, paper.scontent) &&
                Objects.equals(sa, paper.sa) &&
                Objects.equals(sb, paper.sb) &&
                Objects.equals(sc, paper.sc) &&
                Objects.equals(sd, paper.sd) &&
                Objects.equals(skey, paper.skey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, eid, sid, cno, stype, scontent, sa, sb, sc, sd, skey);
    }
}
