package com.example.demo.model;

public class answer {
    Integer id;
    String content;
    Integer question_id;

    @Override
    public String toString() {
        return "answer{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", question_id=" + question_id +
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

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public answer() {
    }

    public answer(Integer id, String content, Integer question_id) {
        this.id = id;
        this.content = content;
        this.question_id = question_id;
    }
}
