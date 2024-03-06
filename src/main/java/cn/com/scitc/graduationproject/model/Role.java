package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Role {
    private int roleid;
    private String rolename;

    @Id
    @Column(name = "roleid", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    @Basic
    @Column(name = "rolename", nullable = true, length = 20)
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return roleid == role.roleid &&
                Objects.equals(rolename, role.rolename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleid, rolename);
    }
}
