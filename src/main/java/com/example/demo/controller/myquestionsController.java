package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.i_q_aService;
import com.example.demo.service.invest_dataService;
import com.example.demo.service.statisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@EnableAutoConfiguration
@Controller
@RequestMapping("/myquestions")
public class myquestionsController {
    @Resource
    private com.example.demo.service.memberService memberService;
    @Resource
    private com.example.demo.service.investService investService;
    @Resource
    private com.example.demo.service.i_q_aService i_q_aService;
    @Resource
    private com.example.demo.service.answerService answerService;
    @Resource
    private com.example.demo.service.questionService questionService;
    @Resource
    private com.example.demo.service.statisticService statisticService;
    @Resource
    private com.example.demo.service.invest_dataService invest_dataService;



    @RequestMapping("/main")
    public String main(ModelMap model,HttpServletRequest request){
//        HttpSession session=request.getSession();//获取session并将userName存入session对象
//        String tel=(String)session.getAttribute("tel");
//        Object test=this.memberService.getmemberBytel(tel);
//        member member = (member)test;
//        model.addAttribute("member",member);
        HttpSession session=request.getSession();//获取session并将userName存入session对象
        Integer idno= (Integer) session.getAttribute("idno");
        System.out.println("idno:"+idno);
        if(idno==null)
            return "nouser";
        member member=this.memberService.getmemberById(idno);
        model.addAttribute("member",member);
        model.addAttribute("opttype","main");
        return "myquestions";
    }
    @RequestMapping("/questions")
    public String questions(@RequestParam(value="range",required = false) String range,@RequestParam(value = "question_name",required = false) String question_name, ModelMap model,HttpServletRequest request){
        Integer user_id=null;
        if (question_name!=null){
            System.out.println(question_name);
            model.addAttribute("questionlist",this.investService.Liketitle(question_name));
            model.addAttribute("opttype","questions");
            return "myquestions";
        }
        else
            System.out.println(question_name);

        if(range!=null)
        {
            List<statistic> statistics=null;
            List<invest> invests=new ArrayList<invest>();
            switch (range) {
                case "all":model.addAttribute("questionlist", this.investService.selectALL());break;
                case "answered":
                    user_id= (Integer) request.getSession().getAttribute("idno");
                     statistics=statisticService.selectanswered(String.valueOf(user_id));
                    for(statistic statistic:statistics){
                        invest invest=investService.selectinvestByid(Integer.valueOf(statistic.getInvest_id()));
                        invests.add(invest);
                    }
                    model.addAttribute("questionlist", invests);break;
                case "unanswered":
                    user_id= (Integer) request.getSession().getAttribute("idno");
                    statistics=statisticService.selectanswered(String.valueOf(user_id));
                    invests=investService.selectALL();
                    List<invest> investList=new ArrayList<invest>();
                    for(statistic statistic:statistics){
                        for(invest invest:invests) {
                            if (statistic.getInvest_id().equals(invest.getId())){
                                investList.add(invest);
                            }
                        }
                    }
                    for(invest invest:investList)
                        invests.remove(invest);
                    model.addAttribute("questionlist",invests);break;
                case "stopped":model.addAttribute("questionlist", this.investService.selectBystatus(2));break;
                case "running":model.addAttribute("questionlist", this.investService.selectBystatus(1));break;
                case "will":model.addAttribute("questionlist", this.investService.selectBystatus(0));break;

                default:System.out.println("selectquestion is wrong");break;
            }
            model.addAttribute("opttype", "questions");
            return "myquestions";
        }
        else {
            model.addAttribute("opttype", "questions");
            model.addAttribute("questionlist", this.investService.selectALL());
            return "myquestions";
        }
    }

    @RequestMapping("/showinvest")//展示问卷
    public String showinvest(ModelMap model,HttpServletRequest request,@RequestParam(value="id",required = false) String id){
        model.addAttribute("invest",investService.selectinvestByid(Integer.valueOf(id)));
        model.addAttribute("i_q_a", i_q_aService.selectByPrimaryKey(Integer.valueOf(id)));//查询视图
        model.addAttribute("questionlist",questionService.selectByInvest_id(Integer.valueOf(id)));
        model.addAttribute("answerlist",answerService.selectByAll());
        model.addAttribute("opttype","showinvest");
        return "myquestions";
    }
    @ResponseBody
    @RequestMapping("/addstatistic")
    public String addstatistic(ModelMap model,@RequestParam(value="invest_id") String invest_id,@RequestParam(value = "question_id") String question_id,
                               @RequestParam(value = "answer_id") String answer_id,@RequestParam(value="use_id") String user_id){
        statistic statistic =new statistic();
        statistic.setInvest_id(Integer.valueOf(invest_id));
        statistic.setQuestion_id(Integer.valueOf(question_id));
        statistic.setAnswer_id(Integer.valueOf(answer_id));
        statistic.setUser_id(Integer.valueOf(user_id));
        statisticService.addStatistic(statistic);
        return statistic.toString();
    }

