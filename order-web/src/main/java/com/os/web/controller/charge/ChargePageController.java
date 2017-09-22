package com.os.web.controller.charge;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChargePageController {
    @RequestMapping("/charge")
    public String chargePage(){
        return "account/account";
    }
}
