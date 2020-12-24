package com.example.demo.model;

import java.util.Date;

public class invest {
    Integer id;
    String title;
    Integer status;//0 未开始 1 正在进行 2已结束
    Date created;

    @Override
    public String toString() {
        return "invest{" +
                "id=" + id +
                ", status=" + status +
                ", title='" + title + '\'' +
                ", created=" + created +
                '}';
    }

    public invest() {
    }

    public invest(Integer id,  String title,Integer status, Date created) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
