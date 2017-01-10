package com.os.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jian Zhu on 12/26/2016.
 */
@Controller
@RequestMapping("/mvc")
public class HelloWorldController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}
