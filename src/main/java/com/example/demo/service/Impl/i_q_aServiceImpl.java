package com.example.demo.service.Impl;

import com.example.demo.model.i_q_a;
import com.example.demo.service.i_q_aService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("i_q_aService")
public class i_q_aServiceImpl implements i_q_aService {
    @Resource
    private com.example.demo.repository.i_q_aMapper i_q_aMapper;

    @Override
    public List<i_q_a> selectByPrimaryKey(Integer invest_id) {
        return i_q_aMapper.selectByPrimaryKey(invest_id);
    }

    @Override
    public List<i_q_a> selectAll() {
        return i_q_aMapper.selectAll();
    }
}
