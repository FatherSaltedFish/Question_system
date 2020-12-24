package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new testInterceptor()).addPathPatterns("/**"); super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/myquestions/**").addPathPatterns("/attendant/**"); super.addInterceptors(registry);
        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/attendant/**"); super.addInterceptors(registry);
    }
}
