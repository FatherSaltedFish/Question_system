package com.example.demo.model;

import java.util.Objects;

public class answer {
    Integer id;
    String content;
    Integer question_id;

    public answer(String content) {
        this.content = content;
    }

    public answer(String content, Integer question_id) {
        this.content = content;
        this.question_id = question_id;
    }

    @Override
    public String toString() {
        return "answer{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", question_id=" + question_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        answer answer = (answer) o;
        return Objects.equals(id, answer.id) &&
                Objects.equals(content, answer.content) &&
                Objects.equals(question_id, answer.question_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, question_id);
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
