package com.example.demo.service;

import com.example.demo.model.i_q_a;
import org.springframework.stereotype.Service;

import java.util.List;
public interface i_q_aService {
    public List<i_q_a> selectByPrimaryKey(Integer invest_id);

    List<i_q_a> selectAll();

}
