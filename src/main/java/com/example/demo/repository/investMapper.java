package com.example.demo.repository;

import com.example.demo.model.invest;

import java.util.List;

public interface investMapper {
    List<invest> selectAll();
    List<invest> selectBystatus(Integer Integer);

    int addinvest(invest invest);

    invest selectinvestByid(Integer id);

    void deleteinvest(Integer id);

    void updataStatus(Integer invest_id,Integer status);

    int batchInsert(List<invest> invests);

}
