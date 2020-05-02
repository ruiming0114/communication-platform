package com.rm.plat.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int userid;
    private String username;
    private String password;
    private String perms;
    private int sex;//0boy 1girl
    private String userimgpath;
    private Date birthday;
    private String email;
    private String signature;
    private String securityQ;
    private String SecurityA;

    public int getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPerms() {
        return perms;
    }

    public int getSex() {
        return sex;
    }

    public String getUserimgpath() {
        return userimgpath;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getSignature() {
        return signature;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public String getSecurityA() {
        return SecurityA;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setUserimgpath(String userimgpath) {
        this.userimgpath = userimgpath;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public void setSecurityA(String securityA) {
        SecurityA = securityA;
    }
}
