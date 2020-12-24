package com.example.demo.repository;

import com.example.demo.model.invest_data;

import java.util.List;

public interface invest_dataMapper {
    List<invest_data> selectAll();

    List<invest_data> selectByinvest_id(Integer invest_id);

    List<invest_data> selectByquestion_id(Integer question_id);

}
