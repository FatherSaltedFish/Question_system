package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.invest_dataService;
import com.example.demo.service.messageService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableAutoConfiguration
@RequestMapping("/attendant")
public class attendantController {

//    // 注入mapper类
//    @Resource
//    private attendantService attendantService;
//
//    @RequestMapping(value="{idno}", method= RequestMethod.GET, produces="application/json")
//    public attendant getUser(@PathVariable String idno) throws Exception {
//        attendant attendant = this.attendantService.getattendantByidno(idno);
//        return attendant;
//    }
//
    @Resource
    private com.example.demo.service.investService investService;
    @Resource
    private com.example.demo.service.attendantService attendantService;
    @Resource
    private com.example.demo.service.i_q_aService i_q_aService;
    @Resource
    private com.example.demo.service.answerService answerService;
    @Resource
    private com.example.demo.service.questionService questionService;
    @Resource
    private com.example.demo.service.memberService memberService;
    @Resource
    private com.example.demo.service.statisticService statisticService;
    @Resource
    private com.example.demo.service.messageService messageService;
    @Resource
    private com.example.demo.service.invest_dataService invest_dataService;



    @RequestMapping("/main")//我的信息
    public String main(ModelMap model,HttpServletRequest request){
        HttpSession session=request.getSession();//获取session并将userName存入session对象
        String idno= (String) session.getAttribute("idno");
        attendant attendant=this.attendantService.getattendantByidno(idno);
        model.addAttribute("attendant",attendant);
        model.addAttribute("opttype","main");
        model.addAttribute("opttype","main");
        return "attendant";
    }


    @RequestMapping("/selectinvest")//查询问卷
    public String questions(@RequestParam(value="range",required = false) String range,@RequestParam(value = "question_name",required = false) String question_name,ModelMap model){
        if (question_name!=null){
            model.addAttribute("questionlist",this.investService.Liketitle(question_name));
            model.addAttribute("opttype","selectinvest");
            return "attendant";
        }

        if(range!=null) {
            switch (range) {
                case "stopped":
                    model.addAttribute("questionlist", this.investService.selectBystatus(2));
                    break;
                case "running":
                    model.addAttribute("questionlist", this.investService.selectBystatus(1));
                    break;
                case "will":
                    model.addAttribute("questionlist", this.investService.selectBystatus(0));
                    break;
            }
        }else {
            model.addAttribute("questionlist", this.investService.selectALL());
        }
        model.addAttribute("opttype","selectinvest");
        return "attendant";
    }

    @RequestMapping("/addinvest")//添加问卷页面跳转
    public String addquestions(ModelMap model,HttpServletRequest request){
//        HttpSession session=request.getSession();//获取session并将userName存入session对象
//        String idno=(String)session.getAttribute("idno");
//        Object test=this.attendantService.getattendantByidno(idno);
//        attendant attendant = (attendant)test;
//        model.addAttribute("attendant",attendant);
        model.addAttribute("opttype","addinvest");
        return "attendant";
    }

    @RequestMapping("/showinvest")//添加问题 跳转
    public String showinvest(@RequestParam(value="title",required = false) String title,@RequestParam(value="id",required = false) String invest_id,ModelMap model,HttpServletRequest request){
//        HttpSession session=request.getSession();//获取session并将userName存入session对象
//        String idno=(String)session.getAttribute("idno");
//        Object test=this.attendantService.getattendantByidno(idno);
//        attendant attendant = (attendant)test;
//        model.addAttribute("attendant",attendant);
        Integer id;
        if(title!=null) {
            invest invest = new invest();
            invest.setTitle(title);
            investService.addinvest(invest);
            return "redirect:/attendant/showinvest?id="+invest.getId();
        }
        else
            id=Integer.valueOf(invest_id);
        model.addAttribute("invest",investService.selectinvestByid(Integer.valueOf(id)));
        model.addAttribute("i_q_a", i_q_aService.selectByPrimaryKey(Integer.valueOf(id)));//查询视图
        model.addAttribute("questionlist",questionService.selectByInvest_id(Integer.valueOf(id)));
        model.addAttribute("answerlist",answerService.selectByAll());
        model.addAttribute("opttype","showinvest");
        return "attendant";
    }

    @RequestMapping("/deleteinvest")//删除问卷
    public String deleteinvest(@RequestParam(value="id") String id,ModelMap model,HttpServletRequest request){
        investService.deleteinvest(Integer.valueOf(id));
        List<question> questionList=questionService.selectByInvest_id(Integer.valueOf(id));
        questionService.deleteByInvest_id(Integer.valueOf(id));
        for(question question:questionList){
            answerService.deleteByquestion_id(question.getId());
        }
        statisticService.deleteByinvest_id(Integer.valueOf(id));

        model.addAttribute("opttype","selectinvest");
        model.addAttribute("questionlist",this.investService.selectALL());
        return "attendant";
    }

