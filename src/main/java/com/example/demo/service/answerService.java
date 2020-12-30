package com.example.demo.service;

import com.example.demo.model.answer;
import com.example.demo.model.invest;
import com.example.demo.model.question;

import java.util.List;

public interface answerService {
    List<answer> selectByquestion_id(Integer question_id);

    List<answer> selectByAll();

    void deleteByquestion_id(Integer question_id);

    void addanswer(answer answer);

    void saveAll(List<answer> answers);

}
