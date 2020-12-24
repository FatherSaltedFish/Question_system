package com.example.demo.service;

import com.example.demo.model.invest_data;

import java.util.List;

public interface invest_dataService {
    List<invest_data> selectAll();

    List<invest_data> selectByinvest_id(Integer invest_id);

    List<invest_data> selectByquestion_id(Integer question_id);

}
