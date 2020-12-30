package com.example.demo;

import com.example.demo.model.invest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class test {
    public static void main(String[] args){
        List<invest> list=new ArrayList<invest>();
        list.add(new invest("123"));
        list.add(new invest("123"));
        list.add(new invest("124"));
        list.add(new invest("125"));
        list.add(new invest("123"));
        Set<invest> set=new HashSet<invest>();
        set.addAll(list);
        System.out.println("list"+list.toString());
        System.out.println("Set"+set.toString());
    }
}