    @RequestMapping("/addquestion")//添加问题
    public String addquestion(@RequestParam(value="id") String id,ModelMap model,HttpServletRequest request){
        model.addAttribute("opttype","addquestion");
        model.addAttribute("id",id);
        return "attendant";
    }

    @RequestMapping("/changeinvest")//修改问卷
    public String changeinvest(@RequestParam(value="id") String id,ModelMap model,HttpServletRequest request){
        return "attendant";
    }

    @RequestMapping("/selectmember")//查询所有用户
    public String selectmember(ModelMap model){
        model.addAttribute("memberList",memberService.selectAll());
        model.addAttribute("opttype","selectmember");
        return "attendant";
    }

    @RequestMapping("/deletemember")//删除用户
    public String deletemember(ModelMap model,@RequestParam(value="idno") String idno){
        memberService.deletememberByprimarykey(idno);
        model.addAttribute("memberList",memberService.selectAll());
        model.addAttribute("opttype","selectmember");
        return "attendant";
    }

    @RequestMapping("/changemember")
    public String changemember(ModelMap model,@RequestParam(value="idno") String idno,@RequestParam(value="pwd") String pwd){
        memberService.updatememberpwd(idno,pwd);
        model.addAttribute("memberList",memberService.selectAll());
        model.addAttribute("opttype","selectmember");
        return "attendant";
    }

    @RequestMapping("/save")//添加问题
    public String  save(ModelMap model,@RequestParam(value="invest_id") String invest_id,@RequestParam(value="type") String type,@RequestParam(value="addquestion") String addquestion,@RequestParam(value="addanswer") String[] addanswer,HttpServletRequest request){
        question question=new question();
        question.setContent(addquestion);
        question.setInvest_id(Integer.valueOf(invest_id));
        question.setType(type);
        questionService.addquestion(question);
        for(String answer:addanswer){
            answer a=new answer();
            a.setContent(answer);
            a.setQuestion_id(question.getId());
            answerService.addanswer(a);
        }

        return ("redirect:/attendant/showinvest?id="+invest_id);
    }

    @RequestMapping("/addmember")
    public String addmember(ModelMap model){
        model.addAttribute("opttype","addmember");
        return "attendant";
    }

    @RequestMapping("/addmember2")
    public String addmember2(ModelMap model,@RequestParam(value="tel") String tel,@RequestParam(value="name") String name,
                             @RequestParam(value="pwd") String pwd,@RequestParam(value="email") String email){
        memberService.addmember(new member(0,tel,pwd,name,email));
        return "redirect:/attendant/selectmember";
    }

    @RequestMapping("/delquestion")
    public String delquestion(ModelMap model,HttpServletRequest request,@RequestParam(value = "question_id")String question_id,@RequestParam(value = "invest_id")String invest_id){
        questionService.deleteByPrimaryKey(Integer.valueOf(question_id));
        answerService.deleteByquestion_id(Integer.valueOf(question_id));
        statisticService.deleteByQuestion_id(Integer.valueOf(question_id));
        return this.showinvest(null,invest_id,model,request);
    }

    @RequestMapping("/showmessage")
    public String showmessage(ModelMap model){
        model.addAttribute("messageList",messageService.selectAll());
        model.addAttribute("opttype","showmessage");
        return "attendant";
    }

    @RequestMapping("/showdata")
    public String showdata(ModelMap model,@RequestParam(value = "id")String invest_id){
        model.addAttribute("invest_dataList",invest_dataService.selectByinvest_id(Integer.valueOf(invest_id)));
        model.addAttribute("invest",investService.selectinvestByid(Integer.valueOf(invest_id)));
        List<question> questionList=questionService.selectByInvest_id(Integer.valueOf(invest_id));
        model.addAttribute("questionList",questionList);
        model.addAttribute("answerList",answerService.selectByAll());
        model.addAttribute("opttype","showdata");
        return "attendant";
    }

    @RequestMapping("/releasedata")
    public String releasedata(ModelMap model,@RequestParam(value = "id")String invest_id){
        investService.updataStatus(Integer.valueOf(invest_id),1);
        return "redirect:/attendant/selectinvest";
    }

    @RequestMapping("/stopdata")
    public String stopdata(ModelMap model,@RequestParam(value = "id")String invest_id){
        investService.updataStatus(Integer.valueOf(invest_id),2);
        return "redirect:/attendant/selectinvest";
    }

    @ResponseBody
    @RequestMapping("/user_number")
    public String user_number(){
        System.out.println(memberService.getalluser_number());
        return memberService.getalluser_number();
    }

}