package com.example.demo.model;

import com.alibaba.excel.annotation.ExcelProperty;

public class ExceLInfo {
    @ExcelProperty("问卷题目")
    String invest_title;
    @ExcelProperty("问题题目")
    String question_content;
    @ExcelProperty("问题类型")
    String question_type;
    @ExcelProperty("答案题目")
    String answer_content;

    public ExceLInfo(String invest_title, String question_content, String question_type, String answer_content) {
        this.invest_title = invest_title;
        this.question_content = question_content;
        this.question_type = question_type;
        this.answer_content = answer_content;
    }

    @Override
    public String toString() {
        return "ExceLInfo{" +
                "invest_title='" + invest_title + '\'' +
                ", question_content='" + question_content + '\'' +
                ", question_type='" + question_type + '\'' +
                ", answer_content='" + answer_content + '\'' +
                '}';
    }

    public String getInvest_title() {
        return invest_title;
    }

    public void setInvest_title(String invest_title) {
        this.invest_title = invest_title;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public ExceLInfo() {
    }
}
