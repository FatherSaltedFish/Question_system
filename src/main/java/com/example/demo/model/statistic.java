package com.example.demo.model;

import java.util.Date;

public class statistic {
    Integer id,invest_id,question_id,answer_id,user_id;
    Date created;

    @Override
    public String toString() {
        return "statistic{" +
                "id=" + id +
                ", invest_id=" + invest_id +
                ", question_id=" + question_id +
                ", answer_id=" + answer_id +
                ", user_id=" + user_id +
                ", created=" + created +
                '}';
    }

    public statistic(Integer id, Integer invest_id, Integer question_id, Integer answer_id, Integer user_id, Date created) {
        this.id = id;
        this.invest_id = invest_id;
        this.question_id = question_id;
        this.answer_id = answer_id;
        this.user_id = user_id;
        this.created = created;
    }

    public statistic() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInvest_id() {
        return invest_id;
    }

    public void setInvest_id(Integer invest_id) {
        this.invest_id = invest_id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public Integer getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
