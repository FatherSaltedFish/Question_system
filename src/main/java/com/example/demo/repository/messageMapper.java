package com.example.demo.repository;

import com.example.demo.model.message;

import java.util.List;

public interface messageMapper {

    int insert_message(message message);

    List<message> selectAll();
}
