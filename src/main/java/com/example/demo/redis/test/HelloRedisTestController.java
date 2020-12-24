//package com.example.demo.redis.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.Resource;
//
//@RestController
//@EnableAutoConfiguration
//@RequestMapping("/hello")
//public class HelloRedisTestController {
//
//    @Resource
//    private RedisTemplate redisTemplate;
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//    @RequestMapping("/index")
//    public String index(){
//        stringRedisTemplate.opsForValue().set("aaa","111");
//        String string =stringRedisTemplate.opsForValue().get("aaa");
//        System.out.println(string);
//        return "Hello";
//    }
//}
