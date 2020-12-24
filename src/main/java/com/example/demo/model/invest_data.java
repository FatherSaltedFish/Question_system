package com.example.demo.model;

import com.alibaba.excel.annotation.ExcelProperty;

public class invest_data {
    @ExcelProperty("问卷编号")
    Integer invest_id;
    @ExcelProperty("问题编号")
    Integer question_id;
    @ExcelProperty("答案编号")
    Integer answer_id;
    @ExcelProperty("数量")
    Integer number;

    public invest_data(Integer invest_id, Integer question_id, Integer answer_id, Integer number) {
        this.invest_id = invest_id;
        this.question_id = question_id;
        this.answer_id = answer_id;
        this.number = number;
        if (this.number==null)
            this.number=0;
    }

    public invest_data() {
    }

    @Override
    public String toString() {
        return "invest_data{" +
                "invest_id=" + invest_id +
                ", question_id=" + question_id +
                ", answer_id=" + answer_id +
                ", number=" + number +
                '}';
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        if(number==null)
            this.number=0;
        else
            this.number = number;
    }
}
