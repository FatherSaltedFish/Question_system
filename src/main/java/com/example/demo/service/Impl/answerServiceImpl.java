package com.example.demo.service.Impl;

import com.example.demo.model.answer;
import com.example.demo.model.question;
import com.example.demo.service.answerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("answerService")
public class answerServiceImpl implements answerService {

    @Resource
    private com.example.demo.repository.answerMapper answerMapper;
    @Override
    public List<answer> selectByquestion_id(Integer question_id) {
        return answerMapper.selectByquestion_id(question_id);
    }



    @Override
    public List<answer> selectByAll() {
        return answerMapper.selectByAll();
    }

    @Override
    public void deleteByquestion_id(Integer question_id) {
        answerMapper.deleteByquestion_id(question_id);
    }

    @Override
    public void addanswer(answer answer) {
        answerMapper.addanswer(answer);
    }


}
