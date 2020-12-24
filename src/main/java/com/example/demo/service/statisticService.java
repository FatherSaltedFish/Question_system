package com.example.demo.service;

import com.example.demo.model.statistic;

import java.util.List;

public interface statisticService {
    void addStatistic(statistic statistic);

    void deleteByinvest_id(Integer invest_id);

    void deleteByQuestion_id(Integer question_id);

    List<statistic> selectanswered(String user_id);

    List<statistic> selectByinvest_idAnduser_id(Integer invest_id,Integer user_id);

    boolean ifexsists(statistic statistic);

    void deleteByquestion_idAnduser_id(Integer question_id,Integer user_id);

    List<statistic> selectByinvest_id(Integer invest_id);

}
