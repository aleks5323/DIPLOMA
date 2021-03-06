package com.diploma.Entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users", schema = "public", catalog = "diploma")
public class UsersEntity {
    private int uid;
    private String uname;
    private String uemail;
    private String upassword;

    @Id
    @Column(name = "uid")
    @SequenceGenerator(name="conSeqGen", sequenceName = "users_uid_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conSeqGen")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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
