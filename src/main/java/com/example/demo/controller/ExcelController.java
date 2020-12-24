package com.example.demo.controller;


import com.alibaba.excel.EasyExcel;
import com.example.demo.Listener.ExcelListener;
import com.example.demo.Util.ExcelUtil;
import com.example.demo.model.i_q_a;
import com.example.demo.model.invest_data;
import com.example.demo.model.question;
import com.example.demo.model.statistic;
import com.example.demo.service.i_q_aService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

/**
 * @author LWong
 * @date 2020/03/06
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {


    @Resource
    private com.example.demo.service.i_q_aService i_q_aService;
    @Resource
    private com.example.demo.service.statisticService statisticService;
    @Resource
    private com.example.demo.service.questionService questionService;

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


    @RequestMapping("/importexcel")
    @ResponseBody
    public String importexcel(@RequestParam(value = "excelFile") MultipartFile file) throws IOException{
        EasyExcel.read(file.getInputStream(), question.class, new ExcelListener(questionService)).sheet().doRead();
        return "success";
    }
}
