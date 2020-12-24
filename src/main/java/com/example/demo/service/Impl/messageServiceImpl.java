package com.example.demo.service.Impl;

import com.example.demo.model.message;
import com.example.demo.repository.messageMapper;
import com.example.demo.service.messageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("messageService")
public class messageServiceImpl implements messageService {
    @Resource
    private com.example.demo.repository.messageMapper messageMapper;

    @Override
    public boolean insert_message(message message) {

        if(messageMapper.insert_message(message)==1)
            return true;
        else
            return false;
    }

    @Override
    public List<message> selectAll() {
        return messageMapper.selectAll();
    }
}
