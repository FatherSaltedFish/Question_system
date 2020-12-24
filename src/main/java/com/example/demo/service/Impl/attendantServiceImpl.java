package com.example.demo.service.Impl;

import com.example.demo.model.attendant;
import com.example.demo.repository.attendantMapper;
import com.example.demo.repository.memberMapper;
import com.example.demo.service.attendantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("attendantService")
public class attendantServiceImpl implements attendantService {
    @Resource
    private com.example.demo.repository.attendantMapper attendantMapper;
    @Override
    public attendant getattendantByidno(String idno) {
        return attendantMapper.selectByPrimaryKey(idno);
    }
}
