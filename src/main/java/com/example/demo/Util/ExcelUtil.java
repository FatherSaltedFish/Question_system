package com.example.demo.Util;

import com.alibaba.excel.EasyExcel;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {
    public static void download(HttpServletResponse response, Class t, List list) throws IOException, IllegalAccessException,InstantiationException {
        response.setContentType("application/vnd.ms-excel");// 设置文本内省
        response.setCharacterEncoding("utf-8");// 设置字符编码
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();
        String s1="attachment;filename=questions"+sdf.format(date)+".xlsx";
        response.setHeader("Content-disposition",s1); // 设置响应头
        EasyExcel.write(response.getOutputStream(), t).sheet("模板").doWrite(list); //用io流来写入数据
    }
}