    @RequestMapping("/save")
    public String save(ModelMap model,@RequestParam(value = "invest_id")String invest_id,@RequestParam(value = "user_id")String user_id,HttpServletRequest request){
        List<question> question_list=questionService.selectByInvest_id(Integer.valueOf(invest_id));
        statistic statistic=new statistic();
        statistic.setInvest_id(Integer.valueOf(invest_id));
        statistic.setUser_id(Integer.valueOf(user_id));
        for(question question:question_list){
            statistic.setQuestion_id(question.getId());
            statisticService.deleteByquestion_idAnduser_id(statistic.getQuestion_id(), statistic.getUser_id());
            String[] outerArray=request.getParameterValues(String.valueOf(question.getId()));
            if(question.getType().equals("text")){
                answer answer=new answer();
                answer.setContent(outerArray[0]);
                answer.setQuestion_id(Integer.valueOf(question.getId()));
                answerService.addanswer(answer);
                statistic.setAnswer_id(answer.getId());
                statisticService.addStatistic(statistic);
                continue;
            }
            for(String outer:outerArray) {
                statistic.setAnswer_id(Integer.valueOf(outer));
                statisticService.addStatistic(statistic);
            }
        }
        return "redirect:/myquestions/questions";
    }

    @RequestMapping("/showdata")
    public String showdata(ModelMap model,@RequestParam(value = "id")String invest_id){
        model.addAttribute("invest_dataList", invest_dataService.selectByinvest_id(Integer.valueOf(invest_id)));
        model.addAttribute("invest",investService.selectinvestByid(Integer.valueOf(invest_id)));
        List<question> questionList=questionService.selectByInvest_id(Integer.valueOf(invest_id));
        model.addAttribute("questionList",questionList);
        model.addAttribute("answerList",answerService.selectByAll());
        model.addAttribute("opttype","showdata");
        return "myquestions";
    }

    @RequestMapping("/reanswer")
    public String reanswer(ModelMap model,HttpServletRequest request,@RequestParam(value="invest_id",required = false) String invest_id){
        model.addAttribute("invest",investService.selectinvestByid(Integer.valueOf(invest_id)));
        //model.addAttribute("i_q_a", i_q_aService.selectByPrimaryKey(Integer.valueOf(invest_id)));//查询视图
        model.addAttribute("questionlist",questionService.selectByInvest_id(Integer.valueOf(invest_id)));
        model.addAttribute("answerlist",answerService.selectByAll());
        model.addAttribute("opttype","showinvest");
        model.addAttribute("statisticList",statisticService.selectByinvest_idAnduser_id(Integer.valueOf(invest_id), (Integer) request.getSession().getAttribute("idno")));
        return "myquestions";
    }

    @ResponseBody
    @RequestMapping("/change_info")
    public boolean change_info(ModelMap model,HttpServletRequest request,@RequestParam(value="username",required = false) String username,@RequestParam(value="pwd",required = false) String pwd,
                              @RequestParam(value = "tel",required = false)String tel,@RequestParam(value = "email",required = false)String email){
        System.out.println("start");
        String idno= String.valueOf( request.getSession().getAttribute("idno"));
        System.out.println("get idno success");
        if(username!=null){
            System.out.println("start username ");
            this.memberService.updatememberusername(idno,username);
            request.getSession().setAttribute("userName",username);
            System.out.println("end username ");
            return true;
        }
        if(pwd!=null){
            System.out.println("start pwd ");
            this.memberService.updatememberpwd(idno,pwd);
            System.out.println("end pwd ");
            return true;
        }
        if(tel!=null){
            System.out.println("start tel ");
            this.memberService.updatemembertel(idno,tel);
            System.out.println("end tel ");
            return true;
        }
        if(email!=null){
            System.out.println("start email ");
            this.memberService.updatememberemail(idno,email);
            System.out.println("end email ");
            return true;
        }
        return false;
    }
}
