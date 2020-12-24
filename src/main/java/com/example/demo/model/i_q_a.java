package com.example.demo.model;

import com.alibaba.excel.annotation.ExcelProperty;

public class i_q_a {
    @ExcelProperty("问卷编号")
    Integer invest_id;
    @ExcelProperty("问卷标题")
    String title;
    @ExcelProperty("问题编号")
    Integer question_id;
    @ExcelProperty("问题内容")
    String question_content;
    @ExcelProperty("答案内容")
    String answer_content;

    @Override
    public String toString() {
        return "i_q_a{" +
                "invest_id=" + invest_id +
                ", title='" + title + '\'' +
                ", question_id=" + question_id +
                ", question_content='" + question_content + '\'' +
                ", answer_content='" + answer_content + '\'' +
                '}';
    }

    public i_q_a() {
    }

    public i_q_a(Integer invest_id, String title, Integer question_id, String question_content, String answer_content) {
        this.invest_id = invest_id;
        this.title = title;
        this.question_id = question_id;
        this.question_content = question_content;
        this.answer_content = answer_content;
    }

    public Integer getInvest_id() {
        return invest_id;
    }

    public void setInvest_id(Integer invest_id) {
        this.invest_id = invest_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }
}
