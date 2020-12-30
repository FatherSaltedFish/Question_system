package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class member {
    Integer idno;

    String tel;
    String pwd;
    String username;
    String email;

    public int getIdno() {
        return idno;
    }

    public void setIdno(Integer idno) {
        this.idno = idno;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public member(Integer idno, String tel, String pwd, String username, String email) {
        this.idno = idno;
        this.tel = tel;
        this.pwd = pwd;
        this.username = username;
        this.email = email;
    }

    public member() {
    }

    @Override
    public String toString() {
        return "member{" +
                "idno=" + idno +
                ", tel='" + tel + '\'' +
                ", pwd='" + pwd + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
