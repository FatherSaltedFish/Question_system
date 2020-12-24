package com.example.demo.service;

import com.example.demo.model.question;

import java.util.List;

public interface questionService {
    List<question> selectByInvest_id(Integer invest_id);

    void deleteByInvest_id(Integer invest_id);

    void addquestion(question question);

    void deleteByPrimaryKey(Integer id);

    void saveAll(List<question> questions);

}
