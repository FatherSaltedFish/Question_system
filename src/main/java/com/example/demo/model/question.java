package com.example.demo.model;

public class question {
    Integer id;
    String content;
    Integer invest_id;

    public question(Integer id, String content, Integer invest_id) {
        this.id = id;
        this.content = content;
        this.invest_id = invest_id;
    }

    public question() {
    }

    @Override
    public String toString() {
        return "question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", invest_id=" + invest_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getInvest_id() {
        return invest_id;
    }

    public void setInvest_id(Integer invest_id) {
        this.invest_id = invest_id;
    }
}
