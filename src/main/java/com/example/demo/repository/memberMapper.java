package com.example.demo.repository;

import com.example.demo.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface memberMapper {

    // 对应xml映射文件元素的ID
    member selectByPrimaryKey(Integer idno);

    member selectBytel(String tel);

    member selectByemail(String email);

    void addmember(member member);

    List<member> selectAll();

    void deletememberByprimarykey(String idno);

    void updatememberpwd(String idno,String pwd);

    void updatememberusername(String idno,String username);

    void updatemembertel(String idno,String tel);

    void updatememberemail(String idno,String email);

    String getalluser_number();

}
