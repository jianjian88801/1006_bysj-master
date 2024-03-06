package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Exam {
    private int eid;
    private String pname;
    private int cno;
    private int userid;
    private int classid;
    private int singlenumber;
    private int singlecore;
    private int multiplenumber;
    private int multiplecore;
    private Date examdate;
    private Date examtime;
    private int testtime;

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
    @Column(name = "eid", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
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
    @Column(name = "cno", nullable = false)
    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
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
    @Column(name = "singlenumber", nullable = false)
    public int getSinglenumber() {
        return singlenumber;
    }

    public void setSinglenumber(int singlenumber) {
        this.singlenumber = singlenumber;
    }

    @Basic
    @Column(name = "singlecore", nullable = false)
    public int getSinglecore() {
        return singlecore;
    }

    public void setSinglecore(int singlecore) {
        this.singlecore = singlecore;
    }

    @Basic
    @Column(name = "multiplenumber", nullable = false)
    public int getMultiplenumber() {
        return multiplenumber;
    }

    public void setMultiplenumber(int multiplenumber) {
        this.multiplenumber = multiplenumber;
    }

    @Basic
    @Column(name = "multiplecore", nullable = false)
    public int getMultiplecore() {
        return multiplecore;
    }

    public void setMultiplecore(int multiplecore) {
        this.multiplecore = multiplecore;
    }

    @Basic
    @Column(name = "examdate", nullable = false)
    public Date getExamdate() {
        return examdate;
    }

    public void setExamdate(Date examdate) {
        this.examdate = examdate;
    }

    @Basic
    @Column(name = "examtime", nullable = false)
    public Date getExamtime() {
        return examtime;
    }

    public void setExamtime(Date examtime) {
        this.examtime = examtime;
    }

    @Basic
    @Column(name = "testtime", nullable = false)
    public int getTesttime() {
        return testtime;
    }

    public void setTesttime(int testtime) {
        this.testtime = testtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exam exam = (Exam) o;
        return eid == exam.eid &&
                cno == exam.cno &&
                userid == exam.userid &&
                classid == exam.classid &&
                singlenumber == exam.singlenumber &&
                singlecore == exam.singlecore &&
                multiplenumber == exam.multiplenumber &&
                multiplecore == exam.multiplecore &&
                testtime == exam.testtime &&
                Objects.equals(pname, exam.pname) &&
                Objects.equals(examdate, exam.examdate) &&
                Objects.equals(examtime, exam.examtime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eid, pname, cno, userid, classid, singlenumber, singlecore, multiplenumber, multiplecore, examdate, examtime, testtime);
    }
}
