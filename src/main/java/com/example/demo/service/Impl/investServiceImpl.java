package com.example.demo.service.Impl;

import com.example.demo.model.invest;
import com.example.demo.model.member;
import com.example.demo.repository.investMapper;
import com.example.demo.repository.memberMapper;
import com.example.demo.repository.statisticMapper;
import com.example.demo.service.investService;
import com.example.demo.service.questionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("investService")
public class investServiceImpl implements investService {

    @Resource
    private investMapper investMapper;

    @Override
    public List<invest> selectALL() {
        return investMapper.selectAll();
    }


    @Override
    public int addinvest(invest invest) {
        return investMapper.addinvest(invest);
    }

    @Override
    public invest selectinvestByid(Integer id) {
        return investMapper.selectinvestByid(id);
    }

    @Override
    public List<invest> selectBystatus(Integer Integer) {
        return investMapper.selectBystatus(Integer);
    }

    @Override
    public void deleteinvest(Integer id) {
        investMapper.deleteinvest(id);
    }

    @Override
    public void updataStatus(Integer invest_id, Integer status) {
        investMapper.updataStatus(invest_id,status);
    }

    @Override
    public int batchInsert(List<invest> invests) {
        return investMapper.batchInsert(invests);
    }

}
