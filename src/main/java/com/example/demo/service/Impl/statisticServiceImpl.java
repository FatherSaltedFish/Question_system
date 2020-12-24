package com.example.demo.service.Impl;

import com.example.demo.model.statistic;
import com.example.demo.repository.statisticMapper;
import com.example.demo.service.statisticService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service("statisticService")
public class statisticServiceImpl implements statisticService {
    @Resource
    private com.example.demo.repository.statisticMapper statisticMapper;
    @Override
    public void addStatistic(statistic statistic) {
        statisticMapper.addStatistic(statistic);
    }

    @Override
    public void deleteByinvest_id(Integer invest_id) {
        statisticMapper.deleteByinvest_id(invest_id);
    }

    public void deleteByQuestion_id(Integer question_id){
        statisticMapper.deleteByQuestion_id(question_id);
    }

    @Override
    public List<statistic> selectanswered(String user_id) {
        return statisticMapper.selectanswered(user_id);

    }

    @Override
    public List<statistic> selectByinvest_idAnduser_id(Integer invest_id, Integer user_id) {
        return statisticMapper.selectByinvest_idAnduser_id(invest_id,user_id);
    }

    @Override
    public boolean ifexsists(statistic statistic) {
        if (statisticMapper.selectByquestion_idAnduser_id(statistic.getQuestion_id(),statistic.getUser_id()).isEmpty()){
            return false;
        }
        return true;
    }

    public void deleteByquestion_idAnduser_id(Integer question_id, Integer user_id) {
        statisticMapper.deleteByquestion_idAnduser_id(question_id,user_id);
    }

    @Override
    public List<statistic> selectByinvest_id(Integer invest_id) {
        return statisticMapper.selectByinvest_id(invest_id);
    }


}
