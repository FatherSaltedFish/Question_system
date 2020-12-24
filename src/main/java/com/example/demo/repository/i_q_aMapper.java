package com.example.demo.repository;

import com.example.demo.model.i_q_a;

import java.util.List;

public interface i_q_aMapper {
    List<i_q_a> selectByPrimaryKey(Integer invest_id);
    List<i_q_a> selectAll();
}
