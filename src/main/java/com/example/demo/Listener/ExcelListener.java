package com.example.demo.Listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.model.ExceLInfo;
import com.example.demo.model.answer;
import com.example.demo.model.invest;
import com.example.demo.model.question;
import com.example.demo.service.answerService;
import com.example.demo.service.investService;
import com.example.demo.service.questionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 有个很重要的点 ExcelListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class ExcelListener extends AnalysisEventListener<ExceLInfo> {

    private List<ExceLInfo> list = new ArrayList<>();
    private List<invest> list_invest = new ArrayList<>();
    private List<question> list_question = new ArrayList<>();
    private List<answer> list_answer = new ArrayList<>();
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;

    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private investService investService;
    private questionService questionService;
    private com.example.demo.service.answerService answerService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public ExcelListener(investService investService,questionService questionService,answerService answerService) {
        this.investService=investService;
        this.questionService = questionService;
        this.answerService=answerService;
    }


    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(ExceLInfo goods, AnalysisContext analysisContext) {
        System.out.println("解析到一条数据:========================"+goods.toString());
        // 数据存储到datas，供批量处理，或后续自己业务逻辑处理。
        list.add(goods);
        list_invest.add(new invest(goods.getInvest_title()));
        //list_question.add(new question(goods.getQuestion_content(),goods.getQuestion_type()));
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if(list.size() >= BATCH_COUNT){
            saveData();
            // 存储完成清理datas
            list.clear();
        }

    }

    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();//确保所有数据都能入库
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        int invest_id=0;
        Set<invest> set_invest=new HashSet<>();
        set_invest.addAll(list_invest);
        list_invest.clear();
        list_invest.addAll(set_invest);
        System.out.println("=============================="+list_invest.size()+"条数据，开始存储到invest数据库");
        for(invest invest:list_invest){
            investService.addinvest(invest);
        }

        for (ExceLInfo exceLInfo:list){
            for (invest invest:list_invest){
                if(exceLInfo.getInvest_title().equals(invest.getTitle())){
                    list_question.add(new question(exceLInfo.getQuestion_content(),invest.getId(),exceLInfo.getQuestion_type()));
                }
            }
        }
        Set<question> questionSet=new HashSet<>();
        questionSet.addAll(list_question);
        list_question.clear();
        list_question.addAll(questionSet);
        System.out.println("=============================="+list_question.size()+"条数据，开始存储到question数据库");
        questionService.saveAll(list_question);


        for (ExceLInfo exceLInfo:list){
            for (question question:list_question){
                if(exceLInfo.getQuestion_content().equals(question.getContent())){
                    list_answer.add(new answer(exceLInfo.getAnswer_content(),question.getId()));
                }
            }
        }
        System.out.println("=============================="+list_answer.size()+"条数据，开始存储到answer数据库");
        answerService.saveAll(list_answer);

    }

}
