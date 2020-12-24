package com.example.demo.repository;

import com.example.demo.model.statistic;

import java.util.List;

public interface statisticMapper {
    void addStatistic(statistic statistic);

    void deleteByinvest_id(Integer invest_id);

    void deleteByQuestion_id(Integer question_id);

    List<statistic> selectanswered(String user_id);

    List<statistic> selectByinvest_idAnduser_id(Integer invest_id,Integer user_id);

    List<statistic> selectByquestion_idAnduser_id(Integer question_id,Integer user_id);

    void deleteByquestion_idAnduser_id(Integer question_id,Integer user_id);

    List<statistic> selectByinvest_id(Integer invest_id);

}
