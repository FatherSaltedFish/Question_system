package com.example.demo.service;

import com.example.demo.model.*;

import java.util.List;

public interface memberService {

    public member getmemberById(Integer idno);

    public member getmemberBytel(String tel);

    public member getmemberByemail(String email);

    public void addmember(member member);

    public List<member> selectAll();

    void deletememberByprimarykey(String idno);

    void updatememberpwd(String idno,String pwd);
}
