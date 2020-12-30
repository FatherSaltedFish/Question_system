package com.example.demo.controller;

import com.example.demo.model.attendant;
import com.example.demo.model.member;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ws.server.endpoint.interceptor.DelegatingSmartEndpointInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@EnableAutoConfiguration
@Controller
public class loginController {
    @RequestMapping("/login")
    public static String login(ModelMap model){
        model.addAttribute("model","login");
        model.addAttribute("ismember",false);
        model.addAttribute("goway","/login_myquestions");
        return "login";
    }

    @RequestMapping("/Clogin")
    public static String Clogin(ModelMap model){
        model.addAttribute("model","Clogin");
        model.addAttribute("goway","/login_attendant");
        return "login";
    }

    @RequestMapping("/register")
    public static String register(ModelMap model){
        model.addAttribute("model","register");
        model.addAttribute("goway","/addmember");
        return "login";
    }

    @Resource
    private com.example.demo.service.memberService memberService;

    @RequestMapping("/addmember")
    public String addmember(@RequestParam(value = "tel") String tel, @RequestParam(value = "pwd") String pwd,
                            @RequestParam(value = "username") String username, @RequestParam(value = "email") String email, HttpServletRequest request, ModelMap model){
        HttpSession session=request.getSession();//获取session并将userName存入session对象
        Object test=this.memberService.getmemberBytel(tel);
        Object test2=this.memberService.getmemberByemail(email);
        if(test!=null || test2!=null){
            model.addAttribute("model","register");
            model.addAttribute("goway","/addmember");
            model.addAttribute("ismember",true);
            model.addAttribute("wrong","电话或邮箱已使用");
            return "login";
        }
        member member = new member();
        member.setTel(tel);
        member.setUsername(username);
        member.setPwd(pwd);
        member.setEmail(email);
        this.memberService.addmember(member);
        Object test1=this.memberService.getmemberBytel(tel);
        member = (member)test1;
        model.addAttribute("member",member);
        session.setAttribute("userName", member.getUsername());
        session.setAttribute("idno", member.getIdno());

        model.addAttribute("member",member);
        model.addAttribute("opttype","main");
        return "redirect:/myquestions/main";
    }

    @RequestMapping(path = {"/login_myquestions/","/login_myquestions"})
    public  String member_index(@RequestParam(value="tel",required = false) String tel, @RequestParam(value="pwd",required = false) String pwd,HttpServletRequest request,ModelMap model){
        Object test=this.memberService.getmemberBytel(tel);
        HttpSession session=request.getSession();//获取session并将userName存入session对象
        if(test==null){
            model.addAttribute("model","login");
            model.addAttribute("ismember",true);
            model.addAttribute("wrong","账号未注册，请注册");
            model.addAttribute("goway","/login_myquestions");
            return "login";
        }
        member member = (member)test;
        if(member.getPwd().equals(pwd)){
            session.setAttribute("userName", member.getUsername());
            session.setAttribute("idno", member.getIdno());
//            model.addAttribute("member",member);
//            model.addAttribute("opttype","main");
////            String string =stringRedisTemplate.opsForValue().get("aaa");
        }
        else {
            model.addAttribute("model","login");
            model.addAttribute("ismember",true);
            model.addAttribute("wrong","手机号或密码错误");
            model.addAttribute("goway","/login_myquestions");
            return "login";
        }
        return "redirect:/myquestions/main";
    }

    @Resource
    private com.example.demo.service.attendantService attendantService;

    @RequestMapping(path={"/login_attendant","/login_attendant/"})//登录传递
    public  String attendant_index(@RequestParam(value="tel",required = false) String idno, @RequestParam(value="pwd",required = false) String pwd, HttpServletRequest request, ModelMap model){
        Object test=this.attendantService.getattendantByidno(idno);
        HttpSession session=request.getSession();
        if(test==null){
            model.addAttribute("model","Clogin");
            model.addAttribute("ismember",true);
            model.addAttribute("wrong","账号非管理员账号");
            model.addAttribute("goway","/login_attendant");
            return "login";
        }
        attendant attendant = (attendant)test;
        if(attendant.getPwd().equals(pwd)){
            session.setAttribute("attendantName", attendant.getUsername());
            session.setAttribute("idno",attendant.getIdno());
//            model.addAttribute("attendant",attendant);
//            model.addAttribute("opttype","main");
        }
        else {
            model.addAttribute("model","Clogin");
            model.addAttribute("ismember",true);
            model.addAttribute("wrong","账号或密码错误");
            model.addAttribute("goway","/login_attendant");
            return "login";
        }
        return "redirect:/attendant/main";
    }


}
