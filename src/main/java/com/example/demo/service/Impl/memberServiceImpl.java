package com.example.demo.service.Impl;

import org.springframework.stereotype.Service;
import com.example.demo.model.*;
import com.example.demo.service.*;
import javax.annotation.Resource;
import com.example.demo.repository.*;

import java.util.List;

@Service("memberService")
public class memberServiceImpl implements memberService {

    // 注入mapper类
    @Resource
    private memberMapper memberMapper;

    @Override
    public member getmemberById(Integer idno) {
        return memberMapper.selectByPrimaryKey(idno);
    }

    @Override
    public member getmemberBytel(String tel) {
        return memberMapper.selectBytel(tel);
    }

    @Override
    public member getmemberByemail(String email) {
        return memberMapper.selectByemail(email);
    }

    @Override
    public void addmember(member member) {
        memberMapper.addmember(member);
    }

    @Override
    public List<member> selectAll() {
        return memberMapper.selectAll();
    }

    @Override
    public void deletememberByprimarykey(String idno) {
        memberMapper.deletememberByprimarykey(idno);
    }

    @Override
    public void updatememberpwd(String idno, String pwd) {
        memberMapper.updatememberpwd(idno,pwd);
    }

    @Override
    public void updatememberusername(String idno, String username) {
        memberMapper.updatememberusername(idno,username);
    }

    @Override
    public void updatemembertel(String idno, String tel) {
        memberMapper.updatemembertel(idno,tel);
    }

    @Override
    public void updatememberemail(String idno, String email) {
        memberMapper.updatememberemail(idno,email);
    }

    @Override
    public String getalluser_number() {
        return memberMapper.getalluser_number();
    }
}
