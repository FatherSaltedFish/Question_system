package com.example.demo.service;

import com.example.demo.model.message;

import java.util.List;

public interface messageService {

    public boolean insert_message(message message);

    List<message> selectAll();
}
