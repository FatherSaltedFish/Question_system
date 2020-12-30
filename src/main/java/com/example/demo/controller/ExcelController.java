package com.example.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.example.demo.Listener.ExcelListener;
import com.example.demo.Util.ExcelUtil;
import com.example.demo.model.*;
import com.example.demo.service.i_q_aService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LWong
 * @date 2020/03/06
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {


    @Resource
    private com.example.demo.service.i_q_aService i_q_aService;
    @Resource
    private com.example.demo.service.statisticService statisticService;
    @Resource
    private com.example.demo.service.questionService questionService;
    @Resource
    private com.example.demo.service.investService investService;
    @Resource
    private com.example.demo.service.answerService answerService;

    @RequestMapping("/downallexcel")
    public void getallExcel(HttpServletResponse response) throws IllegalAccessException, IOException,InstantiationException {
        List<i_q_a> list = i_q_aService.selectAll();
        ExcelUtil.download(response,i_q_a.class,list);
    }

    @RequestMapping("/downdataexcelByinvest_id")
    public void getpastExcel(@RequestParam(value = "id")String invest_id, HttpServletResponse response) throws IllegalAccessException, IOException,InstantiationException {
        List<statistic> list = statisticService.selectByinvest_id(Integer.valueOf(invest_id));
        ExcelUtil.download(response,invest_data.class,list);
    }

    @RequestMapping("/downexcelByinvest_id")
    public void getpastdataExcel(@RequestParam(value = "id")String invest_id, HttpServletResponse response) throws IllegalAccessException, IOException,InstantiationException {
        List<i_q_a> list = i_q_aService.selectByPrimaryKey(Integer.valueOf(invest_id));
        ExcelUtil.download(response,i_q_a.class,list);
    }

    @RequestMapping("/downtemplate")
    public void gettemplate(HttpServletResponse response) throws IllegalAccessException, IOException,InstantiationException {
        List<ExceLInfo> list =new ArrayList<ExceLInfo>();
        ExcelUtil.download(response,ExceLInfo.class,list);
    }


    @RequestMapping("/importexcel")
    public String importexcel(@RequestParam(value = "excelFile") MultipartFile file) throws IOException{
        System.out.println(1);
        EasyExcel.read(file.getInputStream(), ExceLInfo.class, new ExcelListener(investService,questionService,answerService)).sheet().doRead();
        return "redirect:/attendant/selectinvest";
    }
}
