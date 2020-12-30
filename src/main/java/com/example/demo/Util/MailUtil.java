package com.example.demo.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Date;

public class MailUtil{

    @Autowired
    JavaMailSender javaMailSender;

    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("这是一封测试邮件");
        message.setFrom("1541987221@qq.com");
        message.setTo("1541987221@qq.com");
        message.setSentDate(new Date());
        message.setText("这是测试邮件的正文");
        javaMailSender.send(message);
    }
}