package com.os.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jian Zhu on 12/26/2016.
 */
@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    public String hello(ModelMap model){
        model.addAttribute("message","Hello Spring MVC Framework!");
        return "hello";
    }
}
