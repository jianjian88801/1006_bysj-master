package cn.com.scitc.graduationproject.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Users {
    private int userid;
    private Integer roleid;
    private String username;
    private String userpwd;
    private String truename;
    private Integer classid;

    @Id
    @Column(name = "userid", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "roleid", nullable = true)
    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Basic
    @Column(name = "username", nullable = true, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "userpwd", nullable = true, length = 20)
    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    @Basic
    @Column(name = "truename", nullable = true, length = 30)
    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    @Basic
    @Column(name = "classid", nullable = true)
    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userid == users.userid &&
                Objects.equals(roleid, users.roleid) &&
                Objects.equals(username, users.username) &&
                Objects.equals(userpwd, users.userpwd) &&
                Objects.equals(truename, users.truename) &&
                Objects.equals(classid, users.classid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, roleid, username, userpwd, truename, classid);
    }
}
