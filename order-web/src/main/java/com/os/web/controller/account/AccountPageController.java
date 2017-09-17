package com.os.web.controller.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountPageController {
    @RequestMapping("/acc")
    public String accountPage(){
        return "account";
    }
}
