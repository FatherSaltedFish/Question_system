package com.example.demo.controller;

import com.example.demo.model.question;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@EnableAutoConfiguration

public class testController {
    @RequestMapping("/test")
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/UploadOneServlet.do", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("multipartFile") List<MultipartFile> multipartFiles) throws Exception {
        String a=null;
        for (MultipartFile multipartFile :multipartFiles) {
            String fileName = multipartFile.getOriginalFilename();
            String filePath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/") + "fileUpload";
            String fileTotalName = filePath + File.separator + fileName;
            File f = new File(fileTotalName);
            multipartFile.transferTo(f);
            a=fileName;
        }
        return a;
    }

    @RequestMapping(value = "/test/add")
    public String add(HttpServletRequest request){
        System.out.println(request.getPathInfo());
        return "test";
    }
}

