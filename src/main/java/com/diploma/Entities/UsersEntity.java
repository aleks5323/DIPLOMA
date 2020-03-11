package com.diploma.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public", catalog = "diploma")
public class UsersEntity {
    private int uid;
    private String uname;
    private String uemail;
    private String upassword;

    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Id
    @Basic
    @Column(name = "uname")
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    @Basic
    @Column(name = "uemail")
    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    @Basic
    @Column(name = "upassword")
    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (uid != that.uid) return false;
//        if (uname != null ? !uname.equals(that.uname) : that.uname != null) return false;
//        if (uemail != null ? !uemail.equals(that.uemail) : that.uemail != null) return false;
//        if (upassword != null ? !upassword.equals(that.upassword) : that.upassword != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (uemail != null ? uemail.hashCode() : 0);
        result = 31 * result + (upassword != null ? upassword.hashCode() : 0);
        return result;
    }
}
