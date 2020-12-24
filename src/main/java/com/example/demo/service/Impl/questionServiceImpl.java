package com.example.demo.service.Impl;

import com.example.demo.model.question;
import com.example.demo.service.questionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("questionService")
public class questionServiceImpl implements questionService {

    @Resource
    private com.example.demo.repository.questionMapper questionMapper;
    @Override
    public List<question> selectByInvest_id(Integer invest_id) {
        return questionMapper.selectByInvest_id(invest_id);
    }

    @Override
    public void deleteByInvest_id(Integer invest_id) {
        questionMapper.deleteByInvest_id(invest_id);
    }

    public void addquestion(question question){
        questionMapper.addquestion(question);
    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        questionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveAll(List<question> questions) {
        questionMapper.saveAll(questions);
    }
}
