package com.os.web.controller;

import com.os.web.model.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
    @RequestMapping("/")
    public String home(Model model){
        Msg msg = new Msg();
        msg.setTitle("测试标题");
        msg.setContent("测试内容");
        msg.setEtraInfo("额外信息，只对管理员显示");
        model.addAttribute("msg",msg);
        return "home";
    }
}
