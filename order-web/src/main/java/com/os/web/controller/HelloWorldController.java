package com.os.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jian Zhu on 12/26/2016.
 */
@Controller
@RequestMapping("/mvc")
public class HelloWorldController {
    private static final Log LOGGER = LogFactory.getLog(HelloWorldController.class);

    @RequestMapping("/hello")
    public String hello(){
        LOGGER.info("accept hello request in controller.");
        return "hello";
    }
}
