package com.example.demo.service.Impl;

import com.example.demo.model.invest_data;
import com.example.demo.service.invest_dataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("invest_dataService")
public class invest_dataServiceImpl implements invest_dataService {
    @Resource
    com.example.demo.repository.invest_dataMapper invest_dataMapper;

    @Override
    public List<invest_data> selectAll() {
        return invest_dataMapper.selectAll();
    }

    @Override
    public List<invest_data> selectByinvest_id(Integer invest_id) {
        return invest_dataMapper.selectByinvest_id(invest_id);
    }

    @Override
    public List<invest_data> selectByquestion_id(Integer question_id) {
        return invest_dataMapper.selectByquestion_id(question_id);
    }

}
