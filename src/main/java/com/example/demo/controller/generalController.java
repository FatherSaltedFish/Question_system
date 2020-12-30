package com.example.demo.controller;


import com.example.demo.model.invest_data;
import com.example.demo.model.member;
import com.example.demo.model.statistic;
import com.example.demo.service.memberService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@EnableAutoConfiguration
public class generalController {
    @Resource
    com.example.demo.service.memberService memberService;
    @Resource
    com.example.demo.service.statisticService statisticService;
    @Resource
    com.example.demo.service.answerService answerService;
    @Resource
    com.example.demo.service.invest_dataService invest_dataService;
    @ResponseBody
    @RequestMapping("/checkemail")
    public boolean checkemail(@RequestParam(value = "email") String email){
        if(memberService.getmemberByemail(email)==null)
            return true;
        else
            return false;
    }
    @ResponseBody
    @RequestMapping("/checktel")
    public boolean checktel(@RequestParam(value = "tel") String tel){
        if(memberService.getmemberBytel(tel)==null &&Pattern.matches("\\d{11}",tel)){
            return true;
        }
        else
            return false;
    }
    @ResponseBody
    @RequestMapping("/selectanswer")
    public List<invest_data> checkanswer(@RequestParam(value = "question_id") String question_id){
        return invest_dataService.selectByquestion_id(Integer.valueOf(question_id));
    }


    @RequestMapping({"/nouser","/nouser1"})
    public String nouser(){
        return "nouser";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session=request.getSession();//获取session并将userName存入session对象
        session.invalidate();
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping("/ifanswer")
    public boolean ifanswer(@RequestParam(value = "invest_id") String invest_id,@RequestParam(value = "user_id") String user_id){
        List<statistic> statistics=statisticService.selectanswered(user_id);
        for(statistic statistic:statistics){
            //System.out.println(statistic.getInvest_id()+":    :"+invest_id);
            if (statistic.getInvest_id()==Integer.valueOf(invest_id)){
                //System.out.println("success");
                return true;
            }
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/getmember")
    public member getmembet(@RequestParam(value = "id") String invest_id) {
        System.out.println(memberService.getmemberById(Integer.valueOf(invest_id)));
        return memberService.getmemberById(Integer.valueOf(invest_id));
    }
}
