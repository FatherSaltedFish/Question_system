package com.example.demo.service;

import com.example.demo.model.invest;

import java.util.List;

public interface investService {

    public List<invest> selectALL();

    public int addinvest(invest invest);

    invest selectinvestByid(Integer id);

    List<invest> selectBystatus(Integer integer);

    void deleteinvest(Integer id);

    void updataStatus(Integer invest_id,Integer status);

    int batchInsert(List<invest> invests);
}
