package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Type {
    private int stype;
    private String name;

    @Id
    @Column(name = "stype", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public int getStype() {
        return stype;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return stype == type.stype &&
                Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stype, name);
    }
}
