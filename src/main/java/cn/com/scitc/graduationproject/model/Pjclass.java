package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pjclass {
    private int classid;
    private String classname;

    @Id
    @Column(name = "classid", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public int getClassid() {
        return classid;
    }

    public void setClassid(int classid) {
        this.classid = classid;
    }

    @Basic
    @Column(name = "classname", nullable = true, length = 20)
    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pjclass pjclass = (Pjclass) o;
        return classid == pjclass.classid &&
                Objects.equals(classname, pjclass.classname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classid, classname);
    }
}
