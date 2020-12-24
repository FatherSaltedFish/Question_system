package com.example.demo.controller;

import com.example.demo.model.member;
import com.example.demo.model.message;
import com.example.demo.service.messageService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@EnableAutoConfiguration
@Controller
public class indexController {
    @RequestMapping("/")
    public static String index(){
        return "index";
    }

    @Resource
    private messageService messageService;
    @RequestMapping("/user_message")
    public String user_message(@RequestParam(value = "name") String name, @RequestParam(value = "email") String email
            , @RequestParam(value = "message") String user_message, ModelMap modelMap){
        com.example.demo.model.message message=new message();
        message.setName(name);
        message.setMessage(user_message);
        message.setEmail(email);
        modelMap.addAttribute("tof",this.messageService.insert_message(message));
        return ("redirect:/");
    }
}
