package com.example.demo.model;

import java.util.Objects;

public class question {
    Integer id;
    String content;
    Integer invest_id;
    String type;

    public question(String content, String type) {
        this.content = content;
        this.type = type;
    }

    public question(Integer id, String content, Integer invest_id, String type) {
        this.id = id;
        this.content = content;
        this.invest_id = invest_id;
        this.type = type;
    }

    public question() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        question question = (question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(content, question.content) &&
                Objects.equals(invest_id, question.invest_id) &&
                Objects.equals(type, question.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, invest_id, type);
    }

    public question(String content, Integer invest_id, String type) {
        this.content = content;
        this.invest_id = invest_id;
        this.type = type;
    }

    @Override
    public String toString() {
        return "question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", invest_id=" + invest_id +
                ", type='" + type + '\'' +
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
