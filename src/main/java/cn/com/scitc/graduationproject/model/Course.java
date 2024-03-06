package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Course {
    private int cno;
    private String cname;

    @Id
    @Column(name = "cno", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public int getCno() {
        return cno;
    }

    public void setCno(int cno) {
        this.cno = cno;
    }

    @Basic
    @Column(name = "cname", nullable = false, length = 20)
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return cno == course.cno &&
                Objects.equals(cname, course.cname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cno, cname);
    }
}
