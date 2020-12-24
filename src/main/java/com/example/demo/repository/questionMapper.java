package com.example.demo.repository;

import com.example.demo.model.question;

import java.util.List;

public interface questionMapper {
    List<question> selectByInvest_id(Integer invest_id);

    void deleteByInvest_id(Integer invest_id);

    void addquestion(question question);

    void deleteByPrimaryKey(Integer invest_id);

    void saveAll(List<question> questions);
}
